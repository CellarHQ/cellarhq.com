package com.cellarhq.services

import com.cellarhq.domain.Organization
import com.cellarhq.generated.Tables
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import ratpack.exec.ExecControl
import rx.Observable

import javax.sql.DataSource

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
                OrganizationRecord organizationRecord = create.newRecord(Tables.ORGANIZATION, organization)


                organizationRecord.reset(Tables.ORGANIZATION.DATA)
                organizationRecord.reset(Tables.ORGANIZATION.CREATED_DATE)
                organizationRecord.reset(Tables.ORGANIZATION.MODIFIED_DATE)

                if (organizationRecord.id) {
                    organizationRecord.update()
                } else {
                    organizationRecord.reset(Tables.ORGANIZATION.ID)
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
                        .from(Tables.ORGANIZATION)
                        .where(Tables.ORGANIZATION.ID.eq(id))
                        .fetchOneInto(Organization)
            }
        }).asObservable()

    }

    Observable<Organization> findBySlug(String slug) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(Tables.ORGANIZATION)
                        .where(Tables.ORGANIZATION.SLUG.eq(slug))
                        .fetchOneInto(Organization)
            }
        }).asObservable()

    }

    Observable<Organization> all(int numberOfRows=20, int offset=0) {
        observeEach(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(Tables.ORGANIZATION)
                        .orderBy(Tables.ORGANIZATION.NAME)
                        .limit(offset, numberOfRows)
                        .fetchInto(Organization)
            }
        })
    }

    Observable<Organization> search(String searchTerm, int numberOfRows=20, int offset=0) {
        observeEach(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(Tables.ORGANIZATION)
                        .where(Tables.ORGANIZATION.NAME.likeIgnoreCase("%${searchTerm}%"))
                        .orderBy(Tables.ORGANIZATION.NAME)
                        .limit(offset, numberOfRows)
                        .fetchInto(Organization)
            }
        })
    }

    Observable<Void> delete(String slug) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                OrganizationRecord org = create.fetchOne(Tables.ORGANIZATION, Tables.ORGANIZATION.SLUG.equal(slug))

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
                create.selectCount().from(Tables.ORGANIZATION).fetchOneInto(Integer)
            }
        })
    }

    Observable<Integer> searchCount(String searchTerm) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.selectCount()
                    .from(Tables.ORGANIZATION)
                    .where(Tables.ORGANIZATION.NAME.likeIgnoreCase("%${searchTerm}%"))
                    .fetchOneInto(Integer)
            }
        })
    }
}
