package com.cellarhq.webapp

import com.cellarhq.api.services.CellarService
import com.cellarhq.api.services.CellaredDrinkService
import com.cellarhq.api.services.DrinkService
import com.cellarhq.api.services.OrganizationService
import com.cellarhq.auth.profiles.CellarHQProfile
import com.cellarhq.auth.services.photo.PhotoService
import com.cellarhq.common.Messages
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.common.validation.ValidationErrorMapper
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.domain.Photo
import com.cellarhq.domain.views.CellaredDrinkDetails
import com.cellarhq.jooq.SortCommand
import com.cellarhq.util.DateUtil
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.pac4j.RatpackPac4j

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static ratpack.handlebars.Template.handlebarsTemplate

@SuppressWarnings(['LineLength', 'MethodSize'])
@Slf4j
class CellarsEndpoint implements Action<Chain> {

  ValidatorFactory validatorFactory
  CellarService cellarService
  CellaredDrinkService cellaredDrinkService
  DrinkService drinkService
  OrganizationService organizationService
  PhotoService photoService

  @Inject
  CellarsEndpoint(ValidatorFactory validatorFactory,
                  CellarService cellarService,
                  CellaredDrinkService cellaredDrinkService,
                  DrinkService drinkService,
                  OrganizationService organizationService,
                  PhotoService photoService) {
    this.validatorFactory = validatorFactory
    this.cellarService = cellarService
    this.cellaredDrinkService = cellaredDrinkService
    this.drinkService = drinkService
    this.organizationService = organizationService
    this.photoService = photoService
  }

  @Override
  void execute(Chain chain) throws Exception {
    Groovy.chain(chain) {
      path('cellars') {
        byMethod {
          get {
            Integer requestedPage = request.queryParams.page?.toInteger() ?: 1
            Integer pageSize = 20
            Integer offset = (requestedPage - 1) * pageSize
            String searchTerm = request.queryParams.search

            rx.Observable<Integer> totalCount = cellarService.count(searchTerm).single()

            rx.Observable cellars = searchTerm ?
              cellarService.search(
                searchTerm,
                SortCommand.fromRequest(request),
                pageSize,
                offset).toList() :
              cellarService.all(SortCommand.fromRequest(request), pageSize, offset).toList()

            rx.Observable.zip(cellars, totalCount) { List list, Integer count ->
              [
                cellars   : list,
                totalCount: count
              ]
            }.subscribe({ Map map ->
              Integer pageCount = (map.totalCount / pageSize)
              Boolean shouldShowPagination = pageCount != 0

              render handlebarsTemplate('cellars/list-cellars.html',
                [cellars             : map.cellars,
                 currentPage         : requestedPage,
                 totalPageCount      : pageCount,
                 shouldShowPagination: shouldShowPagination,
                 title               : 'CellarHQ : Cellars',
                 pageId              : 'cellars.list'])
            }, {
              clientError 500
            })
          }
        }
      }

      path('cellars/:slug') {
        byMethod {
          get {
            Optional<CellarHQProfile> profile = context.maybeGet(CellarHQProfile)

            String slug = pathTokens['slug']

            rx.Observable.zip(
              cellarService.findBySlug(slug).single(),
              cellaredDrinkService.all(slug, SortCommand.fromRequest(request)).toList(),
              photoService.findByCellarSlug(slug)
            ) { Cellar cellar, List cellaredDrinks, Photo photo ->
              [
                cellar        : cellar,
                cellaredDrinks: cellaredDrinks,
                photo         : photo
              ]
            }.subscribe { Map map ->
              if (map.cellar == null) {
                clientError 404
              } else {
                if (!isSelf(profile, map.cellar.id) && map.cellar.private) {
                  SessionUtil.setFlash(context, FlashMessage.info(Messages.PRIVATE_CELLAR)).then {
                    redirect('/cellars')
                  }
                } else {
                render handlebarsTemplate('cellars/show-cellar.html',
                  [cellar        : map.cellar,
                   photo         : map.photo,
                   cellaredDrinks: map.cellaredDrinks,
                   self          : isSelf(profile, map.cellar.id),
                   title         : "CellarHQ : ${map.cellar.displayName}",
                   pageId        : 'cellars.show'])
                }
              }
            }
          }
        }
      }

      post('cellars/:slug/empty') { CellarHQProfile profile ->
        String slug = pathTokens['slug']

        cellarService.findBySlugPromise(slug).then { Cellar cellar ->
          cellarService.emptyCellar(cellar).then {
            log.debug("Deleting cellared beers for ${cellar.screenName}")
            context.redirect('/yourcellar')
          }
        }
      }

      post('cellars/:slug/delete') { CellarHQProfile profile ->
        String slug = pathTokens['slug']

        cellarService.findBySlugPromise(slug).then { Cellar cellar ->
          if (isSelf(profile.cellarId, cellar.id)) {
            cellarService.deleteCellar(cellar).then {
              RatpackPac4j.logout(context).then {
                redirect("/register?success=$Messages.CELLAR_DELETED")
              }
            }
          } else {
            clientError 405
          }
        }
      }

      get('cellars/:slug/archive') {
        Optional<CellarHQProfile> profile = context.maybeGet(CellarHQProfile)
        String slug = pathTokens['slug']

        rx.Observable.zip(
          cellarService.findBySlug(slug).single(),
          cellaredDrinkService.archive(slug, SortCommand.fromRequest(request)).toList(),
          photoService.findByCellarSlug(slug).single()
        ) { Cellar cellar, List cellaredDrinks, Photo photo ->
          [
            cellar : cellar,
            archive: cellaredDrinks,
            photo  : photo
          ]
        }.subscribe { Map map ->
          if (map.cellar == null) {
            log.error(LogUtil.toLog('archive', [
              msg : 'Could not locate a cellar by slug',
              slug: slug
            ]))
            clientError 404
          } else {
            render handlebarsTemplate('cellars/show-archive.html',
              [cellar : map.cellar,
               photo  : map.photo,
               archive: map.archive,
               self   : isSelf(profile, map.cellar.id),
               title  : 'CellarHQ : Your Cellar',
               pageId : 'cellars.archive']
            )
          }
        }
      }

      get('cellars/:slug/export') {
          cellaredDrinkService.csv(pathTokens['slug'], SortCommand.fromRequest(request)).then { String csv ->
            render csv
          }
      }

      path('cellars/:slug/drinks') { CellarHQProfile profile ->
        byMethod {
          post {
            String slug = pathTokens['slug']
            parse(Form).then { Form form ->

              if (!form.beerId) {
                SessionUtil.setFlash(context, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, [
                  'beerId must be set. Make sure javascript is enabled prior to selecting a beer.'
                ])).then {
                  return redirect('/yourcellar')
                }
              }


              CellaredDrink drink = applyForm(new CellaredDrink(), form).with { CellaredDrink self ->
                cellarId = profile.cellarId
                drinkId = Long.valueOf(form.beerId)
                return self
              }

              Validator validator = validatorFactory.validator
              Set<ConstraintViolation<CellaredDrink>> drinkViolations = validator.validate(drink)

              if (drinkViolations.empty) {
                rx.Observable.zip(
                  cellaredDrinkService.save(drink).single(),
                  drinkService.findNameById(drink.drinkId).single(),
                  organizationService.findNameByDrink(drink.drinkId).single()
                ) { CellaredDrink savedDrink, String drinkName, String orgName ->
                  [
                    cellared: savedDrink,
                    drink   : drinkName,
                    org     : orgName
                  ]
                }.subscribe({ Map map ->
                  SessionUtil.setFlash(context, FlashMessage.success(
                    String.format(Messages.CELLARED_DRINK_SAVED, map.drink, map.org),
                    new FlashMessage.SocialButton(
                      String.format(Messages.CELLARED_DRINK_SAVED_SOCIAL, map.drink, map.org),
                      "/cellars/${slug}"
                    ))).then {
                    redirect('/yourcellar')
                  }
                })
              } else {
                List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
                SessionUtil.setFlash(context, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages)).then {
                  redirect('/yourcellar')
                }
              }
            }
          }
        }
      }

