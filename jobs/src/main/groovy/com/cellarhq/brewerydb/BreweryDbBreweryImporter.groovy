package com.cellarhq.brewerydb

import com.cellarhq.generated.tables.records.OrganizationRecord
import com.github.slugify.Slugify
import org.jooq.DSLContext
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.ORGANIZATION


class BreweryDbBreweryImporter {
    void importBreweriesFromBDB(DSLContext dslContext) {
        BreweryDbBreweryRetreiver breweryRetreiver = new BreweryDbBreweryRetreiver()

        breweryRetreiver.withEachBrewery { Map breweryMap ->
            String searchSlug = new Slugify().slugify(breweryMap.name.toString())

            try {
                OrganizationRecord organization = dslContext.select(ORGANIZATION.ID)
                    .from(ORGANIZATION)
                    .where(ORGANIZATION.SLUG.eq(searchSlug))
                    .fetchOneInto(OrganizationRecord)

                if (organization) {
                    organization.breweryDbId = breweryMap.id
                    organization.established = breweryMap?.established ? Short.parseShort(breweryMap.established.toString()) : null
                    organization.website = breweryMap.website

                    organization.store()
                } else {
                    OrganizationRecord newOrganization = dslContext.newRecord(ORGANIZATION)

                    newOrganization.name = breweryMap.name
                    newOrganization.slug = new Slugify().slugify(newOrganization.name)
                    newOrganization.established = breweryMap?.established ? Short.parseShort(breweryMap.established.toString()) : null
                    newOrganization.website = breweryMap.website
                    newOrganization.type = 'BREWERY'

                    newOrganization.store()
                }
            } catch (DataAccessException e) {
                println "Data access exception ${e.message} processing ${breweryMap.name}"
            }
        }
    }
}
