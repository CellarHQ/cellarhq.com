package com.cellarhq.simpleDB

import com.amazonaws.services.simpledb.model.Item
import com.cellarhq.generated.tables.pojos.Organization
import com.cellarhq.generated.tables.records.OrganizationRecord
import org.jooq.DSLContext
import org.jooq.exception.DataAccessException

import static com.cellarhq.generated.Tables.ORGANIZATION

class SimpleDBBreweryImporter {
    String selectAllBeersQuery = 'select * from cellar_PROD_breweries limit 2500'

    SimpleDBItemRetriever itemRetriever = new SimpleDBItemRetriever()
    SimpleDBToOrganizationMapper organizationMapper = new SimpleDBToOrganizationMapper()
    AmazonHelper helper = new AmazonHelper()

    void importBeersFromS3(DSLContext dslContext) {
        itemRetriever.withEachItem(selectAllBeersQuery) { Item item ->
            Organization organization = organizationMapper.mapItemToOrganization(dslContext, item)

            OrganizationRecord organizationRecord = dslContext.newRecord(ORGANIZATION, organization)
            organizationRecord.reset(ORGANIZATION.ID)
            organizationRecord.reset(ORGANIZATION.DATA)

            if (!organizationRecord.createdDate) organizationRecord.reset(ORGANIZATION.CREATED_DATE)
            if (!organizationRecord.modifiedDate) organizationRecord.reset(ORGANIZATION.MODIFIED_DATE)

            try {
                organizationRecord.store()
            } catch (DataAccessException e) {
                println "Error inserting because ${e.message} with ${organizationRecord}"
            }
        }
    }

}
