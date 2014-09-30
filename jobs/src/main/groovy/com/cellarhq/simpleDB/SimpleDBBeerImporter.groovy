package com.cellarhq.simpleDB

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.tables.Organization
import com.cellarhq.generated.tables.pojos.Drink
import com.cellarhq.generated.tables.records.DrinkRecord
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.github.slugify.Slugify
import org.jooq.DSLContext
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.DRINK
import static com.cellarhq.generated.Tables.ORGANIZATION


class SimpleDBBeerImporter {
    void importBeersFromS3(DSLContext dslContext) {
        String selectAllBeersQuery = 'select * from cellar_PROD_beers limit 2500'

        SimpleDBItemRetriever itemRetriever = new SimpleDBItemRetriever()
        SimpleDBToDrinkMapper drinkMapper = new SimpleDBToDrinkMapper()
        AmazonHelper helper = new AmazonHelper()

        itemRetriever.withEachItem(selectAllBeersQuery) { Item item ->
            String beerName = helper.getAttribute(item.attributes, 'name').replace('"', '""')
            String breweryName = helper.getAttribute(item.attributes, 'brewery').replace('"', '""')

            String verificationSelect = "select * from cellar_PROD_cellars  where beer=\"${beerName}\" and brewery=\"${breweryName}\" limit 1"

            if (itemRetriever.doesQueryReturnResult(verificationSelect)) {
                Drink drink = drinkMapper.mapItemToDrink(dslContext, item)

                DrinkRecord drinkRecord = dslContext.newRecord(DRINK, drink)
                drinkRecord.reset(DRINK.ID)
                drinkRecord.reset(DRINK.DATA)

                if (!drinkRecord.createdDate) drinkRecord.reset(DRINK.CREATED_DATE)
                if (!drinkRecord.modifiedDate) drinkRecord.reset(DRINK.MODIFIED_DATE)

                try {
                    if (drinkRecord.organizationId) {
                        drinkRecord.store()

                    } else {
                        String name = helper.getAttribute(item.attributes, 'brewery').trim()
                        OrganizationRecord organization = new LastResort().insertOrganization(name, dslContext)

                        println "Could not find brewery ${organization.name} so inserted it now"

                        drinkRecord.organizationId = organization.id
                        drinkRecord.store()
                    }
                } catch (DataAccessException e) {
                    println "Error inserting because ${e.message} with ${drinkRecord}"
                }
            }
        }
    }
}
