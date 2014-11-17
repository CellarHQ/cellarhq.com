package com.cellarhq.services

import com.cellarhq.generated.Keys

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observe
import static ratpack.rx.RxRatpack.observeEach

import com.cellarhq.domain.CellaredDrink
import com.cellarhq.domain.views.CellaredDrinkDetails
import com.cellarhq.generated.tables.records.CellaredDrinkRecord
import com.cellarhq.jooq.CellarStatsUpdater

import com.cellarhq.jooq.SortCommand
import com.cellarhq.mappers.CustomViewRecordMapperProvider
import com.cellarhq.util.JooqUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.SelectConditionStep
import org.jooq.SelectJoinStep
import ratpack.exec.ExecControl

import javax.sql.DataSource
import java.sql.Timestamp
import java.time.LocalDateTime

@Slf4j
class CellaredDrinkService extends BaseJooqService {

    @Inject
    CellaredDrinkService(DataSource dataSource, ExecControl execControl) {
        super(dataSource, execControl)
    }

    rx.Observable<CellaredDrink> save(CellaredDrink cellaredDrink) {
        observe(execControl.blocking {
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
        }).asObservable()
    }

    rx.Observable<CellaredDrink> findById(Long id) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(CELLARED_DRINK)
                        .where(CELLARED_DRINK.ID.eq(id))
                        .fetchOneInto(CellaredDrink)
            }
        }).asObservable()
    }

    rx.Observable<CellaredDrinkDetails> findByIdForEdit(String cellarSlug, Long id) {
        observe(execControl.blocking {
            jooq({ Configuration c ->
                c.set(new CustomViewRecordMapperProvider([
                        organizationName: String,
                        drinkName: String
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
                        .and(CELLAR.SCREEN_NAME.eq(cellarSlug)))
                        .fetchOneInto(CellaredDrinkDetails)
            }
        })
    }

    rx.Observable<CellaredDrink> all(Long id, SortCommand sortCommand) {
        observeEach(execControl.blocking {
            allQuery(sortCommand) { SelectJoinStep step ->
                step.where(CELLAR.ID.eq(id))
            }
        })
    }

    rx.Observable<CellaredDrink> all(String cellarSlug, SortCommand sortCommand) {
        observeEach(execControl.blocking {
            allQuery(sortCommand) { SelectJoinStep step ->
                step.where(CELLAR.SLUG.equalIgnoreCase(cellarSlug))
            }
        })
    }

    private List<CellaredDrinkDetails> allQuery(SortCommand sortCommand, Closure<SelectConditionStep> criteria) {
        jooq({ Configuration c ->
            c.set(new CustomViewRecordMapperProvider([
                    organizationSlug: String,
                    organizationName: String,
                    drinkSlug: String,
                    drinkName: String,
                    styleName: String
            ]))
        }) { DSLContext create ->
            SelectJoinStep selectStep = create.select(JooqUtil.andFields(
                        CELLARED_DRINK.fields(),
                        ORGANIZATION.SLUG.as('organizationSlug'),
                        ORGANIZATION.NAME.as('organizationName'),
                        DRINK.SLUG.as('drinkSlug'),
                        DRINK.NAME.as('drinkName'),
                        STYLE.NAME.as('styleName')
                    ))
                    .from(CELLARED_DRINK)
                    .join(CELLAR).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_CELLAR_ID)
                    .join(DRINK).onKey(Keys.CELLARED_DRINK__FK_CELLARED_DRINK_DRINK_ID)
                    .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
                    .leftOuterJoin(STYLE).onKey(Keys.DRINK__FK_DRINK_STYLE_ID)

            criteria(selectStep)
                    .and(CELLARED_DRINK.QUANTITY.greaterThan(0))
                    .orderBy(makeSortField(sortCommand, ORGANIZATION.NAME, [
                            beerName: DRINK.NAME,
                            breweryName: ORGANIZATION.NAME,
                            size: CELLARED_DRINK.SIZE,
                            quantity: CELLARED_DRINK.QUANTITY,
                            bottleDate: CELLARED_DRINK.BOTTLE_DATE,
                            style: STYLE.NAME
                    ]), DRINK.NAME.asc())
                    .fetchInto(CellaredDrinkDetails)
        }
    }

    rx.Observable<Void> delete(Long id) {
        observe(execControl.blocking {
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

                return Void
            }
        })
    }

    rx.Observable<CellaredDrink> drink(String cellarSlug, Long id) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                CellaredDrinkRecord drink = create
                        .select()
                        .from(CELLARED_DRINK)
                        .join(CELLAR).onKey()
                        .where(CELLAR.SCREEN_NAME.eq(cellarSlug))
                            .and(CELLARED_DRINK.ID.eq(id))
                        .fetchOneInto(CellaredDrinkRecord)

                if (drink && drink.quantity > 0) {
                    create.update(CELLARED_DRINK)
                            .set(CELLARED_DRINK.QUANTITY, CELLARED_DRINK.QUANTITY.minus(1))
                            .set(CELLARED_DRINK.MODIFIED_DATE, Timestamp.valueOf(LocalDateTime.now()))
                            .where(CELLARED_DRINK.ID.eq(id))
                            .execute()

                    new CellarStatsUpdater().updateAllCounts(drink.cellarId, drink.drinkId, create)

                    drink.refresh(CELLARED_DRINK.QUANTITY, CELLARED_DRINK.MODIFIED_DATE)
                    return drink.into(CellaredDrink)
                }

                return null
            }
        })
    }

    rx.Observable<CellaredDrink> findTradeableCellaredDrinks(Long drinkId, SortCommand sortCommand) {
        observeEach(execControl.blocking {
            allQuery(sortCommand) { SelectJoinStep step ->
                step.where(CELLARED_DRINK.DRINK_ID.eq(drinkId))
            }
        })
    }
}
