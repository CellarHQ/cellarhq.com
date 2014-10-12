package com.cellarhq.dbimport.simpledb

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.commands.support.ProgressSupport
import com.cellarhq.generated.tables.pojos.CellaredDrink
import com.cellarhq.generated.tables.records.CellaredDrinkRecord
import org.jooq.DSLContext
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.CELLARED_DRINK


class SimpleDBCellaredBeerImporter implements ProgressSupport {
    void importCellaredBeers(DSLContext dslContext) {
        String selectAllBeersQuery = 'select * from cellar_PROD_cellars limit 2500'

        SimpleDBItemRetriever itemRetriever = new SimpleDBItemRetriever()
        SimpleDBToCellaredDrinkMapper drinkMapper = new SimpleDBToCellaredDrinkMapper()
        AmazonHelper helper = new AmazonHelper()

        itemRetriever.withEachItem(selectAllBeersQuery) { Item item ->
            CellaredDrink drink = drinkMapper.mapItemToCellaredDrink(dslContext, item)

            CellaredDrinkRecord drinkRecord = dslContext.newRecord(CELLARED_DRINK, drink)
            drinkRecord.reset(CELLARED_DRINK.ID)

            if (!drinkRecord.createdDate) drinkRecord.reset(CELLARED_DRINK.CREATED_DATE)
            if (!drinkRecord.modifiedDate) drinkRecord.reset(CELLARED_DRINK.MODIFIED_DATE)

            try {
                if (drinkRecord.drinkId) {
                    drinkRecord.store()
                    incrementProgressAnts()
                }

            } catch (DataAccessException e) {
                println "Error inserting because ${e.message} with ${drinkRecord}"
            }
        }
    }
}
