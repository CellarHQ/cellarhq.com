package com.cellarhq.api.services

import com.cellarhq.domain.Organization
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.cellarhq.jooq.BaseJooqService
import com.cellarhq.jooq.SortCommand
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import ratpack.exec.Blocking
import rx.Observable

import javax.sql.DataSource

import static com.cellarhq.generated.Tables.DRINK
import static com.cellarhq.generated.Tables.ORGANIZATION
import static ratpack.rx.RxRatpack.observe
import static ratpack.rx.RxRatpack.observeEach

@Slf4j
class OrganizationService extends BaseJooqService {

  @Inject
  OrganizationService(DataSource dataSource) {
    super(dataSource)
  }

  Observable<Organization> save(Organization organization) {
    observe(Blocking.get {
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
    })

  }

  Observable<Organization> get(Long id) {
    observe(Blocking.get {
      jooq { DSLContext create ->
        create.select()
          .from(ORGANIZATION)
          .where(ORGANIZATION.ID.eq(id))
          .fetchOneInto(Organization)
      }
    }).asObservable()

  }

  Observable<Organization> findBySlug(String slug) {
    observe(Blocking.get {
      jooq { DSLContext create ->
        create.select()
          .from(ORGANIZATION)
          .where(ORGANIZATION.SLUG.eq(slug))
          .fetchOneInto(Organization)
      }
    }).asObservable()

  }

  Observable<String> findNameByDrink(Long drinkId) {
    observe(Blocking.get {
      jooq { DSLContext create ->
        create.select(ORGANIZATION.NAME)
          .from(ORGANIZATION)
          .join(DRINK).onKey()
          .where(DRINK.ID.eq(drinkId))
          .fetchOne(ORGANIZATION.NAME)
      }
    }).asObservable()
  }

  Observable<Organization> all(SortCommand sortCommand = null, int numberOfRows = 20, int offset = 0) {
    observeEach(Blocking.get {
      jooq { DSLContext create ->
        create.select()
          .from(ORGANIZATION)
          .orderBy(
          ORGANIZATION.COLLABORATION.asc(),
          makeSortField(sortCommand, ORGANIZATION.NAME, [
            name       : ORGANIZATION.NAME,
            location   : ORGANIZATION.LOCALITY_SORT,
            established: ORGANIZATION.ESTABLISHED
          ]))
          .limit(offset, numberOfRows)
          .fetchInto(Organization)
      }
    })
  }

  Observable<Organization> search(String searchTerm, SortCommand sortCommand, int numberOfRows = 20, int offset = 0) {
    observeEach(Blocking.get {
      jooq { DSLContext create ->
        create.select()
          .from(ORGANIZATION)
          .where(ORGANIZATION.NAME.likeIgnoreCase("%${searchTerm}%"))
          .orderBy(
          ORGANIZATION.COLLABORATION.asc(),
          makeSortField(sortCommand, ORGANIZATION.NAME, [
            name       : ORGANIZATION.NAME,
            location   : ORGANIZATION.LOCALITY_SORT,
            established: ORGANIZATION.ESTABLISHED
          ]))
          .limit(offset, numberOfRows)
          .fetchInto(Organization)
      }
    })
  }

  Observable<Void> delete(String slug) {
    observe(Blocking.op {
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
          .from(ORGANIZATION)
          .where(ORGANIZATION.NAME.likeIgnoreCase("%${searchTerm}%"))
          .fetchOneInto(Integer)
      }
    })
  }
}
