package com.cellarhq.services

import com.cellarhq.domain.Organization
import com.cellarhq.generated.tables.records.OrganizationRecord

import com.cellarhq.jooq.SortCommand
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import ratpack.exec.ExecControl
import rx.Observable

import javax.sql.DataSource

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observe
import static ratpack.rx.RxRatpack.observeEach

@CompileStatic
@Slf4j
class OrganizationService extends BaseJooqService {

    @Inject
    OrganizationService(DataSource dataSource, ExecControl execControl) {
        super(dataSource, execControl)
    }

    Observable<Organization> save(Organization organization) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                OrganizationRecord organizationRecord = create.newRecord(ORGANIZATION, organization)


                organizationRecord.reset(ORGANIZATION.DATA)
                organizationRecord.reset(ORGANIZATION.CREATED_DATE)
                organizationRecord.reset(ORGANIZATION.MODIFIED_DATE)

                if (organizationRecord.id) {
                    organizationRecord.update()
                } else {
                    organizationRecord.reset(ORGANIZATION.ID)
                    organizationRecord.store()
                }

                organizationRecord.into(Organization)
            }
        }).asObservable()

    }

    Observable<Organization> get(Long id) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(ORGANIZATION)
                        .where(ORGANIZATION.ID.eq(id))
                        .fetchOneInto(Organization)
            }
        }).asObservable()

    }

    Observable<Organization> findBySlug(String slug) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(ORGANIZATION)
                        .where(ORGANIZATION.SLUG.eq(slug))
                        .fetchOneInto(Organization)
            }
        }).asObservable()

    }

    Observable<String> findNameByDrink(Long drinkId) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.select(ORGANIZATION.NAME)
                        .from(ORGANIZATION)
                        .join(DRINK).onKey()
                        .where(DRINK.ID.eq(drinkId))
                        .fetchOne(ORGANIZATION.NAME)
            }
        }).asObservable()
    }

    Observable<String> findNameByDrink(String drinkSlug) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.select(ORGANIZATION.NAME)
                        .from(ORGANIZATION)
                        .join(DRINK).onKey()
                        .where(DRINK.SLUG.eq(drinkSlug))
                        .fetchOne(ORGANIZATION.NAME)
            }
        }).asObservable()
    }

    Observable<Organization> all(SortCommand sortCommand = null, int numberOfRows=20, int offset=0) {
        observeEach(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(ORGANIZATION)
                        .orderBy(makeSortField(sortCommand, ORGANIZATION.NAME, [
                                name: ORGANIZATION.NAME,
                                location: ORGANIZATION.LOCALITY,
                                established: ORGANIZATION.ESTABLISHED
                        ]))
                        .limit(offset, numberOfRows)
                        .fetchInto(Organization)
            }
        })
    }

    Observable<Organization> search(String searchTerm, SortCommand sortCommand, int numberOfRows=20, int offset=0) {
        observeEach(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(ORGANIZATION)
                        .where(ORGANIZATION.NAME.likeIgnoreCase("%${searchTerm}%"))
                        .orderBy(makeSortField(sortCommand, ORGANIZATION.NAME, [
                                name: ORGANIZATION.NAME,
                                location: ORGANIZATION.LOCALITY,
                                established: ORGANIZATION.ESTABLISHED
                        ]))
                        .limit(offset, numberOfRows)
                        .fetchInto(Organization)
            }
        })
    }

    Observable<Void> delete(String slug) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                OrganizationRecord org = create.fetchOne(ORGANIZATION, ORGANIZATION.SLUG.equal(slug))

                if (org) {
                    org.delete()
                }

                return Void
            }
        })
    }

    Observable<Integer> count() {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.selectCount().from(ORGANIZATION).fetchOneInto(Integer)
            }
        })
    }

    Observable<Integer> searchCount(String searchTerm) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.selectCount()
                    .from(ORGANIZATION)
                    .where(ORGANIZATION.NAME.likeIgnoreCase("%${searchTerm}%"))
                    .fetchOneInto(Integer)
            }
        })
    }
}
