package com.cellarhq.s3

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.Keys
import com.cellarhq.generated.tables.pojos.CellaredDrink
import com.github.slugify.Slugify
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
    Slugify slugmaker = new Slugify()

    CellaredDrink mapItemToCellaredDrink(DSLContext dslContext, Item item) {
        return new CellaredDrink().with { CellaredDrink self ->
            Integer cellarId = dslContext.select(CELLAR.ID)
                .from(CELLAR)
                .where(
                CELLAR.SCREEN_NAME.eq(
                    helper.getAttribute(item.attributes, 'screenName')))
                .fetchOneInto(Integer)

            self.cellarId = cellarId

            String drinkName = helper.getAttribute(item.attributes, attrBeer)
            String organizationName = helper.getAttribute(item.attributes, attrBrewery)
            Integer drinkId = findDrinkId(dslContext, drinkName, organizationName)

            if (!drinkId) {
                println "Could not find drink: ${drinkName} - ${organizationName}"
            }

            self.drinkId = drinkId
            //self.bottleDate = helper.getDateAttribute(item.attributes, attrBottleDate)?.toLocalDate()
            self.notes = helper.getAttribute(item.attributes, attrNotes)
            self.quantity  = helper.getNumberAttribute(item.attributes, attrQuantity) ?: 0
            self.size = helper.getAttribute(item.attributes, attrSize)
            self.tradeable = false

            self
        }
    }

    private Integer findDrinkId(DSLContext dslContext, String beerName, String breweryName) {

        String drinkSlug = slugmaker.slugify(beerName)
        String organizationSlug = slugmaker.slugify(breweryName)

        List<Integer> drinkId = dslContext.select(DRINK.ID)
            .from(DRINK)
            .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
            .where(
            DRINK.NAME.likeIgnoreCase(beerName)
                .and(ORGANIZATION.NAME.equalIgnoreCase(breweryName)))
            .fetchInto(Integer)

        if (drinkId.size() > 0) {
            return drinkId.first()
        }

        drinkId = dslContext.select(DRINK.ID)
            .from(DRINK)
            .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
            .where(
            DRINK.SLUG.equalIgnoreCase(drinkSlug)
                .and(ORGANIZATION.SLUG.equalIgnoreCase(organizationSlug)))
            .fetchInto(Integer)

        if (drinkId.size() > 0) {
            return drinkId.first()
        }

        drinkId = dslContext.select(DRINK.ID)
            .from(DRINK)
            .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
            .where(
            DRINK.SLUG.equalIgnoreCase(drinkSlug))
            .fetchInto(Integer)

        if (drinkId.size() ==  1) {
            return drinkId.first()
        }

        drinkId = dslContext.select(DRINK.ID)
            .from(DRINK)
            .join(ORGANIZATION).onKey(Keys.DRINK__FK_DRINK_ORGANIZATION_ID)
            .where(
            DRINK.SLUG.like('%' + drinkSlug + '%')
                .and(ORGANIZATION.SLUG.equalIgnoreCase(organizationSlug)))
            .fetchInto(Integer)

        if (drinkId.size() > 0) {
            return drinkId.first()
        }

        return null

    }
}
