package com.cellarhq.endpoints

import com.cellarhq.Messages
import com.cellarhq.domain.Availability
import com.cellarhq.domain.Drink
import com.cellarhq.domain.DrinkType
import com.cellarhq.jooq.SortCommand
import com.cellarhq.services.DrinkService
import com.cellarhq.services.StyleService
import com.cellarhq.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.cellarhq.validation.ValidationErrorMapper
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.form.Form

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class BeerEndpoint implements Action<Chain> {

    ValidatorFactory validatorFactory
    DrinkService drinkService
    StyleService styleService

    @Inject
    BeerEndpoint(ValidatorFactory validatorFactory, DrinkService drinkService, StyleService styleService) {
        this.validatorFactory = validatorFactory
        this.drinkService = drinkService
        this.styleService = styleService
    }

    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            handler('beers') {
                byMethod {
                    /**
                     * List all breweries; has search.
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
                            log.error(LogUtil.toLog('ListBeerError'), t)
                            clientError 500
                        })
                    }

                    /**
                     * Add a new brewery.
                     */
                    post {
                        Form form = parse(Form)

                        Drink drink = applyFrom(new Drink(), form)

                        Validator validator = validatorFactory.validator
                        Set<ConstraintViolation<Drink>> drinkViolations = validator.validate(drink)

                        if (drinkViolations.empty) {
                            drinkService.save(drink).single().subscribe { Drink savedDrink ->
                                redirect("/beers/${savedDrink.slug}")
                            }
                        } else {
                            List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
                            SessionUtil.setFlash(request, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages))
                            redirect('/beers/add')
                        }
                    }
                }
            }

            /**
             * HTML page for adding a new brewery.
             */
            get('beers/add') {
                render handlebarsTemplate('beer/new-beer.html', [
                        drink: new Drink(),
                        title: 'CellarHQ : Add New Beer',
                        pageId: 'beers.new',
                        availability: Availability.toHandlebars()
                ])
            }

            /**
             * HTML page for editing breweries.
             */
            get('beers/:slug/edit') {
                String slug = pathTokens['slug']
                drinkService.findBySlug(slug).single().subscribe { Drink drink ->
                    if (drink.editable) {
                        render handlebarsTemplate('beer/edit-beer.html', [
                                beer: drink,
                                title: "CellarHQ : Edit ${drink.name}",
                                pageId: 'beer.edit'
                        ])
                    }
                }
            }

            handler('beers/:slug') {
                byMethod {
                    /**
                     * Get an existing brewery.
                     */
                    get {
                        String slug = pathTokens['slug']

                        drinkService.findBySlug(slug).single().subscribe { Drink drink ->
                            render handlebarsTemplate('beer/show-beer.html',
                                [drink : drink,
                                 title : "CellarHQ : ${drink.name}",
                                 pageId: 'beer.show'])
                        }
                    }
                }

                post {
                    String slug = pathTokens['slug']
                    Form form = parse(Form)

                    drinkService.findBySlug(slug).single().subscribe { Drink drink ->
                        if (drink) {
                            if (drink.editable) {
                                applyFrom(drink, form)
                                drinkService.save(drink)
                                    .single()
                                    .subscribe { Drink savedDrink -> redirect("/beers/${savedDrink.slug}") }
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

    private Drink applyFrom(Drink drink, Form form) {
        if (!drink.id) {
            drink.with {
                organizationId = Long.valueOf(form.organizationId)
                styleId = Long.valueOf(form.styleId)
                glasswareId = Long.valueOf(form.glasswareId)
                drinkType = DrinkType.BEER
                slug = form.name
                name = form.name
            }
        }
        drink.with {
            description = form.description
            srm = Integer.valueOf(form.srm)
            ibu = Integer.valueOf(form.ibu)
            abv = BigDecimal.valueOf(form.abv)
            availability = form.availability
        }
        return drink
    }
}
