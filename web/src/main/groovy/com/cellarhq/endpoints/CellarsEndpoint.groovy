package com.cellarhq.endpoints

import com.cellarhq.Messages
import com.cellarhq.auth.AccessControlException
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.services.CellarService
import com.cellarhq.services.CellaredDrinkService
import com.cellarhq.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.cellarhq.validation.ValidationErrorMapper
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.UserProfile
import ratpack.form.Form
import ratpack.groovy.handling.GroovyChainAction

import static ratpack.handlebars.Template.handlebarsTemplate

import ratpack.session.store.SessionStorage

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressWarnings('MethodSize')
@Slf4j
class CellarsEndpoint extends GroovyChainAction {

    ValidatorFactory validatorFactory
    CellarService cellarService
    CellaredDrinkService cellaredDrinkService

    @Inject
    CellarsEndpoint(ValidatorFactory validatorFactory,
                    CellarService cellarService,
                    CellaredDrinkService cellaredDrinkService) {
        this.validatorFactory = validatorFactory
        this.cellarService = cellarService
        this.cellaredDrinkService = cellaredDrinkService
    }

    @Override
    protected void execute() throws Exception {
        get('cellars') {
            Integer requestedPage = request.queryParams.page?.toInteger() ?: 1
            Integer pageSize = 20
            Integer offset = (requestedPage - 1) * pageSize
            String searchTerm = request.queryParams.search

            rx.Observable<Integer> totalCount = cellarService.count(searchTerm).single()
            rx.Observable cellars = searchTerm ?
                    cellarService.search(searchTerm, pageSize, offset).toList() :
                    cellarService.all(pageSize, offset).toList()

            rx.Observable.zip(cellars, totalCount) { List list, Integer count ->
                [
                        cellars: list,
                        totalCount: count
                ]
            }.subscribe({ Map map ->
                Integer pageCount = (map.totalCount / pageSize) + (map.totalCount % pageSize)

                render handlebarsTemplate('cellars/list.html',
                        cellars: map.cellars,
                        currentPage: requestedPage,
                        totalPageCount: pageCount,
                        title: 'CellarHQ : Cellars',
                        pageId: 'cellars.list')
            }, {
                clientError 500
            })
        }

        get('cellars/:slug') {
            String slug = pathTokens['slug']
            boolean isSelf = request.maybeGet(UserProfile)?.username == slug

            rx.Observable.zip(
                    cellarService.findBySlug(slug).single(),
                    cellaredDrinkService.all(slug).toList()
            ) { Cellar cellar, List cellaredDrinks ->
                [
                        cellar: cellar,
                        cellaredDrinks: cellaredDrinks,
                ]
            }.subscribe { Map map ->
                if (map.cellar == null) {
                    clientError 404
                } else {
                    render handlebarsTemplate('cellars/show.html',
                            cellar: map.cellar,
                            photo: map.cellar.photo,
                            cellaredDrinks: map.cellaredDrinks,
                            self: isSelf,
                            title: "CellarHQ : ${map.cellar.displayName}",
                            pageId: 'cellars.show')
                }
            }
        }

        post('cellars/:slug/drinks') {
            String slug = pathTokens['slug']
            boolean isSelf = request.maybeGet(UserProfile)?.username == slug

            if (!isSelf) {
                log.warn(LogUtil.toLog('AccessControlExceeded', [
                        subject: request.maybeGet(UserProfile)?.username,
                        accessingAs: slug,
                        action: 'POSTing to another cellar drinks'
                ]))
                throw new AccessControlException("Cannot modify other user's cellar")
            }

            Form form = parse(Form)

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern('yyyy-MM-dd')
            CellaredDrink drink = new CellaredDrink().with { CellaredDrink self ->
                cellarId = (long) request.get(SessionStorage).get(SecurityModule.SESSION_CELLAR_ID)
                drinkId = Long.valueOf(form.beerId)
                size = form.size
                quantity = Long.valueOf(form.quantity)
                notes = form.notes
                binIdentifier = form.binIdentifier
                tradeable = Boolean.valueOf(form.tradeable)

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

            Validator validator = validatorFactory.validator
            Set<ConstraintViolation<CellaredDrink>> drinkViolations = validator.validate(drink)

            String next = request.queryParams.get('next') ?: '/yourcellar'
            if (drinkViolations.empty) {
                cellaredDrinkService.save(drink).single().subscribe { CellaredDrink savedDrink ->
                    redirect(next)
                }
            } else {
                List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
                SessionUtil.setFlash(request, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages))
                redirect(next)
            }
        }
    }
}
