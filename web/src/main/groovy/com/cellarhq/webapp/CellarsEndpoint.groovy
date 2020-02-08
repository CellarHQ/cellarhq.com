package com.cellarhq.webapp

import com.amazonaws.services.dynamodbv2.xspec.S
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
import com.cellarhq.domain.Drink
import com.cellarhq.domain.Organization
import com.cellarhq.domain.Photo
import com.cellarhq.domain.views.CellaredDrinkDetails
import com.cellarhq.jooq.SortCommand
import com.cellarhq.util.DateUtil
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.func.Pair
import ratpack.groovy.handling.GroovyChainAction
import ratpack.pac4j.RatpackPac4j

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static com.cellarhq.common.Messages.BEER_EDIT_SAVED
import static com.cellarhq.common.Messages.CELLARED_DRINK_SAVED
import static com.cellarhq.common.Messages.CELLARED_DRINK_SAVED_SOCIAL
import static com.cellarhq.common.Messages.FORM_VALIDATION_ERROR
import static com.cellarhq.common.Messages.PRIVATE_CELLAR
import static com.cellarhq.common.Messages.UNAUTHORIZED_ERROR
import static com.cellarhq.common.session.FlashMessage.error
import static com.cellarhq.common.session.FlashMessage.info
import static com.cellarhq.common.session.FlashMessage.success
import static com.cellarhq.common.session.FlashMessage.warning
import static com.cellarhq.util.SessionUtil.setFlash
import static java.lang.String.format
import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class CellarsEndpoint extends GroovyChainAction {

  ValidatorFactory validatorFactory
  CellarService cellarService
  CellaredDrinkService cellaredDrinkService
  DrinkService drinkService
  OrganizationService organizationService
  PhotoService photoService
  BinStatsService binStatsService

  @Inject
  CellarsEndpoint(ValidatorFactory validatorFactory,
                  CellarService cellarService,
                  CellaredDrinkService cellaredDrinkService,
                  DrinkService drinkService,
                  OrganizationService organizationService,
                  PhotoService photoService,
                  BinStatsService binStatsService) {
    this.validatorFactory = validatorFactory
    this.cellarService = cellarService
    this.cellaredDrinkService = cellaredDrinkService
    this.drinkService = drinkService
    this.organizationService = organizationService
    this.photoService = photoService
    this.binStatsService = binStatsService
  }

  @Override
  void execute() throws Exception {
    path('cellars') {
      byMethod {
        get {
          Integer requestedPage = request.queryParams.page?.toInteger() ?: 1
          Integer pageSize = 20
          Integer offset = (requestedPage - 1) * pageSize
          String searchTerm = request.queryParams.search

          cellarService.count(searchTerm).flatRight {
            searchTerm ?
              cellarService.search(searchTerm, SortCommand.fromRequest(request), pageSize, offset) :
              cellarService.all(SortCommand.fromRequest(request), pageSize, offset)
          }.then { Pair<Integer, List<Cellar>> pair ->
            Integer pageCount = (pair.left / pageSize).toInteger()
            Boolean shouldShowPagination = pageCount != 0

            render handlebarsTemplate('cellars/list-cellars.html',
              [cellars             : pair.right,
               currentPage         : requestedPage,
               totalPageCount      : pageCount,
               shouldShowPagination: shouldShowPagination,
               title               : 'CellarHQ : Cellars',
               pageId              : 'cellars.list'])
          }
        }
      }
    }

    path('cellars/:slug') {
      byMethod {
        get {
          Optional<CellarHQProfile> profile = context.maybeGet(CellarHQProfile)
          String slug = pathTokens['slug']

          cellarService.findBySlug(slug).flatRight {
            cellaredDrinkService.all(slug, SortCommand.fromRequest(request))
          }.flatRight {
            photoService.findByCellarSlug(slug)
          }.then { Pair<Pair<Cellar, List<CellaredDrinkDetails>>, Photo> pair ->
            if (pair.left.left == null) {
              clientError 404
            } else {
              if (!isSelf(profile, pair.left.left.id) && pair.left.left.private) {
                setFlash(context, info(PRIVATE_CELLAR)).then {
                  redirect('/cellars')
                }
              } else {
                render handlebarsTemplate('cellars/show-cellar.html',
                  [cellar            : pair.left.left,
                   photo             : pair.right,
                   cellaredDrinks    : pair.left.right,
                   usesBinIdentifiers: binStatsService.binsPresent(pair.left.right as List<CellaredDrink>),
                   binStats          : binStatsService.calculateBinStats(pair.left.right as List<CellaredDrink>),
                   self              : isSelf(profile, pair.left.left.id),
                   title             : "CellarHQ : ${pair.left.left.displayName}",
                   pageId            : 'cellars.show'])
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

      cellarService.findBySlug(slug).flatRight {
        cellaredDrinkService.archive(slug, SortCommand.fromRequest(request))
      }.flatRight {
        photoService.findByCellarSlug(slug)
      }.then { Pair<Pair<Cellar, List<CellaredDrinkDetails>>, Photo> pair ->
        if (!pair.left.left) {
          log.error(LogUtil.toLog('archive', [
            msg : 'Could not locate a cellar by slug',
            slug: slug
          ]))
          clientError 404
        } else {
          render handlebarsTemplate('cellars/show-archive.html',
            [cellar : pair.left.left,
             photo  : pair.right,
             archive: pair.left.right,
             self   : isSelf(profile, pair.left.left.id),
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
              setFlash(context, error(FORM_VALIDATION_ERROR, [
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
              cellaredDrinkService.save(drink).flatRight {
                drinkService.findNameById(drink.drinkId)
              }.flatRight {
                organizationService.findNameByDrink(drink.drinkId)
              }.then { Pair<Pair<CellaredDrink, Drink>, Organization> pair ->
                setFlash(context,
                  success(format(CELLARED_DRINK_SAVED, pair.left.right, pair.right),
                  new FlashMessage.SocialButton(format(CELLARED_DRINK_SAVED_SOCIAL, pair.left.right, pair.right), "/cellars/${slug}"
                  ))).then {
                  redirect('/yourcellar')
                }
              }
            } else {
              List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
              setFlash(context, error(FORM_VALIDATION_ERROR, messages)).then {
                redirect('/yourcellar')
              }
            }
          }
        }
      }
    }

    post('cellars/:slug/drinks/:drinkId') {
      Long drinkId = Long.valueOf(pathTokens['drinkId'])

      cellaredDrinkService.findById(drinkId).then { CellaredDrink drink ->
        if (drink) {
          parse(Form).then { Form form ->
            CellaredDrink editedDrink = applyForm(drink, form)

            Validator validator = validatorFactory.validator
            Set<ConstraintViolation<CellaredDrink>> drinkViolations = validator.validate(editedDrink)

            if (drinkViolations.empty) {
                cellaredDrinkService.save(editedDrink).flatRight() {
                  drinkService.findNameById(drink.drinkId)
                }.flatRight {
                  organizationService.findNameByDrink(drink.drinkId)
                }.then { Pair<Pair<CellaredDrink, Drink>, Organization> pair ->
                  setFlash(context, success(format(BEER_EDIT_SAVED, pair.left.right, pair.right))).then {
                    redirect('/yourcellar')
                  }
                }
            } else {
              List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
              setFlash(context, error(FORM_VALIDATION_ERROR, messages)).then {
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

      cellaredDrinkService.findByIdForEdit(slug, drinkId).then { CellaredDrinkDetails drink ->
        if (drink) {
          if (isSelf(profile, drink.cellarId)) {
            render handlebarsTemplate('cellars/edit-cellared-drink.html',
              [action       : request.uri.replace('/edit', ''),
               cellaredDrink: drink,
               title        : 'CellarHQ : Edit Cellared Drink',
               pageId       : 'cellared-drink.edit'])
          } else {
            setFlash(context, warning(UNAUTHORIZED_ERROR)).then {
              redirect('/yourcellar')
            }
          }
        }
      }
    }
  }

  private static boolean isSelf(Long sessionCellarId, Long requestCellarId) {
    return (sessionCellarId && requestCellarId == sessionCellarId)
  }

  private static boolean isSelf(Optional<CellarHQProfile> profile, Long requestCellarId) {
    if (!profile.present) {
      return false
    }

    return (requestCellarId == profile.get().cellarId)
  }

  private static CellaredDrink applyForm(CellaredDrink cellaredDrink, Form form) {
    return cellaredDrink.with { CellaredDrink self ->
      size = form.size
      quantity = form.quantity ? Integer.valueOf(form.quantity) : 0
      notes = form.notes
      binIdentifier = form.binIdentifier

      tradeable = form.tradeable == 'on'
      numTradeable = form.numTradeable ? Short.valueOf(form.numTradeable) : 0 as Short

      DateUtil.parse(form.bottleDate).map { bottleDate = it }
      DateUtil.parse(form.drinkByDate).map { drinkByDate = it }
      DateUtil.parse(form.dateAcquired).map { dateAcquired = it }

      return self
    }
  }
}
