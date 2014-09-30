package com.cellarhq

import com.cellarhq.brewerydb.BreweryDbBeerImporter
import com.cellarhq.brewerydb.BreweryDbBreweryImporter
import com.cellarhq.cellars.CellarCountUpdater
import com.cellarhq.cleanup.OrphanedDataRemover
import com.cellarhq.simpleDB.SimpleDBAccountImporter
import com.cellarhq.simpleDB.SimpleDBBeerImporter
import com.cellarhq.simpleDB.SimpleDBBreweryImporter
import com.cellarhq.simpleDB.SimpleDBCellaredBeerImporter
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
class UpdateData {
    public static void main(String[] args) {
        String userName = "cellarhq"
        String password = "cellarhq"
        String url = "jdbc:postgresql://localhost:15432/cellarhq"

        try {
            Class.forName("org.postgresql.ds.PGSimpleDataSource").newInstance()
            Connection conn = DriverManager.getConnection(url, userName, password)

            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES)

            println "Importing breweries"
            new SimpleDBBreweryImporter().importBeersFromS3(create)

            println "Importing beers"
            new SimpleDBBeerImporter().importBeersFromS3(create)

            println "Importing accounts"
            new SimpleDBAccountImporter().importAccountsFromS3(create)

            println "Importing cellared beers"
            new SimpleDBCellaredBeerImporter().importCellaredBeers(create)

            println "Updating stats for users"
            new CellarCountUpdater().updateCounts(create)

            println "Removing unused beers and breweries"
            new OrphanedDataRemover().deleteOrphans(create)

            println "Updating breweries from BreweryDB"
            new BreweryDbBreweryImporter().importBreweriesFromBDB(create)

            println "Updating beers from BreweryDB"
            new BreweryDbBeerImporter().importBreweriesFromBDB(create)

            println "All Done!"
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
