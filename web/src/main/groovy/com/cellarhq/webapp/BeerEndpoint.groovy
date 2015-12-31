package com.cellarhq.webapp

import com.cellarhq.api.services.CellaredDrinkService
import com.cellarhq.api.services.DrinkService
import com.cellarhq.api.services.OrganizationService
import com.cellarhq.api.services.StyleService
import com.cellarhq.auth.services.photo.PhotoService
import com.cellarhq.common.Messages
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.common.validation.ValidationErrorMapper
import com.cellarhq.domain.*
import com.cellarhq.domain.views.CellaredDrinkDetails
import com.cellarhq.jooq.SortCommand
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static ratpack.handlebars.Template.handlebarsTemplate

@SuppressWarnings(['LineLength', 'MethodSize'])
@Slf4j
class BeerEndpoint implements Action<Chain> {

  ValidatorFactory validatorFactory
  DrinkService drinkService
  StyleService styleService
  OrganizationService organizationService
  PhotoService photoService
  CellaredDrinkService cellaredDrinkService

  @Inject
  BeerEndpoint(ValidatorFactory validatorFactory,
               DrinkService drinkService,
               StyleService styleService,
               OrganizationService organizationService,
               PhotoService photoService,
               CellaredDrinkService cellaredDrinkService) {

    this.validatorFactory = validatorFactory
    this.drinkService = drinkService
    this.styleService = styleService
    this.organizationService = organizationService
    this.photoService = photoService
    this.cellaredDrinkService = cellaredDrinkService
  }

  @Override
  void execute(Chain chain) throws Exception {
    Groovy.chain(chain) {
      path('beers') {
        byMethod {
          /**
           * List all beers; has search.
           */
          get {
            Integer requestedPage = request.queryParams.page?.toInteger() ?: 1
            Integer pageSize = 20
            Integer offset = (requestedPage - 1) * pageSize
            String searchTerm = request.queryParams.search

            rx.Observable<Integer> totalCount = searchTerm ?
              drinkService.searchCount(searchTerm).single() :
              drinkService.count().single()

            rx.Observable drinks = searchTerm ?
              drinkService.search(
                searchTerm,
                SortCommand.fromRequest(request),
                pageSize,
                offset).toList() :
              drinkService.all(
                SortCommand.fromRequest(request),
                pageSize,
                offset).toList()


            rx.Observable.zip(drinks, totalCount) { List list, Integer count ->
              [
                drinks    : list,
                totalCount: count
              ]
            }.subscribe({ Map map ->
              Integer pageCount = (map.totalCount / pageSize)
              Boolean shouldShowPagination = pageCount != 0

              render handlebarsTemplate('beer/list-beer.html',
                [drinks              : map.drinks,
                 currentPage         : requestedPage,
                 totalPageCount      : pageCount,
                 shouldShowPagination: shouldShowPagination,
                 title               : 'CellarHQ : Beer',
                 pageId              : 'beer.list'])
            }, { Throwable t ->
              log.error(LogUtil.toLog(request, 'ListBeerError'), t)
              clientError 500
            })
          }

          post {
            parse(Form).then { Form form ->

              Drink drink
              try {
                drink = applyFrom(new Drink(), form)
              } catch (NumberFormatException e) {
                SessionUtil.setFlash(context, FlashMessage.warning(Messages.FORM_VALIDATION_ERROR))
                return redirect('/beers/add')
              }

              Validator validator = validatorFactory.validator
              Set<ConstraintViolation<Drink>> drinkViolations = validator.validate(drink)

              if (drinkViolations.empty) {

                rx.Observable.zip(
                  drinkService.save(drink).single(),
                  organizationService.get(drink.organizationId).single()
                ) { Drink savedDrink, Organization organization ->
                  [
                    savedDrink: savedDrink,
                    org       : organization
                  ]
                }.subscribe({ Map map ->
                  redirect("/breweries/${map.org.slug}/beers/${map.savedDrink.slug}")
                }, { Throwable t ->
                  if (t.message.contains('unq_drink_slug')) {
                    SessionUtil.setFlash(context, FlashMessage.error(
                      String.format(Messages.BEER_ADD_ALREADY_EXISTS_ERROR, drink.slug)
                    ))
                  } else {
                    SessionUtil.setFlash(context, FlashMessage.error(Messages.FORM_VALIDATION_ERROR))
                  }
                  return redirect('/beers/add')
                })
              } else {
                List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
                SessionUtil.setFlash(context, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages))
                redirect('/beers/add')
              }
            }
          }
        }
      }


      get('beers/add') {
        render handlebarsTemplate('beer/new-beer.html', [
          org         : null,
          drink       : new Drink(),
          title       : 'CellarHQ : Add New Beer',
          pageId      : 'beers.new',
          availability: Availability.toHandlebars()
        ])

      }

      /**
       * HTML page for adding a new beer to a brewery.
       */
      get('breweries/:brewery/beers/add') {
        organizationService.findBySlug(pathTokens['brewery']).single().subscribe({ Organization org ->
          if (org) {
            render handlebarsTemplate('beer/new-beer.html', [
              org         : org,
              drink       : new Drink(),
              title       : 'CellarHQ : Add New Beer',
              pageId      : 'beers.new',
              availability: Availability.toHandlebars()
            ])
          } else {
            clientError 404
          }
        }, { Throwable t ->
          log.error(LogUtil.toLog(request, 'ShowBeerError'), t)
          clientError 500
        })
      }

      /**
       * HTML page for editing a beer belonging to a brewery.
       */
      get('breweries/:brewery/beers/:slug/edit') {
        String slug = pathTokens['slug']
        String brewery = pathTokens['brewery']
        drinkService.findBySlug(brewery, slug).single().subscribe({ Drink drink ->
          if (drink && drink.organizationSlug == pathTokens['brewery']) {
            if (drink.editable) {
              render handlebarsTemplate('beer/edit-beer.html', [
                drink       : drink,
                title       : "CellarHQ : Edit ${drink.name}",
                pageId      : 'beer.edit',
                availability: Availability.toHandlebars()
              ])
            } else {
              clientError 403
            }
          } else {
            clientError 404
          }
        }, { Throwable t ->
          log.error(LogUtil.toLog(request, 'ShowBeerError'), t)
          clientError 500
        })
      }

      post('breweries/:brewery/beers') {
        parse(Form).then { Form form ->

          Drink drink
          try {
            drink = applyFrom(new Drink(), form)
          } catch (NumberFormatException e) {
            SessionUtil.setFlash(context, FlashMessage.warning(Messages.FORM_VALIDATION_ERROR))
            return redirect("breweries/${pathTokens['brewery']}/beers/add")
          }

          Validator validator = validatorFactory.validator
          Set<ConstraintViolation<Drink>> drinkViolations = validator.validate(drink)

          if (drinkViolations.empty) {
            // TODO: We could get away with one less query if we made the insert select id from org.
            organizationService.findBySlug(pathTokens['brewery']).single().subscribe({ Organization org ->
              drink.organizationId = org.id

              drinkService.save(drink).single().subscribe({ Drink savedDrink ->
                redirect("/breweries/${pathTokens['brewery']}/beers/${savedDrink.slug}")
              }, { Throwable t ->
                if (t.message.contains('unq_drink_slug')) {
                  SessionUtil.setFlash(context, FlashMessage.error(
                    String.format(Messages.BEER_ADD_ALREADY_EXISTS_ERROR, drink.slug)
                  ))
                } else {
                  SessionUtil.setFlash(context, FlashMessage.error(Messages.FORM_VALIDATION_ERROR))
                }
                redirect("/breweries/${pathTokens['brewery']}/beers/add")
              })
            }, { Throwable t ->
              log.error(LogUtil.toLog(request, 'ShowBeerError'), t)
              clientError 500
            })
          } else {
            List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
            SessionUtil.setFlash(context, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages))
            redirect("/breweries/${pathTokens['brewery']}/beers/add")
          }
        }
      }

