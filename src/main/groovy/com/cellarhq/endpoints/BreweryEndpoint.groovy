package com.cellarhq.endpoints

import com.cellarhq.Messages
import com.cellarhq.domain.Organization
import com.cellarhq.domain.OrganizationType
import com.cellarhq.services.OrganizationService
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

                    rx.Observable<Integer> totalCount = organizationService.count().single()
                    rx.Observable organizations = searchTerm ?
                            organizationService.search(searchTerm, pageSize, offset).toList() :
                            organizationService.all(pageSize, offset).toList()

                    rx.Observable.zip(organizations, totalCount) { List list, Integer count ->
                        [
                                organizations: list,
                                totalCount   : count
                        ]
                    }.subscribe({ Map map ->
                        Integer pageCount = (map.totalCount / pageSize) + (map.totalCount % pageSize)

                        render handlebarsTemplate('breweries/list.html',
                                organizations: map.organizations,
                                currentPage: requestedPage,
                                totlalPageCount: pageCount,
                                title: 'CellarHQ : Breweries',
                                pageId: 'breweries.list',
                                loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile)))
                    }, {
                        clientError 500

                    })
                }

                /**
                 * Add a new brewery.
                 */
                post {
                    Form form = parse(Form)

                    Organization organization = new Organization().with { Organization self ->
                        type = OrganizationType.BREWERY
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

                        self
                    }

                    Validator validator = validatorFactory.validator
                    Set<ConstraintViolation<Organization>> organizationViolations = validator.validate(organization)

                    if (organizationViolations.empty) {
                        organizationService.save(organization).single().subscribe { Organization savedOrganization ->
                            redirect("/breweries/${savedOrganization.slug}")
                        }
                    } else {
                        List<String> messages = new ValidationErrorMapper().buildMessages(organizationViolations)

                        SessionUtil.setFlashMessages(request, messages)

                        redirect('/breweries/add?error=' + Messages.FORM_VALIDATION_ERROR)

                    }


                }
            }
        }

        //TODO: Must be authenticated for this page
        /**
         * HTML page for adding a new brewery.
         */
        get('breweries/add') { OrganizationService organizationService ->
            render handlebarsTemplate('breweries/new.html',
                    organization: new Organization(),
                    title: 'CellarHQ : Add New Brewery',
                    pageId: 'breweries.new',
                    error: request.queryParams.error ?: '',
                    errorMessages: SessionUtil.getFlashMessages(request).collect { [message: it] },
                    loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile)))

        }

        /**
         * HTML page for editing breweries.
         */
        get('breweries/:slug/edit') { OrganizationService organizationService ->
            String slug = pathTokens['slug']
            organizationService.findBySlug(slug).single().subscribe { Organization organization ->
                if (organization.editable) {

                    render handlebarsTemplate('breweries/edit.html',
                            organization: organization,
                            title: "CellarHQ : Edit ${organization.name}",
                            pageId: 'breweries.show',
                            loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile)))
                } else {
                    clientError 403
                }
            }
        }

        handler('breweries/:slug') { OrganizationService organizationService ->
            byMethod {
                /**
                 * Get an existing brewery.
                 */
                get {
                    String slug = pathTokens['slug']
                    organizationService.findBySlug(slug).single().subscribe { Organization organization ->
                        render handlebarsTemplate('breweries/show.html',
                                organization: organization,
                                title: "CellarHQ : ${organization.name}",
                                pageId: 'breweries.show',
                                loggedIn: SessionUtil.isLoggedIn(request.maybeGet(CommonProfile)))
                    }
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

                                foundOrganization.with { Organization self ->
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

                                    self
                                }

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

                /**
                 * Delete an existing brewery.
                 */
                delete {}
            }
        }

    }
}
