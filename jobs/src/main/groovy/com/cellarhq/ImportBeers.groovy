package com.cellarhq

import com.cellarhq.brewerydb.BreweryDbBreweryImporter
import com.cellarhq.s3.S3AccountImporter
import com.cellarhq.s3.S3BeerImporter
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL

import java.sql.Connection
import java.sql.DriverManager

/**
 * Main Class for importing beers and cellared beers from simple db.
 */
@Slf4j
@CompileStatic
class ImportBeers {
    public static void main(String[] args) {
        String userName = "cellarhq"
        String password = "cellarhq"
        String url = "jdbc:postgresql://localhost:15432/cellarhq"

        try {
            Class.forName("org.postgresql.ds.PGSimpleDataSource").newInstance()
            Connection conn = DriverManager.getConnection(url, userName, password)

            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES)

            new BreweryDbBreweryImporter().importBreweriesFromBDB(create)
            new S3BeerImporter().importBeersFromS3(create)
            new S3AccountImporter().importAccountsFromS3(create)
            new S3CellaredBeerImporter().importCellaredBeers(create)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
