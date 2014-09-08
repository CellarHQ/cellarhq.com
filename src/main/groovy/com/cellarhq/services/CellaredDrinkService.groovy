package com.cellarhq.services

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observe
import static ratpack.rx.RxRatpack.observeEach

import com.cellarhq.domain.CellaredDrink
import com.cellarhq.domain.views.CellaredDrinkDetails
import com.cellarhq.generated.tables.records.CellaredDrinkRecord
import com.cellarhq.util.JooqUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import org.jooq.SelectConditionStep
import org.jooq.SelectJoinStep
import ratpack.exec.ExecControl

import javax.sql.DataSource

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

                drinkRecord.into(CellaredDrink)
            }
        }).asObservable()
    }

    rx.Observable<CellaredDrink> findById(String cellarSlug, Long id) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                return findByCellarAndId(create, cellarSlug, id)
            }
        }).asObservable()
    }

    rx.Observable<CellaredDrink> all(Long id) {
        observeEach(execControl.blocking {
            allQuery { SelectJoinStep step ->
                step.where(CELLAR.ID.eq(id))
            }
        })
    }

    rx.Observable<CellaredDrink> all(String cellarSlug) {
        observeEach(execControl.blocking {
            allQuery { SelectJoinStep step ->
                step.where(CELLAR.SCREEN_NAME.eq(cellarSlug))
            }
        })
    }

    private List<CellaredDrinkDetails> allQuery(Closure<SelectConditionStep> criteria) {
        jooq { DSLContext create ->
            SelectJoinStep step = create.select(JooqUtil.andFields(
                        CELLARED_DRINK.fields(),
                        STYLE.NAME.as('styleName'),
                        ORGANIZATION.SLUG.as('organizationSlug'),
                        ORGANIZATION.NAME.as('organizationName'),
                        DRINK.SLUG.as('drinkSlug'),
                        DRINK.NAME.as('drinkName')
                    ))
                    .from(CELLARED_DRINK)
                    .join(CELLAR).onKey()
                    .join(STYLE).onKey()
                    .join(DRINK).onKey()
                    .join(ORGANIZATION).onKey()
            criteria(step)
                    .and(CELLARED_DRINK.QUANTITY.greaterThan(0))
                    .fetchInto(CellaredDrinkDetails)
        }
    }

    rx.Observable<Void> delete(String cellarSlug, Long id) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                CellaredDrinkRecord drink = findByCellarAndId(create, cellarSlug, id)

                if (drink) {
                    drink.delete()
                }

                return Void
            }
        })
    }

    private static CellaredDrinkRecord findByCellarAndId(DSLContext create, String cellarSlug, Long id) {
        return create
                .select()
                .from(CELLARED_DRINK)
                .join(CELLAR).onKey()
                .where(CELLAR.SCREEN_NAME.eq(cellarSlug))
                    .and(CELLARED_DRINK.ID.eq(id))
                .fetchOneInto(CellaredDrinkRecord)
    }
}
