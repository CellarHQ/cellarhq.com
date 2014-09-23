package com.cellarhq

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.Tables
import com.cellarhq.generated.tables.pojos.CellaredDrink
import com.cellarhq.generated.tables.records.CellaredDrinkRecord
import com.cellarhq.generated.tables.records.OrganizationRecord
import com.cellarhq.s3.AmazonHelper
import com.cellarhq.s3.S3ItemRetriever
import com.cellarhq.s3.S3ToCellaredDrinkMapper
import com.github.slugify.Slugify
import org.jooq.DSLContext
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.CELLARED_DRINK


class S3CellaredBeerImporter {
    void importCellaredBeers(DSLContext dslContext) {
        String selectAllBeersQuery = 'select * from cellar_PROD_cellars limit 2500'

        S3ItemRetriever s3ItemRetriever = new S3ItemRetriever()
        S3ToCellaredDrinkMapper drinkMapper = new S3ToCellaredDrinkMapper()
        AmazonHelper helper = new AmazonHelper()

        s3ItemRetriever.withEachItem(selectAllBeersQuery) { Item item ->
            CellaredDrink drink = drinkMapper.mapItemToCellaredDrink(dslContext, item)

            CellaredDrinkRecord drinkRecord = dslContext.newRecord(CELLARED_DRINK, drink)
            drinkRecord.reset(CELLARED_DRINK.ID)

            if (!drinkRecord.createdDate) drinkRecord.reset(CELLARED_DRINK.CREATED_DATE)
            if (!drinkRecord.modifiedDate) drinkRecord.reset(CELLARED_DRINK.MODIFIED_DATE)

            try {
                if (drinkRecord.drinkId) {
                    drinkRecord.store()
                }

            } catch (DataAccessException e) {
                println "Error inserting because ${e.message} with ${drinkRecord}"
            }
        }
    }
}
