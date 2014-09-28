package com.cellarhq.services

import com.cellarhq.domain.Drink
import com.cellarhq.domain.views.DrinkSearchDisplay
import com.cellarhq.generated.Keys
import com.cellarhq.generated.Tables
import com.cellarhq.generated.tables.records.DrinkRecord
import com.cellarhq.mappers.CustomViewRecordMapperProvider
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.Configuration
import org.jooq.DSLContext
import ratpack.exec.ExecControl
import rx.Observable

import javax.sql.DataSource

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observe
import static ratpack.rx.RxRatpack.observeEach

@CompileStatic
class DrinkService extends BaseJooqService {

    @Inject
    DrinkService(DataSource dataSource, ExecControl execControl) {
        super(dataSource, execControl)
    }


    Observable<Drink> search(String searchTerm, int numberOfRows=20, int offset=0) {
        observeEach(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(DRINK)
                        .where(DRINK.NAME.likeIgnoreCase("%${searchTerm}%"))
                        .orderBy(DRINK.NAME)
                        .limit(offset, numberOfRows)
                        .fetchInto(Drink)
            }
        })
    }

    Observable<DrinkSearchDisplay> findByOrganizationSlug(String slug) {
        observeEach(execControl.blocking {
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
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.select(DRINK.NAME)
                        .from(DRINK)
                        .where(DRINK.ID.eq(id))
                        .fetchOne(DRINK.NAME)
            }
        }).asObservable()
    }

    Observable<Drink> save(Drink drink) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                DrinkRecord drinkRecord = create.newRecord(Tables.DRINK, drink)


                drinkRecord.reset(Tables.DRINK.DATA)
                drinkRecord.reset(Tables.DRINK.CREATED_DATE)
                drinkRecord.reset(Tables.DRINK.MODIFIED_DATE)

                if (drinkRecord.id) {
                    drinkRecord.update()
                } else {
                    drinkRecord.reset(Tables.DRINK.ID)
                    drinkRecord.store()
                }

                drinkRecord.into(Drink)
            }
        }).asObservable()

    }

    Observable<Drink> get(Long id) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                    .from(Tables.DRINK)
                    .where(Tables.DRINK.ID.eq(id))
                    .fetchOneInto(Drink)
            }
        }).asObservable()

    }

    Observable<Drink> findBySlug(String slug) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                    .from(Tables.DRINK)
                    .where(Tables.DRINK.SLUG.eq(slug))
                    .fetchOneInto(Drink)
            }
        }).asObservable()

    }

    Observable<Drink> all(int numberOfRows=20, int offset=0) {
        observeEach(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                    .from(Tables.DRINK)
                    .orderBy(Tables.DRINK.NAME)
                    .limit(offset, numberOfRows)
                    .fetchInto(Drink)
            }
        })
    }

    Observable<Void> delete(String slug) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                DrinkRecord drink = create.fetchOne(Tables.DRINK, Tables.DRINK.SLUG.equal(slug))

                if (drink) {
                    drink.delete()
                }

                return Void
            }
        })
    }

    Observable<Integer> count() {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.selectCount().from(Tables.ORGANIZATION).fetchOneInto(Integer)
            }
        })
    }

    Observable<Integer> searchCount(String searchTerm) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.selectCount()
                    .from(Tables.DRINK)
                    .where(Tables.DRINK.NAME.likeIgnoreCase("%${searchTerm}%"))
                    .fetchOneInto(Integer)
            }
        })
    }
}
