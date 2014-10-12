package com.cellarhq.dbimport.simpleDB

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.tables.pojos.Drink
import com.cellarhq.generated.tables.records.StyleRecord
import com.github.slugify.Slugify
import org.joda.time.DateTime
import org.jooq.DSLContext

import java.math.RoundingMode
import java.sql.Timestamp

import static com.cellarhq.generated.Tables.*

class SimpleDBToDrinkMapper {
    static final String attrStyle = "style"
    static final String attrBrewery = "brewery"
    static final String attrGlassware = "glassware"
    static final String attrName = "name"
    static final String attrSrm = "srm"
    static final String attrAbv = "abv"
    static final String attrIbu = "ibu"
    static final String attrAvailability = "availability"
    static final String attrCreated = "created"
    static final String attrUpdated = "updated"
    static final String attrBrewerydbId = "brewerydbId"
    static final String attrBrewerydbLastUpdate = "brewerydbLastUpdate"

    Drink mapItemToDrink(DSLContext dslContext, Item item) {
        AmazonHelper helper = new AmazonHelper()

        Drink d = new Drink().with { Drink self ->

            String styleName = helper.getAttribute(item.attributes, attrStyle).trim()
            String glasswareName = helper.getAttribute(item.attributes, attrGlassware).trim()
            String organizationName = helper.getAttribute(item.attributes, attrBrewery).trim()
            Integer styleId = dslContext.select(STYLE.ID)
                .from(STYLE)
                .where(STYLE.NAME.equalIgnoreCase(styleName))
                .fetchOneInto(Integer)

            // This data will need cleaned for sure. Anything over 100 I am assuming is spam.
            if (!styleId && styleName && styleName.length() < 101) {
                println "Could not find style ${styleName}, adding"

                StyleRecord styleRecord =  dslContext.newRecord(STYLE)
                styleRecord.name = styleName
                styleRecord.categoryId = 13
                styleRecord.store()
                styleId = styleRecord.id
            }

            Integer glasswareId = dslContext.select(GLASSWARE.ID)
                .from(GLASSWARE)
                .where(GLASSWARE.NAME.equalIgnoreCase(glasswareName))
                .fetchOneInto(Integer)

            if (!glasswareId && glasswareName) {
                println "Could not find glass ${glasswareName}"
            }

            String searchSlug = new Slugify().slugify(organizationName)
            Integer organizationId = dslContext.select(ORGANIZATION.ID)
                .from(ORGANIZATION)
                .where(ORGANIZATION.SLUG.eq(searchSlug))
                .fetchOneInto(Integer)

            self.organizationId = organizationId
            self.styleId = styleId
            self.glasswareId = glasswareId
            self.drinkType = 'BEER'

            String name = helper.getAttribute(item.attributes, attrName)

            if (name.size() > 98) {
                name = name.substring(0,97)
            }
            self.slug = determineSlug(dslContext, name)
            self.name = name
            self.description = helper.buildDescription(item.attributes)
            self.srm = helper.getNumberAttribute(item.attributes, attrSrm)
            self.ibu = helper.getNumberAttribute(item.attributes, attrIbu)
            self.abv = helper.getBigDecimalAttribute(item.attributes, attrAbv)?.setScale(3, RoundingMode.FLOOR)?.min(25.00)
            self.availability = helper.getAttribute(item.attributes, attrAvailability)
            self.searchable = true
            self.breweryDbId = helper.getAttribute(item.attributes, attrBrewerydbId)
            self.warningFlag = false
            self.cellaredBeers = 0
            self.containedInCellars = 0
            self.tradableBeers = 0

            DateTime breweryDbUpdatedTime = helper.getDateAttribute(item.attributes, attrBrewerydbLastUpdate)

            if (breweryDbUpdatedTime) {
                self.breweryDbLastUpdated = new Timestamp(breweryDbUpdatedTime.getMillis())
            }

            self.locked = self.breweryDbId as boolean
            self.needsModeration = false

            DateTime created = helper.getDateAttribute(item.attributes, attrCreated)
            if (created) {
                self.createdDate = new Timestamp(created.millis)
            }

            DateTime updated = helper.getDateAttribute(item.attributes, attrUpdated)
            if (updated) {
                self.modifiedDate = new Timestamp(updated.millis)
            }

            self
        }

        return d
    }

    private String determineSlug(DSLContext context, String name) {

        String originalslug = new Slugify().slugify(name)
        String slug = originalslug
        Integer iteration = 1

        while (true) {
            Integer count = context.selectCount().from(DRINK).where(DRINK.SLUG.eq(slug)).fetchOneInto(Integer)
            if (count > 0) {
                iteration++
                slug = "${originalslug}-${iteration}"
            } else {
                if (iteration == 1) return originalslug

                return slug
            }
        }
    }
}
