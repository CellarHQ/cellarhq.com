package com.cellarhq.endpoints

import com.cellarhq.Messages
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.domain.views.CellaredDrinkDetails
import com.cellarhq.jooq.SortCommand
import com.cellarhq.services.CellarService
import com.cellarhq.services.CellaredDrinkService
import com.cellarhq.services.DrinkService
import com.cellarhq.services.OrganizationService
import com.cellarhq.session.FlashMessage
import com.cellarhq.util.SessionUtil
import com.cellarhq.validation.ValidationErrorMapper
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.handling.Context
import ratpack.session.store.SessionStorage

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import static ratpack.handlebars.Template.handlebarsTemplate

@SuppressWarnings('MethodSize')
@Slf4j
class CellarsEndpoint implements Action<Chain> {

    ValidatorFactory validatorFactory
    CellarService cellarService
    CellaredDrinkService cellaredDrinkService
    DrinkService drinkService
    OrganizationService organizationService

    @Inject
    CellarsEndpoint(ValidatorFactory validatorFactory,
                    CellarService cellarService,
                    CellaredDrinkService cellaredDrinkService,
                    DrinkService drinkService,
                    OrganizationService organizationService) {
        this.validatorFactory = validatorFactory
        this.cellarService = cellarService
        this.cellaredDrinkService = cellaredDrinkService
        this.drinkService = drinkService
        this.organizationService = organizationService
    }

    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            handler('cellars') {
                byMethod {
                    get {
                        Integer requestedPage = request.queryParams.page?.toInteger() ?: 1
                        Integer pageSize = 20
                        Integer offset = (requestedPage - 1) * pageSize
                        String searchTerm = request.queryParams.search

                        rx.Observable<Integer> totalCount = cellarService.count(searchTerm).single()

                        rx.Observable cellars = searchTerm ?
                                cellarService.search(searchTerm, SortCommand.fromRequest(request), pageSize, offset).toList() :
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

            handler('cellars/:slug') {
                byMethod {
                    get {
                        String slug = pathTokens['slug']

                        rx.Observable.zip(
                                cellarService.findBySlug(slug).single(),
                                cellaredDrinkService.all(slug, SortCommand.fromRequest(request)).toList()
                        ) { Cellar cellar, List cellaredDrinks ->
                            [
                                    cellar        : cellar,
                                    cellaredDrinks: cellaredDrinks,
                            ]
                        }.subscribe { Map map ->
                            if (map.cellar == null) {
                                clientError 404
                            } else {
                                Long cellarId = (Long) request.get(SessionStorage).get(SecurityModule.SESSION_CELLAR_ID)
                                boolean isSelf = map.cellar.id == cellarId

                                render handlebarsTemplate('cellars/show-cellar.html',
                                        [cellar        : map.cellar,
                                         photo         : map.cellar.photo,
                                         cellaredDrinks: map.cellaredDrinks,
                                         self          : isSelf,
                                         title         : "CellarHQ : ${map.cellar.displayName}",
                                         pageId        : 'cellars.show'])
                            }
                        }
                    }
                }
            }

            post('cellars/:slug/drinks') {
                String slug = pathTokens['slug']

                Form form = parse(Form)
                CellaredDrink drink = applyForm(new CellaredDrink(), form).with { CellaredDrink self ->
                    cellarId = (long) request.get(SessionStorage).get(SecurityModule.SESSION_CELLAR_ID)
                    drinkId = Long.valueOf(form.beerId)
                    return self
                }

                Validator validator = validatorFactory.validator
                Set<ConstraintViolation<CellaredDrink>> drinkViolations = validator.validate(drink)

                if (drinkViolations.empty) {
                    // TODO: New services for these one-off cases? YourCellarService? Doesn't make sense to make two
                    //       queries for two small pieces of data.
                    rx.Observable.zip(
                            cellaredDrinkService.save(drink).single(),
                            drinkService.findNameById(drink.drinkId).single(),
                            organizationService.findNameByDrinkId(drink.drinkId).single()
                    ) { CellaredDrink savedDrink, String drinkName, String orgName ->
                        [
                                cellared: savedDrink,
                                drink   : drinkName,
                                org     : orgName
                        ]
                    }.subscribe({ Map map ->
                        SessionUtil.setFlash(request, FlashMessage.success(
                                Messages.CELLARED_DRINK_SAVED,
                                new FlashMessage.SocialButton(
                                        String.format(Messages.CELLARED_DRINK_SAVED_SOCIAL, map.drink, map.org),
                                        "/cellars/${slug}"
                                )))
                        redirect('/yourcellar')
                    })
                } else {
                    List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
                    SessionUtil.setFlash(request, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages))
                    redirect('/yourcellar')
                }
            }

            post('cellars/:slug/drinks/:drinkId') {
                String slug = pathTokens['slug']
                Long drinkId = Long.valueOf(pathTokens['drinkId'])

                cellaredDrinkService.findById(slug, drinkId).single().subscribe { drink ->
                    if (drink) {
                        CellaredDrink editedDrink = applyForm(drink, parse(Form))

                        Validator validator = validatorFactory.validator
                        Set<ConstraintViolation<CellaredDrink>> drinkViolations = validator.validate(editedDrink)
                        if (drinkViolations.empty) {
                            cellaredDrinkService.save(editedDrink).single().subscribe { CellaredDrink savedDrink ->
                                SessionUtil.setFlash(request, FlashMessage.success(Messages.CELLARED_DRINK_SAVED))
                                redirect('/yourcellar')
                            }
                        } else {
                            List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
                            SessionUtil.setFlash(request, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages))
                            redirect("${request.uri}/edit")
                        }
                    } else {
                        clientError 404
                    }
                }
            }

            get('cellars/:slug/drinks/:drinkId/edit') {
                String slug = pathTokens['slug']
                Long drinkId = Long.valueOf(pathTokens['drinkId'])

                cellaredDrinkService.findByIdForEdit(slug, drinkId).single().subscribe { CellaredDrinkDetails drink ->

                    if (drink) {
                        requireSelf(context, drink) {
                            render handlebarsTemplate('cellars/edit-cellared-drink.html',
                                    [action       : request.uri.replace('/edit', ''),
                                     cellaredDrink: drink,
                                     title        : 'CellarHQ : Edit Cellared Drink',
                                     pageId       : 'cellared-drink.edit'])
                        }
                    }
                }

            }

        }
    }

    private CellaredDrink applyForm(CellaredDrink cellaredDrink, Form form) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern('yyyy-MM-dd')
        return cellaredDrink.with { CellaredDrink self ->
            size = form.size
            quantity = Long.valueOf(form.quantity)
            notes = form.notes
            binIdentifier = form.binIdentifier

            tradeable = form.tradeable == 'on'

            if (form.bottleDate) {
                bottleDate = LocalDate.parse(form.bottleDate, dateFormat)
            }

            if (form.drinkByDate) {
                drinkByDate = LocalDate.parse(form.drinkByDate, dateFormat)
            }

            if (form.numTradeable) {
                numTradeable = Short.valueOf(form.numTradeable)
            } else {
                numTradeable = 0
            }

            if (form.dateAcquired) {
                dateAcquired = LocalDate.parse(form.dateAcquired)
            }

            return self
        }
    }

    void requireSelf(Context context, CellaredDrink cellaredDrink, Closure operation) {
        context.with {
            Long cellarId = (Long) request.get(SessionStorage).get(SecurityModule.SESSION_CELLAR_ID)
            boolean isSelf = cellaredDrink.cellarId == cellarId

            if (isSelf) {
                operation()
            } else {
                SessionUtil.setFlash(request, FlashMessage.warning(Messages.UNAUTHORIZED_ERROR))
                redirect('/yourcellar')
                return
            }
        }
    }

}