      post('cellars/:slug/drinks/:drinkId') {
        Long drinkId = Long.valueOf(pathTokens['drinkId'])

        cellaredDrinkService.findById(drinkId).single().subscribe { CellaredDrink drink ->
          if (drink) {
            parse(Form).then { Form form ->
              CellaredDrink editedDrink = applyForm(drink, form)

              Validator validator = validatorFactory.validator
              Set<ConstraintViolation<CellaredDrink>> drinkViolations = validator.validate(editedDrink)
              if (drinkViolations.empty) {
                rx.Observable.zip(
                  cellaredDrinkService.save(editedDrink).single(),
                  drinkService.findNameById(drink.drinkId).single(),
                  organizationService.findNameByDrink(drink.drinkId).single()
                ) { CellaredDrink savedDrink, String drinkName, String orgName ->
                  [
                    cellared: savedDrink,
                    drink   : drinkName,
                    org     : orgName
                  ]
                }.subscribe { Map map ->
                  SessionUtil.setFlash(context, FlashMessage.success(
                    String.format(Messages.BEER_EDIT_SAVED, map.drink, map.org))).then {
                    redirect('/yourcellar')
                  }
                }
              } else {
                List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
                SessionUtil.setFlash(context, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages)).then {
                  redirect("${request.uri}/edit")
                }
              }
            }
          } else {
            clientError 404
          }
        }
      }

      get('cellars/:slug/drinks/:drinkId/edit') {
        Optional<CellarHQProfile> profile = context.maybeGet(CellarHQProfile)
        String slug = pathTokens['slug']
        Long drinkId = Long.valueOf(pathTokens['drinkId'])

        cellaredDrinkService.findByIdForEdit(slug, drinkId).single().subscribe { CellaredDrinkDetails drink ->
          if (drink) {
            if (isSelf(profile, drink.cellarId)) {
              render handlebarsTemplate('cellars/edit-cellared-drink.html',
                [action       : request.uri.replace('/edit', ''),
                 cellaredDrink: drink,
                 title        : 'CellarHQ : Edit Cellared Drink',
                 pageId       : 'cellared-drink.edit'])
            } else {
              SessionUtil.setFlash(context, FlashMessage.warning(Messages.UNAUTHORIZED_ERROR)).then {
                redirect('/yourcellar')
              }
            }
          }
        }
      }
    }
  }

  private boolean isSelf(Long sessionCellarId, Long requestCellarId) {
    return (sessionCellarId && requestCellarId == sessionCellarId)
  }

  private boolean isSelf(Optional<CellarHQProfile> profile, Long requestCellarId) {
    if (!profile.present) {
      return false
    }

    return (requestCellarId == profile.get().cellarId)
  }

  private CellaredDrink applyForm(CellaredDrink cellaredDrink, Form form) {
    return cellaredDrink.with { CellaredDrink self ->
      size = form.size
      quantity = form.quantity ? Long.valueOf(form.quantity) : 0
      notes = form.notes
      binIdentifier = form.binIdentifier

      tradeable = form.tradeable == 'on'
      numTradeable = form.numTradeable ? Short.valueOf(form.numTradeable) : 0

      DateUtil.parse(form.bottleDate).map { bottleDate = it }
      DateUtil.parse(form.drinkByDate).map { drinkByDate = it }
      DateUtil.parse(form.dateAcquired).map { dateAcquired = it }

      return self
    }
  }
}
