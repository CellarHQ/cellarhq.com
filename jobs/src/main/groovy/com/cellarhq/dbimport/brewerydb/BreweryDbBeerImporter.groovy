package com.cellarhq.dbimport.brewerydb

import com.cellarhq.generated.tables.records.DrinkRecord
import com.cellarhq.support.ProgressSupport
import com.github.slugify.Slugify
import org.jooq.DSLContext
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.*

class BreweryDbBeerImporter implements ProgressSupport {
  void importBreweriesFromBDB(DSLContext dslContext) {
    BreweryDbBeerRetreiver beerRetreiver = new BreweryDbBeerRetreiver()

    beerRetreiver.withEachBrewery { Map beerMap ->
      try {
        String organizationBrewerDbId = beerMap.breweries?.getAt(0)?.id
        String organizationName = beerMap.breweries?.getAt(0)?.name

        Integer organizationId = dslContext.select(ORGANIZATION.ID)
          .from(ORGANIZATION)
          .where(ORGANIZATION.BREWERY_DB_ID.eq(organizationBrewerDbId))
          .fetchAnyInto(Integer)

        DrinkRecord drink = dslContext.selectFrom(DRINK)
          .where(DRINK.BREWERY_DB_ID.eq(beerMap.id))
          .fetchAny()

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
        drink.srm = beerMap.srm instanceof String ? Double.parseDouble(beerMap.srm).toInteger() : null
        drink.abv = beerMap.abv instanceof String ? Double.parseDouble(beerMap.abv).toBigDecimal() : null
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

        drink.organizationId = drink.organizationId ?: organizationId
        drink.glasswareId = glasswareId
        drink.styleId = styleId

        if (drink.organizationId) {
          drink.store()
          incrementProgressAnts()
        } else {
          println "no brewery for beerName:${beerMap.name} orgBreweryDbID:${organizationBrewerDbId} orgName:${organizationName}"
        }

      } catch (DataAccessException e) {
        println "Data access exception ${e.message} processing ${beerMap.id}"
        e.printStackTrace()
      }
    }
  }
}
