package com.cellarhq.api.services

import com.cellarhq.domain.Drink
import com.cellarhq.domain.views.DrinkSearchDisplay
import com.cellarhq.generated.Keys
import com.cellarhq.generated.tables.records.DrinkRecord
import com.cellarhq.jooq.BaseJooqService
import com.cellarhq.jooq.CustomViewRecordMapperProvider
import com.cellarhq.jooq.SortCommand
import com.cellarhq.util.JooqUtil
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.jooq.Configuration
import org.jooq.DSLContext
import ratpack.exec.Blocking
import rx.Observable

import javax.sql.DataSource

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observe
import static ratpack.rx.RxRatpack.observeEach

@Slf4j
class DrinkService extends BaseJooqService {

  @Inject
  DrinkService(DataSource dataSource) {
    super(dataSource)
  }


  Observable<Drink> search(String searchTerm, SortCommand sortCommand, int numberOfRows = 20, int offset = 0) {
    observeEach(Blocking.get {
      jooq({ Configuration c ->
        c.set(new CustomViewRecordMapperProvider([
          organizationName: String,
          organizationSlug: String,
          styleName       : String
        ]))
      }) { DSLContext create ->
        create.select(JooqUtil.andFields(
          DRINK.fields(),
          ORGANIZATION.NAME.as('organizationName'),
          ORGANIZATION.SLUG.as('organizationSlug'),
          STYLE.NAME.as('styleName')))
          .from(DRINK)
          .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
          .leftOuterJoin(STYLE).onKey(Keys.DRINK__FK_DRINK_STYLE_ID)
          .where(DRINK.NAME.likeIgnoreCase("%${searchTerm}%"))
          .orderBy(makeSortField(sortCommand, DRINK.NAME, [
          name        : DRINK.NAME,
          style       : STYLE.NAME,
          availability: DRINK.AVAILABILITY,
          ibu         : DRINK.IBU,
          abv         : DRINK.ABV
        ]))
          .limit(offset, numberOfRows)
          .fetchInto(Drink)
      }
    })
  }

  Observable<Drink> searchByOrganizationId(Long organizationId, String searchTerm, int numRows = 20, int offset = 0) {
    observeEach(Blocking.get {
      jooq { DSLContext create ->
        create.select()
          .from(DRINK)
          .where(DRINK.ORGANIZATION_ID.eq(organizationId)
          .and(DRINK.NAME.likeIgnoreCase("%${searchTerm}%")))
          .orderBy(DRINK.NAME)
          .limit(offset, numRows)
          .fetchInto(Drink)
      }
    })
  }

  Observable<DrinkSearchDisplay> findByOrganizationSlug(String slug) {
    observeEach(Blocking.get {
      jooq({ Configuration c ->
        c.set(new CustomViewRecordMapperProvider([
          drink: String,
          style: String,
        ]))
      }) { DSLContext create ->
        create.select(
          DRINK.SLUG,
          DRINK.NAME.as('drink'),
          STYLE.NAME.as('style'),
          DRINK.AVAILABILITY)
          .from(DRINK)
          .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
          .leftOuterJoin(STYLE).onKey(Keys.DRINK__FK_DRINK_STYLE_ID)
          .where(ORGANIZATION.SLUG.eq(slug))
          .orderBy(DRINK.NAME)
          .fetchInto(DrinkSearchDisplay)
      }
    })
  }

  Observable<String> findNameById(Long id) {
    observe(Blocking.get {
      jooq { DSLContext create ->
        create.select(DRINK.NAME)
          .from(DRINK)
          .where(DRINK.ID.eq(id))
          .fetchOne(DRINK.NAME)
      }
    }).asObservable()
  }

