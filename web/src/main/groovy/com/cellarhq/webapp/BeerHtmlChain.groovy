package com.cellarhq.webapp

import com.cellarhq.api.services.CellaredDrinkService
import com.cellarhq.api.services.DrinkService
import com.cellarhq.api.services.OrganizationService
import com.cellarhq.api.services.StyleService
import com.cellarhq.auth.services.photo.PhotoService
import com.cellarhq.common.Messages
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.common.validation.ValidationErrorMapper
import com.cellarhq.domain.Availability
import com.cellarhq.domain.Drink
import com.cellarhq.domain.DrinkType
import com.cellarhq.domain.Organization
import com.cellarhq.domain.Photo
import com.cellarhq.domain.views.CellaredDrinkDetails
import com.cellarhq.jooq.SortCommand
import com.cellarhq.util.SessionUtil
import com.cellarhq.webapp.handlers.AddBeersHtmlHandler
import com.cellarhq.webapp.handlers.BeersHtmlHandler
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.exec.Operation
import ratpack.form.Form
import ratpack.func.Pair
import ratpack.groovy.handling.GroovyChainAction

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static com.cellarhq.webapp.DrinkFormMapper.applyFrom
import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class BeerHtmlChain extends GroovyChainAction {

  ValidatorFactory validatorFactory
  DrinkService drinkService
  StyleService styleService
  OrganizationService organizationService
  PhotoService photoService
  CellaredDrinkService cellaredDrinkService

  @Inject
  BeerHtmlChain(ValidatorFactory validatorFactory,
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
  void execute() throws Exception {
    path('beers', BeersHtmlHandler)
    get('beers/add', AddBeersHtmlHandler)


    get('breweries/:brewery/beers/add') {
      organizationService.findBySlug(pathTokens['brewery']).then { Organization org ->
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
      }
    }


    get('breweries/:brewery/beers/:slug/edit') {
      String slug = pathTokens['slug']
      String brewery = pathTokens['brewery']

      drinkService.findBySlug(brewery, slug).then { Drink drink ->
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
      }
    }

    post('breweries/:brewery/beers') {
      parse(Form).then { Form form ->

        Drink drink
        try {
          drink = applyFrom(new Drink(), form)
        } catch (NumberFormatException e) {
          SessionUtil.setFlash(context, FlashMessage.warning(Messages.FORM_VALIDATION_ERROR)).then {
            return redirect("breweries/${pathTokens['brewery']}/beers/add")
          }
        }

        Validator validator = validatorFactory.validator
        Set<ConstraintViolation<Drink>> drinkViolations = validator.validate(drink)

        if (drinkViolations.empty) {
          organizationService.findBySlug(pathTokens['brewery']).then { Organization org ->
            drink.organizationId = org.id

            drinkService.save(drink).then({ Drink savedDrink ->
              redirect("/breweries/${pathTokens['brewery']}/beers/${savedDrink.slug}")
            })
          }
        } else {
          List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
          SessionUtil.setFlash(context, FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages)).then {
            redirect("/breweries/${pathTokens['brewery']}/beers/add")
          }
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

          photoService.findByOrganizationAndDrink(brewery, slug).flatRight {
            drinkService.findBySlug(brewery, slug)
          }.flatRight {
            cellaredDrinkService.findTradeableCellaredDrinks(brewery, slug, SortCommand.fromRequest(request))
          }.then { Pair<Pair<Photo, Drink>, List<CellaredDrinkDetails>> pair ->
            if (pair.left.right) {
              render handlebarsTemplate('beer/show-beer.html',
                [drink         : pair.left.right,
                 photo         : pair.left.left,
                 tradableDrinks: pair.right,
                 title         : "CellarHQ : ${pair.left.right.name}",
                 pageId        : 'beer.show'])
            } else {
              clientError 404
            }
          }
        }

        /**
         * Edit an existing beer.
         */
        post {
          String slug = pathTokens['slug']
          String brewery = pathTokens['brewery']
          parse(Form).then { Form form ->

            drinkService.findBySlug(brewery, slug).then { Drink drink ->
              if (drink) {
                if (drink.editable) {
                  applyFrom(drink, form)

                  Validator validator = validatorFactory.validator
                  Set<ConstraintViolation<Drink>> drinkViolations = validator.validate(drink)

                  if (drinkViolations.empty) {
                    drinkService.save(drink).then { Drink savedDrink ->

                      SessionUtil.setFlash(context, FlashMessage.success(Messages.BEER_EDIT_SAVED)).then {
                        redirect("/breweries/${pathTokens['brewery']}/beers/${savedDrink.slug}")
                      }
                    }
                  } else {
                    List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
                    SessionUtil.setFlash(context,
                      FlashMessage.error(Messages.FORM_VALIDATION_ERROR, messages)
                    ).then {
                      redirect("/breweries/${pathTokens['brewery']}/beers/${slug}/edit")
                    }
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