      path('breweries/:brewery/beers/:slug') {

        byMethod {
          /**
           * Get an existing beer.
           */
          get {
            String slug = pathTokens['slug']
            String brewery = pathTokens['brewery']

            rx.Observable photo = photoService.findByOrganizationAndDrink(brewery, slug).single()
            rx.Observable drink = drinkService.findBySlug(brewery, slug).single()
            rx.Observable cellaredDrinks = cellaredDrinkService.findTradeableCellaredDrinks(
              brewery, slug, SortCommand.fromRequest(request)).toList()

            rx.Observable.zip(photo, drink, cellaredDrinks)
            { Photo photo1, Drink drink1, List<CellaredDrinkDetails> drinks ->
              [
                photo         : photo1,
                drink         : drink1,
                tradableDrinks: drinks
              ]
            }.subscribe({ Map map ->
              if (map.drink) {
                render handlebarsTemplate('beer/show-beer.html',
                  [drink         : map.drink,
                   photo         : map.photo,
                   tradableDrinks: map.tradableDrinks,
                   title         : "CellarHQ : ${map.drink.name}",
                   pageId        : 'beer.show'])
              } else {
                clientError 404
              }
            }, { Throwable t ->
              log.error(LogUtil.toLog(request, 'ShowBeerError'), t)
              clientError 500
            })
          }

          /**
           * Edit an existing beer.
           */
          post {
            String slug = pathTokens['slug']
            String brewery = pathTokens['brewery']
            parse(Form).then { Form form ->

              drinkService.findBySlug(brewery, slug).single().subscribe { Drink drink ->
                if (drink) {
                  if (drink.editable) {
                    applyFrom(drink, form)

                    Validator validator = validatorFactory.validator
                    Set<ConstraintViolation<Drink>> drinkViolations = validator.validate(drink)

                    if (drinkViolations.empty) {
                      drinkService.save(drink)
                        .single()
                        .subscribe { Drink savedDrink ->

                        SessionUtil.setFlash(context, FlashMessage.success(Messages.BEER_EDIT_SAVED))
                        redirect("/breweries/${pathTokens['brewery']}/beers/${savedDrink.slug}")
                      }
                    } else {
                      List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
                      SessionUtil.setFlash(context,
                        FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages)
                      )
                      redirect("/breweries/${pathTokens['brewery']}/beers/${slug}/edit")
                    }
                  } else {
                    clientError 403
                  }
                } else {
                  clientError 404
                }
              }
            }
          }
        }
      }

    }
  }

  private Drink applyFrom(Drink drink, Form form) {
    if (!drink.id) {
      drink.with {
        drinkType = DrinkType.BEER
        slug = form.name
        name = form.name
        organizationId = form.organizationId.toLong()
      }
    }

    drink.with {
      description = form.description

      if (form.styleId) {
        styleId = form.styleId.toLong()
      }
      if (form.glasswareId) {
        glasswareId = form.glasswareId.toLong()
      }
      if (form.srm) {
        srm = form.srm.toInteger()
      }
      if (form.ibu) {
        ibu = form.ibu.toInteger()
      }
      if (form.abv) {
        abv = form.abv.toBigDecimal()
      }
      if (form.availability) {
        availability = form.availability
      }

      needsModeration = true
    }
    return drink
  }
}
