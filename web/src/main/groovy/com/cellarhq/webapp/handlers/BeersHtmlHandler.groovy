package com.cellarhq.webapp.handlers

import com.cellarhq.api.services.DrinkService
import com.cellarhq.api.services.OrganizationService
import com.cellarhq.common.validation.ValidationErrorMapper
import com.cellarhq.domain.Drink
import com.cellarhq.domain.Organization
import com.cellarhq.jooq.SortCommand
import ratpack.exec.Promise
import ratpack.form.Form
import ratpack.func.Block
import ratpack.func.Pair
import ratpack.handling.Context
import ratpack.handling.Handler

import javax.inject.Inject
import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static com.cellarhq.common.Messages.FORM_VALIDATION_ERROR
import static com.cellarhq.common.session.FlashMessage.error
import static com.cellarhq.common.session.FlashMessage.warning
import static com.cellarhq.util.SessionUtil.setFlash
import static com.cellarhq.webapp.DrinkFormMapper.applyFrom
import static ratpack.handlebars.Template.handlebarsTemplate

class BeersHtmlHandler implements Handler {
  DrinkService drinkService
  OrganizationService organizationService
  ValidatorFactory validatorFactory

  @Inject
  BeersHtmlHandler(DrinkService drinkService,
                   OrganizationService organizationService,
                   ValidatorFactory validatorFactory) {
    this.drinkService = drinkService
    this.organizationService = organizationService
    this.validatorFactory = validatorFactory
  }

  @Override
  void handle(Context ctx) throws Exception {
    ctx.byMethod { m ->
      m.get({
        Integer requestedPage = ctx.request.queryParams.page?.toInteger() ?: 1
        Integer pageSize = 20
        Integer offset = (requestedPage - 1) * pageSize
        String searchTerm = ctx.request.queryParams.search

        Promise<Integer> totalCountPromise = searchTerm ?
          drinkService.searchCount(searchTerm) :
          drinkService.count()


        totalCountPromise.flatRight { Integer i ->
          searchTerm ?
            drinkService.search(
              searchTerm,
              SortCommand.fromRequest(ctx.request),
              pageSize,
              offset) :
            drinkService.all(
              SortCommand.fromRequest(ctx.request),
              pageSize,
              offset)
        }.then { Pair<Integer, List<Drink>> pair ->
          Integer pageCount = (pair.left / pageSize).toInteger()
          Boolean shouldShowPagination = pageCount != 0

          ctx.render handlebarsTemplate('beer/list-beer.html',
            [drinks              : pair.right,
             currentPage         : requestedPage,
             totalPageCount      : pageCount,
             shouldShowPagination: shouldShowPagination,
             title               : 'CellarHQ : Beer',
             pageId              : 'beer.list'])
        }
      } as Block)

      .post({
        ctx.parse(Form).then { Form form ->
          Drink drink
          try {
            drink = applyFrom(new Drink(), form)
          } catch (NumberFormatException e) {
            setFlash(ctx, warning(FORM_VALIDATION_ERROR)).then {
              return ctx.redirect('/beers/add')
            }
          }

          Validator validator = validatorFactory.validator
          Set<ConstraintViolation<Drink>> drinkViolations = validator.validate(drink)

          if (drinkViolations.empty) {
            drinkService.save(drink).flatRight { d ->
              organizationService.get(drink.organizationId)
            }.then { Pair<Drink, Organization> pair ->
              ctx.redirect("/breweries/${pair.right.slug}/beers/${pair.left.slug}")
            }
          } else {
            List<String> messages = new ValidationErrorMapper().buildMessages(drinkViolations)
            setFlash(ctx, error(FORM_VALIDATION_ERROR, messages)).then {
              ctx.redirect('/beers/add')
            }
          }
        }
      } as Block)
    }
  }
}
