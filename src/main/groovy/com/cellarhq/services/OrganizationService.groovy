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

import static com.cellarhq.util.DataSourceUtil.withDslContext
import static ratpack.rx.RxRatpack.observe
import static ratpack.rx.RxRatpack.observeEach

@CompileStatic
@Slf4j
class OrganizationService {

    private final DataSource dataSource
    private final ExecControl execControl

    @Inject
    OrganizationService(DataSource dataSource, ExecControl execControl) {
        this.dataSource = dataSource
        this.execControl = execControl
    }

    Observable<Organization> save(Organization organization) {
        observe(execControl.blocking {
            withDslContext(dataSource) { DSLContext create ->
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
            withDslContext(dataSource) { DSLContext create ->
                create.select()
                        .from(Tables.ORGANIZATION)
                        .where(Tables.ORGANIZATION.ID.eq(id))
                        .fetchOneInto(Organization)
            }
        }).asObservable()

    }

    Observable<Organization> findBySlug(String slug) {
        observe(execControl.blocking {
            withDslContext(dataSource) { DSLContext create ->
                create.select()
                        .from(Tables.ORGANIZATION)
                        .where(Tables.ORGANIZATION.SLUG.eq(slug))
                        .fetchOneInto(Organization)
            }
        }).asObservable()

    }

    Observable<Organization> all() {
        observeEach(execControl.blocking {
            withDslContext(dataSource) { DSLContext create ->
                create.select()
                        .from(Tables.ORGANIZATION)
                        .fetchInto(Organization)


            }
        })
    }

    Observable<Void> delete(String slug) {
        observe(execControl.blocking {
            withDslContext(dataSource) { DSLContext create ->
                OrganizationRecord org = create.fetchOne(Tables.ORGANIZATION, Tables.ORGANIZATION.SLUG.equal(slug))

                if (org) {
                    org.delete()
                }

                return Void
            }
        })
    }
}
