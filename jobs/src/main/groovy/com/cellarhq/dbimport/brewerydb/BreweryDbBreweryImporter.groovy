package com.cellarhq.dbimport.brewerydb

import com.cellarhq.generated.tables.records.OrganizationRecord
import com.cellarhq.support.ProgressSupport
import com.github.slugify.Slugify
import org.jooq.DSLContext
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.ORGANIZATION

class BreweryDbBreweryImporter implements ProgressSupport {
  void importBreweriesFromBDB(DSLContext dslContext) {
    BreweryDbBreweryRetreiver breweryRetreiver = new BreweryDbBreweryRetreiver()

    resetProgressAnts()
    breweryRetreiver.withEachBrewery { Map breweryMap ->
      try {
        OrganizationRecord organization = dslContext.selectFrom(ORGANIZATION)
          .where(ORGANIZATION.BREWERY_DB_ID.eq(breweryMap.id))
          .fetchAnyInto(OrganizationRecord)

        if (organization) {
          organization.breweryDbId = breweryMap.id
          organization.established = breweryMap?.established ? Short.parseShort(breweryMap.established.toString()) : null
          organization.website = breweryMap.website
          organization.description = breweryMap.description
          organization.name = breweryMap.name
          organization.locked = true
          organization.needsModeration = true
          organization.address = breweryMap.locations?.getAt(0)?.streetAddress
          organization.phone = breweryMap.locations?.getAt(0)?.phone
          organization.postalCode = breweryMap.locations?.getAt(0)?.postalCode
          organization.region = breweryMap.locations?.getAt(0)?.region
          organization.locality = breweryMap.locations?.getAt(0)?.locality
          organization.country = breweryMap.locations?.getAt(0)?.countryIsoCode
          organization.type = 'BREWERY'

          organization.store()
          incrementProgressAnts()
        } else {
          OrganizationRecord newOrganization = dslContext.newRecord(ORGANIZATION)

          newOrganization.breweryDbId = breweryMap.id
          newOrganization.established = breweryMap?.established ? Short.parseShort(breweryMap.established.toString()) : null
          newOrganization.website = breweryMap.website
          newOrganization.description = breweryMap.description
          newOrganization.name = breweryMap.name

          String slug = new Slugify().slugify(breweryMap.name)
          OrganizationRecord existingSlug = dslContext.selectFrom(ORGANIZATION)
            .where(ORGANIZATION.SLUG.eq(slug))
            .fetchAnyInto(OrganizationRecord)
          if (existingSlug) {
            slug += "-1"
          }

          newOrganization.slug = slug

          newOrganization.locked = true
          newOrganization.needsModeration = true
          newOrganization.address = breweryMap.locations?.getAt(0)?.streetAddress
          newOrganization.phone = breweryMap.locations?.getAt(0)?.phone
          newOrganization.postalCode = breweryMap.locations?.getAt(0)?.postalCode
          newOrganization.region = breweryMap.locations?.getAt(0)?.region
          newOrganization.locality = breweryMap.locations?.getAt(0)?.locality
          newOrganization.country = breweryMap.locations?.getAt(0)?.countryIsoCode
          newOrganization.type = 'BREWERY'

          newOrganization.store()
          incrementProgressAnts()
        }
      } catch (DataAccessException e) {
        println "Data access exception ${e.message} processing ${breweryMap.name}"
      }
    }
  }
}
