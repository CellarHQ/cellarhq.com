package com.cellarhq.webapp

import com.cellarhq.api.services.CellarService
import com.cellarhq.api.services.CellaredDrinkService
import com.cellarhq.auth.profiles.CellarHQProfile
import com.cellarhq.auth.services.photo.PhotoService
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.CellaredDrink
import com.cellarhq.domain.Photo
import com.cellarhq.domain.views.CellaredDrinkDetails
import com.cellarhq.jooq.SortCommand
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import ratpack.func.Pair
import ratpack.groovy.handling.GroovyChainAction

import static ratpack.handlebars.Template.handlebarsTemplate

@Slf4j
class YourCellarEndpoint extends GroovyChainAction {

  private final CellarService cellarService
  private final CellaredDrinkService cellaredDrinkService
  private final PhotoService photoService
  private final BinStatsService binStatsService

  @Inject
  YourCellarEndpoint(CellarService cellarService,
                     CellaredDrinkService cellaredDrinkService,
                     PhotoService photoService,
                     BinStatsService binStatsService) {
    this.cellarService = cellarService
    this.cellaredDrinkService = cellaredDrinkService
    this.photoService = photoService
    this.binStatsService = binStatsService
  }

  @Override
  void execute() throws Exception {
    get('yourcellar') { CellarHQProfile profile ->
      Long cellarId = profile.cellarId

      cellarService.get(cellarId).flatRight {
        cellaredDrinkService.all(cellarId, SortCommand.fromRequest(request))
      }.flatRight {
        photoService.findByCellarId(cellarId)
      }.then { Pair<Pair<Cellar, List<CellaredDrink>>, Photo> pair ->
        if (pair.left.left == null) {
          log.error(LogUtil.toLog('YourCellar', [
            msg: 'Could not locate a cellar by user session id',
            id : cellarId
          ]))
          clientError 404
        } else {
          render handlebarsTemplate('cellars/show-cellar.html',
            [cellar            : pair.left.left,
             photo             : pair.right,
             cellaredDrinks    : pair.left.right,
             usesBinIdentifiers: binStatsService.binsPresent(pair.left.right),
             binStats          : binStatsService.calculateBinStats(pair.left.right),
             self              : true,
             title             : 'CellarHQ : Your Cellar',
             pageId            : 'yourcellar']
          )
        }
      }
    }

    get('yourarchive') { CellarHQProfile profile ->
      Long cellarId = profile.cellarId
      cellarService.get(cellarId).flatRight {
        cellaredDrinkService.archive(cellarId, SortCommand.fromRequest(request))
      }.flatRight {
        photoService.findByCellarId(cellarId)
      }.then { Pair<Pair<Cellar, List<CellaredDrinkDetails>>, Photo> pair ->
        if (!pair.left.left) {
          log.error(LogUtil.toLog('YourCellar', [
            msg: 'Could not locate a cellar by user session id',
            id : cellarId
          ]))
          clientError 404
        } else {
          render handlebarsTemplate('cellars/show-archive.html',
            [
              cellar : pair.left.left,
              photo  : pair.right,
              archive: pair.left.right,
              self   : true,
              title  : 'CellarHQ : Your Cellar',
              pageId : 'yourarchive'
            ]
          )
        }
      }
    }
  }

}
