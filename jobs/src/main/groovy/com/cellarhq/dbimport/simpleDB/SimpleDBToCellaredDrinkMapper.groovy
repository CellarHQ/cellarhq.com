package com.cellarhq.dbimport.simpledb

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.Keys
import com.cellarhq.generated.tables.pojos.CellaredDrink
import com.github.slugify.Slugify
import org.jooq.DSLContext

import java.time.LocalDate

import static com.cellarhq.generated.Tables.ORGANIZATION
import static com.cellarhq.generated.Tables.DRINK
import static com.cellarhq.generated.Tables.CELLAR

class SimpleDBToCellaredDrinkMapper {
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

            if (drinkName.length() > 100) {
                drinkName = drinkName.substring(0,99)
            }

            if (organizationName.length() > 100) {
                organizationName = organizationName.substring(0,99)
            }


            Integer drinkId = findDrinkId(dslContext, drinkName, organizationName)

            if (!drinkId) {
                drinkId = insertDrink(drinkName, organizationName, dslContext)
            }

            self.drinkId = drinkId

            org.joda.time.LocalDate parsedDate = helper.getDateAttribute(item.attributes, attrBottleDate)?.toLocalDate()

            if (parsedDate) {
                LocalDate bottledDate = new LocalDate(parsedDate.year, parsedDate.monthOfYear, parsedDate.dayOfMonth)
                self.bottleDate = bottledDate
            }
            self.notes = helper.getAttribute(item.attributes, attrNotes)
            self.quantity  = helper.getNumberAttribute(item.attributes, attrQuantity) ?: 0
            self.size = helper.getAttribute(item.attributes, attrSize)
            self.tradeable = false

            self
        }
    }

    private int insertDrink(String beerName, String breweryName, DSLContext dslContext) {
        String searchSlug = new Slugify().slugify(breweryName)
        Integer organizationId = dslContext.select(ORGANIZATION.ID)
            .from(ORGANIZATION)
            .where(ORGANIZATION.SLUG.eq(searchSlug))
            .fetchOneInto(Integer)

        if (!organizationId) {
            organizationId = new LastResort().insertOrganization(breweryName, dslContext).id
        }

        new LastResort().insertDrink(beerName, organizationId, dslContext).id
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
