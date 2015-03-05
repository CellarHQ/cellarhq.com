package com.cellarhq.commands

import com.cellarhq.dbimport.brewerydb.BreweryDbBeerImporter
import com.cellarhq.dbimport.brewerydb.BreweryDbBreweryImporter
import com.cellarhq.cellars.CellarCountUpdater
import com.cellarhq.dbimport.cleanup.OrphanedDataRemover
import com.cellarhq.support.DatabaseSupport
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
            println "Updating breweries from BreweryDB"
            new BreweryDbBreweryImporter().importBreweriesFromBDB(create)

            println "Updating beers from BreweryDB"
            new BreweryDbBeerImporter().importBreweriesFromBDB(create)

            println "Updating stats for users"
            new CellarCountUpdater().updateCounts(create)

            println "All Done!"

            return true
        } catch (Exception e) {
            e.printStackTrace()
            return false
        }
    }
}
