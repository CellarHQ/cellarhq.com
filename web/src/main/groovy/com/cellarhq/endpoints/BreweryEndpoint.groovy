package com.cellarhq.endpoints

import com.cellarhq.Messages
import com.cellarhq.domain.views.DrinkSearchDisplay
import com.cellarhq.domain.Organization
import com.cellarhq.domain.OrganizationType
import com.cellarhq.services.DrinkService
import com.cellarhq.services.OrganizationService
import com.cellarhq.session.FlashMessage
import com.cellarhq.util.SessionUtil
import com.cellarhq.validation.ValidationErrorMapper
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.CommonProfile
import ratpack.form.Form
import ratpack.groovy.handling.GroovyChainAction

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static ratpack.handlebars.Template.handlebarsTemplate

@SuppressWarnings('MethodSize')
@Slf4j
class BreweryEndpoint extends GroovyChainAction {
    ValidatorFactory validatorFactory
    OrganizationService organizationService

    @Inject
    public BreweryEndpoint(ValidatorFactory validatorFactory, OrganizationService organizationService) {
        this.validatorFactory = validatorFactory
        this.organizationService = organizationService
    }

    @Override
    protected void execute() throws Exception {
        handler('breweries') { OrganizationService organizationService ->
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
                        organizationService.searchCount(searchTerm).single() :
                        organizationService.count().single()

                    rx.Observable organizations = searchTerm ?
                            organizationService.search(searchTerm, pageSize, offset).toList() :
                            organizationService.all(pageSize, offset).toList()

                    rx.Observable.zip(organizations, totalCount) { List list, Integer count ->
                        [
                                organizations: list,
                                totalCount   : count
                        ]
                    }.subscribe({ Map map ->
                        Integer pageCount = (map.totalCount / pageSize)
                        Boolean shouldShowPagination = pageCount != 0

                        render handlebarsTemplate('breweries/list.html',
                                [organizations: map.organizations,
                                currentPage: requestedPage,
                                totalPageCount: pageCount,
                                shouldShowPagination: shouldShowPagination,
                                title: 'CellarHQ : Breweries',
                                pageId: 'breweries.list',
                                loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile))])
                    }, {
                        clientError 500
                    })
                }

                /**
                 * Add a new brewery.
                 */
                post {
                    Form form = parse(Form)

                    Organization organization = updateOrganizationFromForm(new Organization(), form)

                    Validator validator = validatorFactory.validator
                    Set<ConstraintViolation<Organization>> organizationViolations = validator.validate(organization)

                    if (organizationViolations.empty) {
                        organizationService.save(organization).single().subscribe { Organization savedOrganization ->
                            redirect("/breweries/${savedOrganization.slug}")
                        }
                    } else {
                        List<String> messages = new ValidationErrorMapper().buildMessages(organizationViolations)

                        SessionUtil.setFlash(request, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages))

                        redirect('/breweries/add?error=' + Messages.FORM_VALIDATION_ERROR)
                    }
                }
            }
        }

        /**
         * HTML page for adding a new brewery.
         */
        get('breweries/add') { OrganizationService organizationService ->
            render handlebarsTemplate('breweries/new.html',
                    [organization: new Organization(),
                    title: 'CellarHQ : Add New Brewery',
                    pageId: 'breweries.new',
                    error: request.queryParams.error ?: '',
                    errorMessages: SessionUtil.getFlashMessages(request).collect { [message: it] },
                    loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile))])

        }

        /**
         * HTML page for editing breweries.
         */
        get('breweries/:slug/edit') { OrganizationService organizationService ->
            String slug = pathTokens['slug']
            organizationService.findBySlug(slug).single().subscribe { Organization organization ->
                if (organization.editable) {
                    render handlebarsTemplate('breweries/edit.html',
                            [organization: organization,
                            title: "CellarHQ : Edit ${organization.name}",
                            pageId: 'breweries.show',
                            loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile))])
                } else {
                    clientError 403
                }
            }
        }

        handler('breweries/:slug') { OrganizationService organizationService, DrinkService drinkService ->
            byMethod {
                /**
                 * Get an existing brewery.
                 */
                get {
                    String slug = pathTokens['slug']

                    rx.Observable<Organization> organizationObservable =
                        organizationService.findBySlug(slug).single()
                    rx.Observable<DrinkSearchDisplay> drinkObservable =
                        drinkService.findByOrganizationSlug(slug).toList()

                    rx.Observable.zip(organizationObservable, drinkObservable) { Organization org, List drinks ->
                        [
                            organization: org,
                            drinks   : drinks
                        ]
                    }.subscribe({ Map map ->
                        render handlebarsTemplate('breweries/show.html',
                            [organization: map.organization,
                            title: "CellarHQ : ${map.organization.name}",
                            drinks: map.drinks,
                            numberOfDrinks: map.drinks.size(),
                            pageId: 'breweries.show',
                            loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile))])
                    })
                }

                /**
                 * Update an existing brewery
                 */
                post {
                    String slug = pathTokens['slug']
                    Form form = parse(Form)

                    organizationService.findBySlug(slug).single().subscribe { Organization foundOrganization ->
                        if (foundOrganization) {
                            if (foundOrganization.editable) {
                                updateOrganizationFromForm(foundOrganization, form)

                                organizationService.save(foundOrganization)
                                    .single()
                                    .subscribe { Organization savedOrganization ->
                                    redirect("/breweries/${savedOrganization.slug}")
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

    private Organization updateOrganizationFromForm(Organization organization, Form form) {
        organization.with {
            slug = form.name
            name = form.name
            description = form.description
            established = Short.parseShort(form.established)
            phone = form.phone
            website = form.website
            address = form.address
            address2 = form.address2
            locality = form.city
            postalCode = form.postalCode
            country = form.country
            searchable = true
            locked = false
            needsModeration = false
            type = OrganizationType.BREWERY
        }


        return organization
    }
}
