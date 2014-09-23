package com.cellarhq.s3

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.tables.pojos.Organization
import com.github.slugify.Slugify
import org.jooq.DSLContext

class S3ToOrganizationMapper {
    static String attrName = "name"
    static String attrPhotoUrl = "photoUrl"
    static String attrPhotoFullSizeUrl = "photoFullSizeUrl"
    static String attrPhotoScreenName = "photoScreenName"
    static String attrPhotoCreated = "photoCreated"
    static String attrEstablished = "established"
    static String attrWebsite = "website"
    static String attrPhone = "phone"
    static String attrAddress = "address"
    static String attrExtendedAddress = "extendedAddress"
    static String attrLocality = "locality"
    static String attrRegion = "region"
    static String attrPostalCode = "postalCode"
    static String attrCountry = "country"
    static String attrSearchable = "searchable"
    static String attrCreated = "created"
    static String attrUpdated = "updated"
    static String attrNeedsModeration = "needsModeration"
    static String attrBrewerydbId = "brewerydbId"
    static String attrBrewerydbLastUpdate = "brewerydbLastUpdate"

    Organization mapItemToOrganization(DSLContext dslContext, Item item) {
        AmazonHelper helper = new AmazonHelper()

        Organization d = new Organization().with { Organization self ->
            self.name = helper.getAttribute(item.attributes, attrName).trim()
            self.address = helper.getAttribute(item.attributes, attrAddress).trim()
            self.address2 = helper.getAttribute(item.attributes, attrExtendedAddress).trim()
            self.country = helper.getAttribute(item.attributes, attrCountry).trim()
            self.breweryDbId = helper.getAttribute(item.attributes, attrBrewerydbId).trim()


            self.established = helper.getNumberAttribute(item.attributes, attrEstablished)

            self.description = helper.buildDescription(item.attributes)
            self.locality = helper.getAttribute(item.attributes, attrLocality).trim()
            self.region = helper.getAttribute(item.attributes, attrRegion).trim()
            self.phone = helper.getAttribute(item.attributes, attrPhone).trim()
            self.slug = new Slugify().slugify(self.name)
            self.postalCode = helper.getAttribute(item.attributes, attrPostalCode).trim()
            self.website = helper.getAttribute(item.attributes, attrWebsite).trim()
            self.type = 'BREWERY'
            self.searchable = true
            self.locked = false
            self.needsModeration = false

            return self
        }
    }
}
