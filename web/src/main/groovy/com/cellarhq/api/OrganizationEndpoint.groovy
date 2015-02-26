package com.cellarhq.api

import com.cellarhq.domain.Organization
import com.cellarhq.api.services.OrganizationService
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

import static ratpack.jackson.Jackson.fromJson
import static ratpack.jackson.Jackson.json

@Slf4j
class OrganizationEndpoint implements Action<Chain> {

    OrganizationService organizationService

    @Inject
    OrganizationEndpoint(OrganizationService organizationService) {
        this.organizationService = organizationService
    }

    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            get('organizations') { OrganizationService organizationService ->
                organizationService.all().toList().subscribe { List<Organization> organizations ->
                    render json(organizations)
                }
            }

            get('organizations/valid-name') {
                organizationService.search(request.queryParams.name, null, 1, 0)
                        .toList()
                        .subscribe { List<Organization> orgs ->

                    if (request.queryParams.exists) {
                        render json(!orgs.empty)
                    } else {
                        render json(orgs.empty)
                    }
                }
            }

            get('organizations/live-search') {
                organizationService.search(request.queryParams.name, null, 20, 0)
                        .toList()
                        .subscribe { List<Organization> orgs ->

                    render json(orgs.collect {
                        [
                                id  : it.id,
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
}
