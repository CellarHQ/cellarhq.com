package com.cellarhq.api.services

import com.cellarhq.domain.Organization
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.cellarhq.jooq.BaseJooqService
import com.cellarhq.jooq.SortCommand
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise

import javax.sql.DataSource

import static com.cellarhq.generated.Tables.DRINK
import static com.cellarhq.generated.Tables.ORGANIZATION

@Slf4j
class OrganizationService extends BaseJooqService {

  @Inject
  OrganizationService(DataSource dataSource) {
    super(dataSource)
  }

  Promise<Organization> save(Organization organization) {
    Blocking.get {
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
    }

  }

  Promise<Organization> get(Long id) {
    Blocking.get {
      jooq { DSLContext create ->
        create.select()
              .from(ORGANIZATION)
              .where(ORGANIZATION.ID.eq(id))
              .fetchOneInto(Organization)
      }
    }

  }

  Promise<Organization> findBySlug(String slug) {
    Blocking.get {
      jooq { DSLContext create ->
        create.select()
              .from(ORGANIZATION)
              .where(ORGANIZATION.SLUG.eq(slug))
              .fetchOneInto(Organization)
      }
    }

  }

  Promise<String> findNameByDrink(Long drinkId) {
    Blocking.get {
      jooq { DSLContext create ->
        create.select(ORGANIZATION.NAME)
              .from(ORGANIZATION)
              .join(DRINK).onKey()
              .where(DRINK.ID.eq(drinkId))
              .fetchOne(ORGANIZATION.NAME)
      }
    }
  }

  Promise<List<Organization>> all(SortCommand sortCommand = null, int numberOfRows = 20, int offset = 0) {
    Blocking.get {
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
    }
  }

  Promise<List<Organization>> search(String searchTerm, SortCommand sortCommand, int numberOfRows = 20, int offset = 0) {
    Blocking.get {
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
    }
  }

  Operation delete(String slug) {
    Blocking.op {
      jooq { DSLContext create ->
        OrganizationRecord org = create.fetchOne(ORGANIZATION, ORGANIZATION.SLUG.equal(slug))

        if (org) {
          org.delete()
        }
      }
    }
  }

  Promise<Integer> count() {
    Blocking.get {
      jooq { DSLContext create ->
        create.selectCount().from(ORGANIZATION).fetchOneInto(Integer)
      }
    }
  }

  Promise<Integer> searchCount(String searchTerm) {
    Blocking.get {
      jooq { DSLContext create ->
        create.selectCount()
              .from(ORGANIZATION)
              .where(ORGANIZATION.NAME.likeIgnoreCase("%${searchTerm}%"))
              .fetchOneInto(Integer)
      }
    }
  }
}
