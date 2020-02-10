package com.cellarhq.api.services

import com.cellarhq.domain.CellaredDrink
import com.cellarhq.domain.views.CellaredDrinkDetails
import com.cellarhq.generated.Keys
import com.cellarhq.generated.tables.records.CellaredDrinkRecord
import com.cellarhq.jooq.BaseJooqService
import com.cellarhq.jooq.CellarStatsUpdater
import com.cellarhq.jooq.CustomViewRecordMapperProvider
import com.cellarhq.jooq.SortCommand
import com.cellarhq.util.JooqUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.SelectConditionStep
import org.jooq.SelectJoinStep
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

import javax.sql.DataSource
import java.time.LocalDateTime

import static com.cellarhq.generated.Tables.*

@Slf4j
class CellaredDrinkService extends BaseJooqService {

  @Inject
  CellaredDrinkService(DataSource dataSource) {
    super(dataSource)
  }

  Promise<CellaredDrink> save(CellaredDrink cellaredDrink) {
    Blocking.get {
      jooq { DSLContext create ->
        CellaredDrinkRecord drinkRecord = create.newRecord(CELLARED_DRINK, cellaredDrink)

        if (drinkRecord.id) {
          drinkRecord.update()
        } else {
          drinkRecord.reset(CELLARED_DRINK.ID)
          drinkRecord.store()
        }

        if (drinkRecord) {
          new CellarStatsUpdater().updateAllCounts(drinkRecord.cellarId, drinkRecord.drinkId, create)
          return drinkRecord.into(CellaredDrink)
        }

        return null

      }
    }
  }

  Promise<CellaredDrink> findById(Long id) {
    Blocking.get {
      jooq { DSLContext create ->
        create.select()
          .from(CELLARED_DRINK)
          .where(CELLARED_DRINK.ID.eq(id))
          .fetchOneInto(CellaredDrink)
      }
    }
  }

