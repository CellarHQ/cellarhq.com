package com.cellarhq.s3

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.tables.pojos.CellaredDrink
import org.jooq.DSLContext

import static com.cellarhq.generated.Tables.ORGANIZATION
import static com.cellarhq.generated.Tables.DRINK
import static com.cellarhq.generated.Tables.CELLAR

class S3ToCellaredDrinkMapper {
    String attrScreenName = 'screenName'
    String attrBeer = "beer"
    String attrBrewery = "brewery"
    String attrSize = "size"
    String attrQuantity = "quantity"
    String attrBottleDate = "bottleDate"
    String attrNotes = "notes"

    AmazonHelper helper = new AmazonHelper()

    CellaredDrink mapItemToCellaredDrink(DSLContext dslContext, Item item) {
        return new CellaredDrink().with { CellaredDrink self ->
            Integer cellarId = dslContext.select(CELLAR.ID)
                .from(CELLAR)
                .where(
                CELLAR.SCREEN_NAME.eq(
                    helper.getAttribute(item.attributes, 'screenName')))
                .fetchOneInto(Integer)

            self.cellarId = cellarId

            List<Integer> drinkId = dslContext.select(DRINK.ID)
                .from(DRINK)
                .join(ORGANIZATION).onKey()
                .where(
                    DRINK.NAME.eq(
                        helper.getAttribute(item.attributes, attrBeer)).and(
                        ORGANIZATION.NAME.eq(helper.getAttribute(item.attributes, attrBrewery))
                    ))
                .fetchInto(Integer)

            if (drinkId.size() > 0) {
                self.drinkId = drinkId.first()
            }
            //self.bottleDate = helper.getDateAttribute(item.attributes, attrBottleDate)?.toLocalDate()
            self.notes = helper.getAttribute(item.attributes, attrNotes)
            self.quantity  = helper.getNumberAttribute(item.attributes, attrQuantity) ?: 0
            self.size = helper.getAttribute(item.attributes, attrSize)
            self.tradeable = false

            self
        }
    }
}
