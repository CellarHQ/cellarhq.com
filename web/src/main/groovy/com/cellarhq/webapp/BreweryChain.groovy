package com.cellarhq.webapp

import com.cellarhq.api.services.DrinkService
import com.cellarhq.api.services.OrganizationService
import com.cellarhq.auth.services.photo.PhotoService
import com.cellarhq.common.validation.ValidationErrorMapper
import com.cellarhq.domain.Organization
import com.cellarhq.domain.OrganizationType
import com.cellarhq.domain.Photo
import com.cellarhq.domain.views.DrinkSearchDisplay
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import ratpack.exec.Promise
import ratpack.form.Form
import ratpack.func.Pair
import ratpack.groovy.handling.GroovyChainAction

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static com.cellarhq.common.Messages.FORM_VALIDATION_ERROR
import static com.cellarhq.common.session.FlashMessage.error
import static com.cellarhq.jooq.SortCommand.fromRequest
import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class BreweryChain extends GroovyChainAction {
  ValidatorFactory validatorFactory
  OrganizationService organizationService
  DrinkService drinkService
  PhotoService photoService

  @Inject
  BreweryChain(ValidatorFactory validatorFactory,
                      OrganizationService organizationService,
                      DrinkService drinkService,
                      PhotoService photoService) {

    this.validatorFactory = validatorFactory
    this.organizationService = organizationService
    this.drinkService = drinkService
    this.photoService = photoService
  }

  @Override
  void execute() throws Exception {
    path('breweries') {
      byMethod {
        /**
         * List all breweries; has search.
         */
        get {
          Integer requestedPage = request.queryParams.page?.toInteger() ?: 1
          Integer pageSize = 20
          Integer offset = (requestedPage - 1) * pageSize
          String searchTerm = request.queryParams.search

          Promise<Integer> totalCountPromise = searchTerm ?
            organizationService.searchCount(searchTerm) :
            organizationService.count()

          totalCountPromise.flatRight {
            searchTerm ?
              organizationService.search(
                searchTerm, fromRequest(request), pageSize, offset) :
              organizationService.all(fromRequest(request), pageSize, offset)
          }.then { Pair<Integer, List<Organization>> pair ->
            Integer pageCount = (pair.left / pageSize).toInteger()
            Boolean shouldShowPagination = pageCount != 0

            render handlebarsTemplate('breweries/list-brewery.html',
              [organizations       : pair.right,
               currentPage         : requestedPage,
               totalPageCount      : pageCount,
               shouldShowPagination: shouldShowPagination,
               title               : 'CellarHQ : Breweries',
               pageId              : 'breweries.list'])
          }
        }

        /**
         * Add a new brewery.
         */
        post {
          parse(Form).then { Form form ->

            Organization organization = updateOrganizationFromForm(new Organization(), form)

            Validator validator = validatorFactory.validator
            Set<ConstraintViolation<Organization>> organizationViolations =
              validator.validate(organization)

            if (organizationViolations.empty) {
              organizationService.save(organization).then { Organization savedOrganization ->
                redirect("/breweries/${savedOrganization.slug}")
              }
            } else {
              List<String> messages = new ValidationErrorMapper().buildMessages(organizationViolations)

              SessionUtil.setFlash(context, error(FORM_VALIDATION_ERROR, messages)).then {
                redirect('/breweries/add')
              }
            }
          }
        }
      }
    }

    /**
     * HTML page for adding a new brewery.
     */
    get('breweries/add') {
      render handlebarsTemplate('breweries/new-brewery.html',
        [organization: new Organization(),
         title       : 'CellarHQ : Add New Brewery',
         pageId      : 'breweries.new'])

    }

    /**
     * HTML page for editing breweries.
     */
    get('breweries/:slug/edit') {
      String slug = pathTokens['slug']
      organizationService.findBySlug(slug).then { Organization organization ->
        if (organization) {
          if (organization.editable) {
            render handlebarsTemplate('breweries/edit-brewery.html',
              [organization: organization,
               title       : "CellarHQ : Edit ${organization.name}",
               pageId      : 'breweries.edit'])
          } else {
            clientError 403
          }
        } else {
          clientError 404
        }
      }
    }

    path('breweries/:slug') {
      byMethod {
        get {
          String slug = pathTokens['slug']

          organizationService.findBySlug(slug).flatRight {
            drinkService.findByOrganizationSlug(slug)
          }.flatRight {
            photoService.findByOrganization(slug)
          }.then { Pair<Pair<Organization, List<DrinkSearchDisplay>>, Photo> pair ->
            if (pair.left.left) {
              render handlebarsTemplate('breweries/show-brewery.html',
                [
                  organization  : pair.left.left,
                  title         : "CellarHQ : ${pair.left.left.name}",
                  drinks        : pair.left.right,
                  numberOfDrinks: pair.left.right.size() ?: 0,
                  photo         : pair.right,
                  pageId        : 'breweries.show'
                ])
            } else {
              clientError 404
            }
          }
        }

        /**
         * Update an existing brewery
         */
        post {
          String slug = pathTokens['slug']
          parse(Form).then { Form form ->

            organizationService.findBySlug(slug).then { Organization foundOrganization ->
              if (foundOrganization) {
                if (foundOrganization.editable) {
                  updateOrganizationFromForm(foundOrganization, form)

                  organizationService.save(foundOrganization).then { Organization savedOrganization ->
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
  }

  private static Organization updateOrganizationFromForm(Organization organization, Form form) {
    organization.with {
      slug = organization.slug ?: form.name
      name = organization.name ?: form.name
      collaboration = form.collaboration ?: false
      description = form.description
      established = form.established ? Short.parseShort(form.established) : null
      phone = form.phone
      website = form.website
      address = form.address
      address2 = form.address2
      locality = form.city
      postalCode = form.postalCode
      country = form.country
      locked = false
      needsModeration = false
      warningFlag = false
      totalBeers = 0
      cellaredBeers = 0
      containedInCellars = 0
      type = OrganizationType.BREWERY
    }


    return organization
  }
}
