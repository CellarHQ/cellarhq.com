package com.cellarhq.s3

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.tables.pojos.Drink
import com.cellarhq.generated.tables.records.DrinkRecord
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.github.slugify.Slugify
import org.jooq.DSLContext
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.DRINK
import static com.cellarhq.generated.Tables.ORGANIZATION


class S3BeerImporter {
    void importBeersFromS3(DSLContext dslContext) {
        String selectAllBeersQuery = 'select * from cellar_PROD_beers limit 2500'

        S3ItemRetriever s3ItemRetriever = new S3ItemRetriever()
        S3ToDrinkMapper drinkMapper = new S3ToDrinkMapper()
        AmazonHelper helper = new AmazonHelper()

        s3ItemRetriever.withEachItem(selectAllBeersQuery) { Item item ->
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
                    String organizationName = helper.getAttribute(item.attributes, 'brewery').trim()

                    OrganizationRecord newOrganization = dslContext.newRecord(ORGANIZATION)

                    newOrganization.name = organizationName
                    newOrganization.slug = new Slugify().slugify(newOrganization.name)
                    newOrganization.type = 'BREWERY'

                    int newId = newOrganization.store()
                    drinkRecord.organizationId = newId
                    drinkRecord.store()
                }
            } catch (DataAccessException e) {
                println "Error inserting because ${e.message} with ${drinkRecord.abv}"
            }
        }
    }
}
