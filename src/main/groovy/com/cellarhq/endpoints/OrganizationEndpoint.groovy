package com.cellarhq.endpoints

import com.cellarhq.domain.jooq.Organization
import com.cellarhq.services.OrganizationService
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

import static ratpack.jackson.Jackson.fromJson
import static ratpack.jackson.Jackson.json

@Slf4j
class OrganizationEndpoint extends GroovyHandler {

    OrganizationService organizationService

    @Inject
    public OrganizationEndpoint(OrganizationService organizationService) {
        this.organizationService = organizationService
    }

    @Override
    protected void handle(GroovyContext context) {
        context.with {
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
