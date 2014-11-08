package com.cellarhq.dbimport.brewerydb

import com.cellarhq.support.ProgressSupport
import com.cellarhq.generated.tables.records.DrinkRecord
import com.github.slugify.Slugify
import org.jooq.DSLContext
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.*

class BreweryDbBeerImporter implements ProgressSupport {
    void importBreweriesFromBDB(DSLContext dslContext) {
        BreweryDbBeerRetreiver beerRetreiver = new BreweryDbBeerRetreiver()

        beerRetreiver.withEachBrewery { Map beerMap ->
            String searchSlug = new Slugify().slugify(beerMap.name.toString())

            try {
                String organizationBrewerDbId = beerMap.breweries?.getAt(0)?.id
                Integer organizationId = dslContext.select(ORGANIZATION.ID)
                    .from(ORGANIZATION)
                    .where(ORGANIZATION.BREWERY_DB_ID.eq(organizationBrewerDbId))
                    .fetchOneInto(Integer)

                DrinkRecord drink = dslContext.select(DRINK.ID)
                    .from(DRINK)
                    .where(DRINK.SLUG.eq(searchSlug).and(DRINK.ORGANIZATION_ID.eq(organizationId)))
                    .fetchOneInto(DrinkRecord)

                if (!drink) {
                    drink = dslContext.newRecord(DRINK)
                }
                drink.breweryDbId = beerMap.id
                drink.description = beerMap.description
                drink.name = beerMap.name
                drink.slug = new Slugify().slugify(beerMap.name)
                drink.locked = true
                drink.needsModeration = true
                drink.drinkType = 'BEER'
                drink.ibu = beerMap.ibu instanceof String ? Double.parseDouble(beerMap.ibu).toInteger() : null
                drink.srm = beerMap.srm instanceof String  ? Double.parseDouble(beerMap.srm).toInteger() : null
                drink.abv = beerMap.abv instanceof String  ? Double.parseDouble(beerMap.abv).toBigDecimal() : null
                drink.availability = beerMap.availability

                String styleName = beerMap.style?.name
                String glasswareName = beerMap.glass?.name

                Integer styleId = dslContext.select(STYLE.ID)
                    .from(STYLE)
                    .where(STYLE.NAME.equalIgnoreCase(styleName))
                    .fetchOneInto(Integer)

                Integer glasswareId = dslContext.select(GLASSWARE.ID)
                    .from(GLASSWARE)
                    .where(GLASSWARE.NAME.equalIgnoreCase(glasswareName))
                    .fetchOneInto(Integer)

                drink.organizationId = organizationId
                drink.glasswareId = glasswareId
                drink.styleId = styleId

                if (organizationId) {
                    drink.store()
                    incrementProgressAnts()
                }  else {
                    println "no brewery for ${beerMap.name}"
                }

            } catch (DataAccessException e) {
                println "Data access exception ${e.message} processing ${beerMap.name}"
            }
        }
    }
}
