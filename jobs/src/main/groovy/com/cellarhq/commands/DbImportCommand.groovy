package com.cellarhq.commands

import com.cellarhq.dbimport.brewerydb.BreweryDbBeerImporter
import com.cellarhq.dbimport.brewerydb.BreweryDbBreweryImporter
import com.cellarhq.cellars.CellarCountUpdater
import com.cellarhq.dbimport.cleanup.OrphanedDataRemover
import com.cellarhq.commands.support.DatabaseSupport
import com.cellarhq.dbimport.simpledb.SimpleDBAccountImporter
import com.cellarhq.dbimport.simpledb.SimpleDBBeerImporter
import com.cellarhq.dbimport.simpledb.SimpleDBBreweryImporter
import com.cellarhq.dbimport.simpledb.SimpleDBCellaredBeerImporter
import groovy.transform.CompileStatic

@CompileStatic
class DbImportCommand implements NamedCommand, DatabaseSupport {

    @Override
    boolean run() {
        try {
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

            return true
        } catch (Exception e) {
            e.printStackTrace()
            return false
        }
    }
}
