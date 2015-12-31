package com.cellarhq.commands

import com.cellarhq.cellars.CellarCountUpdater
import com.cellarhq.dbimport.brewerydb.BreweryDbBeerImporter
import com.cellarhq.dbimport.brewerydb.BreweryDbBreweryImporter
import com.cellarhq.support.DatabaseSupport
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
