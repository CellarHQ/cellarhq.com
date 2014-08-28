package com.cellarhq.endpoints

import com.cellarhq.domain.Organization
import com.cellarhq.services.OrganizationService
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.groovy.handling.GroovyChainAction

import static ratpack.jackson.Jackson.fromJson
import static ratpack.jackson.Jackson.json

/**
 * @todo Move to api package.
 */
@SuppressWarnings('AbcMetric')
@Slf4j
class OrganizationEndpoint extends GroovyChainAction {

    OrganizationService organizationService

    @Inject
    OrganizationEndpoint(OrganizationService organizationService) {
        this.organizationService = organizationService
    }

    @Override
    protected void execute() throws Exception {
        get('organizations') { OrganizationService organizationService ->
                organizationService.all().toList().subscribe { List<Organization> organizations ->
                    render json(organizations)
                }
        }

        get('organizations/valid-name') {
            organizationService.search(request.queryParams.name, 1, 0).toList().subscribe { List<Organization> orgs ->
                render json(orgs.empty)
            }
        }
        put('organizations/live-search') {
            organizationService.search(request.queryParams.name, 5, 0).toList().subscribe { List<Organization> orgs ->
                render json(orgs.collect {
                    [
                            id: it.id,
                            name: it.name
                    ]
                })
            }
        }

        handler('organizations/:slug') {

            String slug = pathTokens['slug']

            byMethod {
                get {
                    organizationService.findBySlug(slug).single().subscribe { Organization org ->
                        if (org == null) {
                            clientError 404
                        } else {
                            render json(org)
                        }
                    }
                }
                post {
                    organizationService.save(parse(fromJson(Organization))
                    ).single().flatMap {
                        organizationService.findBySlug(it.slug).single()
                    } subscribe { Organization createdOrganization ->
                        render json(createdOrganization)
                    }

                }
                put {
                    organizationService.save(parse(fromJson(Organization))
                    ).single().flatMap {
                        organizationService.findBySlug(it.slug).single()
                    } subscribe { Organization createdOrganization ->
                        render json(createdOrganization)
                    }

                }
                delete {
                    organizationService.delete(slug).subscribe {
                        response.send()
                    }


                }
            }
        }
    }
}