  Observable<Drink> save(Drink drink) {
    observe(Blocking.get {
      jooq { DSLContext create ->
        DrinkRecord drinkRecord = create.newRecord(DRINK, drink)

        drinkRecord.reset(DRINK.DATA)
        drinkRecord.reset(DRINK.CREATED_DATE)
        drinkRecord.reset(DRINK.MODIFIED_DATE)

        if (drinkRecord.id) {
          drinkRecord.update()
        } else {
          drinkRecord.reset(DRINK.ID)
          drinkRecord.store()
        }

        drinkRecord.into(Drink)
      }
    }).asObservable()
  }

  Observable<Drink> get(Long id) {
    observe(Blocking.get {
      jooq { DSLContext create ->
        create.select()
          .from(DRINK)
          .where(DRINK.ID.eq(id))
          .fetchOneInto(Drink)
      }
    }).asObservable()
  }

  Observable<Drink> findBySlug(String brewerySlug, String slug) {
    observe(Blocking.get {
      jooq({ Configuration c ->
        c.set(new CustomViewRecordMapperProvider([
          organizationName: String,
          organizationSlug: String,
          styleName       : String,
          glasswareName   : String
        ]))
      }) { DSLContext create ->
        List<Drink> drinks = create.select(JooqUtil.andFields(
          DRINK.fields(),
          ORGANIZATION.NAME.as('organizationName'),
          ORGANIZATION.SLUG.as('organizationSlug'),
          STYLE.NAME.as('styleName'),
          GLASSWARE.NAME.as('glasswareName')))
          .from(DRINK)
          .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
          .leftOuterJoin(STYLE).onKey(Keys.DRINK__FK_DRINK_STYLE_ID)
          .leftOuterJoin(GLASSWARE).onKey(Keys.DRINK__FK_DRINK_GLASSWARE_ID)
          .where(DRINK.SLUG.eq(slug).and(ORGANIZATION.SLUG.eq(brewerySlug)))
          .fetchInto(Drink)

        if (!drinks) {
          return null
        }

        if (drinks.size() > 1) {
          log.error LogUtil.toLog('FindBySlugError', [brewerySlug: brewerySlug, beerSlug: slug])
        }

        return drinks.first()
      }
    })
  }

  Observable<Drink> all(SortCommand sortCommand = null, int numberOfRows = 20, int offset = 0) {
    observeEach(Blocking.get {
      jooq({ Configuration c ->
        c.set(new CustomViewRecordMapperProvider([
          organizationName: String,
          organizationSlug: String,
          styleName       : String
        ]))
      }) { DSLContext create ->
        create.select(JooqUtil.andFields(
          DRINK.fields(),
          ORGANIZATION.NAME.as('organizationName'),
          ORGANIZATION.SLUG.as('organizationSlug'),
          STYLE.NAME.as('styleName')))
          .from(DRINK)
          .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
          .leftOuterJoin(STYLE).onKey(Keys.DRINK__FK_DRINK_STYLE_ID)
          .orderBy(makeSortField(sortCommand, DRINK.NAME, [
          name        : DRINK.NAME,
          style       : STYLE.NAME,
          availability: DRINK.AVAILABILITY,
          ibu         : DRINK.IBU,
          abv         : DRINK.ABV
        ]))
          .limit(offset, numberOfRows)
          .fetchInto(Drink)
      }
    })
  }

  Observable<Void> delete(String slug) {
    observe(Blocking.op {
      jooq { DSLContext create ->
        DrinkRecord drink = create.fetchOne(DRINK, DRINK.SLUG.equal(slug))

        if (drink) {
          drink.delete()
        }
      }
    })
  }

  Observable<Integer> count() {
    observe(Blocking.get {
      jooq { DSLContext create ->
        create.selectCount().from(ORGANIZATION).fetchOneInto(Integer)
      }
    })
  }

  Observable<Integer> searchCount(String searchTerm) {
    observe(Blocking.get {
      jooq { DSLContext create ->
        create.selectCount()
          .from(DRINK)
          .where(DRINK.NAME.likeIgnoreCase("%${searchTerm}%"))
          .fetchOneInto(Integer)
      }
    })
  }
}
