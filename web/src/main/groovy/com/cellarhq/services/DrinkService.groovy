package com.cellarhq.services

import com.cellarhq.domain.DrinkSearchDisplay
import com.cellarhq.generated.Keys
import com.cellarhq.mappers.CustomViewRecordMapperProvider
import org.jooq.Configuration

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observeEach

import com.cellarhq.domain.Drink
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.DSLContext
import ratpack.exec.ExecControl

import javax.sql.DataSource

@CompileStatic
class DrinkService extends BaseJooqService {

    @Inject
    DrinkService(DataSource dataSource, ExecControl execControl) {
        super(dataSource, execControl)
    }


    rx.Observable<Drink> search(String searchTerm, int numberOfRows=20, int offset=0) {
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

    rx.Observable<DrinkSearchDisplay> findByOrganizationSlug(String slug) {
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
}