  Promise<CellaredDrinkDetails> findByIdForEdit(String cellarSlug, Long id) {
    Blocking.get {
      jooq({ Configuration c ->
        c.set(new CustomViewRecordMapperProvider([
          organizationName: String,
          drinkName       : String
        ]))
      }) { DSLContext create ->
        create.select(JooqUtil.andFields(
          CELLARED_DRINK.fields(),
          ORGANIZATION.NAME.as('organizationName'),
          DRINK.NAME.as('drinkName')
        ))
          .from(CELLARED_DRINK)
          .join(CELLAR).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_CELLAR_ID)
          .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
          .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
          .where(CELLARED_DRINK.ID.eq(id)
          .and(CELLAR.SLUG.eq(cellarSlug)))
          .fetchOneInto(CellaredDrinkDetails)
      }
    }
  }

  Promise<List<CellaredDrinkDetails>> all(Long id, SortCommand sortCommand) {
    Blocking.get {
      allNonZeroQuery(sortCommand) { SelectConditionStep step ->
        step.and(CELLAR.ID.eq(id))
      }
    }
  }

  Promise<List<CellaredDrinkDetails>> all(String cellarSlug, SortCommand sortCommand) {
    Blocking.get {
      allNonZeroQuery(sortCommand) { SelectConditionStep step ->
        step.and(CELLAR.SLUG.equalIgnoreCase(cellarSlug))
      }
    }
  }

  Promise<String> csv(String cellarSlug, SortCommand sortCommand) {
    Blocking.get {
      jooq { DSLContext create ->
       create.select(
         ORGANIZATION.NAME.as('Brewery'),
         DRINK.NAME.as('Beer'),
         STYLE.NAME.as('Style'),
         CELLARED_DRINK.SIZE,
         CELLARED_DRINK.QUANTITY,
         CELLARED_DRINK.NUM_TRADEABLE,
         CELLARED_DRINK.DATE_ACQUIRED,
         CELLARED_DRINK.BOTTLE_DATE,
         CELLARED_DRINK.DRINK_BY_DATE,
         CELLARED_DRINK.BIN_IDENTIFIER,
         CELLARED_DRINK.NOTES)
          .from(CELLARED_DRINK)
          .join(CELLAR).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_CELLAR_ID)
          .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
          .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
          .leftOuterJoin(STYLE).onKey(Keys.DRINK__FK_DRINK_STYLE_ID)
          .where(CELLARED_DRINK.QUANTITY.greaterThan(0)).and(CELLAR.SLUG.equalIgnoreCase(cellarSlug))
          .orderBy(makeSortField(sortCommand, ORGANIZATION.NAME, [
          beerName   : DRINK.NAME,
          breweryName: ORGANIZATION.NAME,
          size       : CELLARED_DRINK.SIZE,
          quantity   : CELLARED_DRINK.QUANTITY,
          bottleDate : CELLARED_DRINK.BOTTLE_DATE,
          style      : STYLE.NAME
        ]), DRINK.NAME.asc(), CELLARED_DRINK.BOTTLE_DATE.asc(), CELLARED_DRINK.SIZE.asc())
          .fetch()
          .formatCSV()
      }
    }
  }


  Promise<List<CellaredDrinkDetails>> archive(Long id, SortCommand sortCommand) {
    Blocking.get {
      allQuery(sortCommand) { SelectJoinStep step ->
        step.where(CELLAR.ID.eq(id)).and(CELLARED_DRINK.QUANTITY.equal(0))
      }
    }
  }

  Promise<List<CellaredDrinkDetails>> archive(String cellarSlug, SortCommand sortCommand) {
    Blocking.get {
      allQuery(sortCommand) { SelectJoinStep step ->
        step.where(CELLAR.SLUG.equalIgnoreCase(cellarSlug)).and(CELLARED_DRINK.QUANTITY.equal(0))
      }
    }
  }

  Operation delete(Long id) {
    Blocking.op {
      jooq { DSLContext create ->
        CellaredDrinkRecord drink = create
          .select()
          .from(CELLARED_DRINK)
          .where(CELLARED_DRINK.ID.eq(id))
          .fetchOneInto(CellaredDrinkRecord)

        if (drink) {
          drink.delete()
          new CellarStatsUpdater().updateAllCounts(drink.cellarId, drink.drinkId, create)
        }
      }
    }
  }

  Promise<CellaredDrink> drink(String cellarSlug, Long id) {
    Blocking.get {
      jooq { DSLContext create ->
        CellaredDrinkRecord drink = create
          .select()
          .from(CELLARED_DRINK)
          .join(CELLAR).onKey()
          .where(CELLAR.SLUG.eq(cellarSlug))
          .and(CELLARED_DRINK.ID.eq(id))
          .fetchOneInto(CellaredDrinkRecord)

        if (drink && drink.quantity > 0) {
          create.update(CELLARED_DRINK)
            .set(CELLARED_DRINK.QUANTITY, CELLARED_DRINK.QUANTITY.minus(1))
            .set(CELLARED_DRINK.MODIFIED_DATE, LocalDateTime.now())
            .where(CELLARED_DRINK.ID.eq(id))
            .execute()

          new CellarStatsUpdater().updateAllCounts(drink.cellarId, drink.drinkId, create)

          drink.refresh(CELLARED_DRINK.QUANTITY, CELLARED_DRINK.MODIFIED_DATE)
          return drink.into(CellaredDrink)
        }

        return null
      }
    }
  }

  /**
   * Finds any cellared drink that has been marked as tradable by the cellar owner.
   *
   * @param organizationSlug
   * @param drinkSlug
   * @param sortCommand
   * @return
   */
  Promise<List<CellaredDrinkDetails>> findTradeableCellaredDrinks(String organizationSlug,
                                                               String drinkSlug,
                                                               SortCommand sortCommand) {
    Blocking.get {
      allQuery(sortCommand) { SelectJoinStep step ->
        step.where(DRINK.SLUG.eq(drinkSlug))
          .and(ORGANIZATION.SLUG.eq(organizationSlug))
          .and(CELLARED_DRINK.TRADEABLE.isTrue())
      }
    }
  }

  private List<CellaredDrinkDetails> allNonZeroQuery(SortCommand sortCommand, Closure<SelectConditionStep> criteria) {
    allQuery(sortCommand) { SelectJoinStep step ->
      criteria(step.where(CELLARED_DRINK.QUANTITY.greaterThan(0)))
    }
  }

  private List<CellaredDrinkDetails> allQuery(SortCommand sortCommand, Closure<SelectConditionStep> criteria) {
    jooq({ Configuration c ->
      c.set(new CustomViewRecordMapperProvider([
        organizationSlug: String,
        organizationName: String,
        drinkSlug       : String,
        drinkName       : String,
        styleName       : String,
        cellarSlug      : String,
        cellarName      : String
      ]))
    }) { DSLContext create ->
      SelectJoinStep selectStep = create.select(JooqUtil.andFields(
        CELLARED_DRINK.fields(),
        ORGANIZATION.SLUG.as('organizationSlug'),
        ORGANIZATION.NAME.as('organizationName'),
        DRINK.SLUG.as('drinkSlug'),
        DRINK.NAME.as('drinkName'),
        STYLE.NAME.as('styleName'),
        CELLAR.SLUG.as('cellarSlug'),
        CELLAR.SCREEN_NAME.as('cellarName')
      ))
        .from(CELLARED_DRINK)
        .join(CELLAR).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_CELLAR_ID)
        .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
        .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
        .leftOuterJoin(STYLE).onKey(Keys.DRINK__FK_DRINK_STYLE_ID)

      criteria(selectStep)
        .orderBy(makeSortField(sortCommand, ORGANIZATION.NAME, [
        beerName   : DRINK.NAME,
        breweryName: ORGANIZATION.NAME,
        size       : CELLARED_DRINK.SIZE,
        quantity   : CELLARED_DRINK.QUANTITY,
        bottleDate : CELLARED_DRINK.BOTTLE_DATE,
        style      : STYLE.NAME,
        binIdentifier : CELLARED_DRINK.BIN_IDENTIFIER
      ]), DRINK.NAME.asc(), CELLARED_DRINK.BOTTLE_DATE.asc(), CELLARED_DRINK.SIZE.asc())
        .fetchInto(CellaredDrinkDetails)
    }
  }
}
