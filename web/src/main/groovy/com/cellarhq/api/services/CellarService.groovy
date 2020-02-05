package com.cellarhq.api.services

import com.cellarhq.auth.services.photo.PhotoService
import com.cellarhq.auth.services.photo.model.ResizeCommand
import com.cellarhq.auth.services.photo.writer.PhotoWriteFailedException
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.Photo
import com.cellarhq.generated.tables.records.CellarRecord
import com.cellarhq.generated.tables.records.PhotoRecord
import com.cellarhq.jooq.BaseJooqService
import com.cellarhq.jooq.SortCommand
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import org.jooq.JoinType
import org.jooq.Record
import org.jooq.SelectJoinStep
import org.jooq.TransactionalCallable
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise
import ratpack.form.UploadedFile

import javax.sql.DataSource
import java.sql.Timestamp
import java.time.LocalDateTime

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observe
import static ratpack.rx.RxRatpack.observeEach

@Slf4j
class CellarService extends BaseJooqService {

  private final PhotoService photoService

  @Inject
  CellarService(DataSource dataSource, PhotoService photoService) {
    super(dataSource)
    this.photoService = photoService
  }

  rx.Observable<Cellar> save(Cellar cellar) {
    observe(Blocking.get {
      jooq { DSLContext create ->
        CellarRecord cellarRecord = create.newRecord(CELLAR, cellar)

        if (cellarRecord.id) {
          cellarRecord.update()
        } else {
          cellarRecord.reset(CELLAR.ID)
          cellarRecord.store()
        }

        cellarRecord.into(Cellar)
      }
    }).asObservable()
  }

  rx.Observable<Cellar> get(Long id) {
    observe(Blocking.get {
      getBlocking(id)
    }).asObservable()
  }

  Cellar getBlocking(Long id) {
    jooq { DSLContext create ->
      create.select()
        .from(CELLAR)
        .where(CELLAR.ID.eq(id))
        .fetchOneInto(Cellar)
    }
  }

  rx.Observable<Cellar> findBySlug(String slug) {
    observe(findBySlugPromise(slug)).asObservable()
  }

  Promise<Cellar> findBySlugPromise(slug) {
    return Blocking.get {
      jooq { DSLContext create ->
        Record record = create.select()
          .from(CELLAR)
          .join(PHOTO, JoinType.LEFT_OUTER_JOIN).onKey()
          .where(CELLAR.SLUG.equalIgnoreCase(slug))
          .fetchOne()

        if (record) {
          Cellar cellar = record.into(Cellar)
          cellar.photo = record.into(Photo)
          cellar.id = record.getValue(CELLAR.ID)
          return cellar
        }
        return (Cellar) null
      }
    }
  }

  rx.Observable<Cellar> all(SortCommand sortCommand = null, int numberOfRows = 20, int offset = 0) {
    observeEach(Blocking.get {
      jooq { DSLContext create ->
        create.select()
          .from(CELLAR)
          .where(CELLAR.PRIVATE.isFalse())
          .orderBy(makeSortField(sortCommand, CELLAR.SCREEN_NAME, [
          name       : CELLAR.SCREEN_NAME,
          totalBeers : CELLAR.TOTAL_BEERS,
          uniqueBeers: CELLAR.UNIQUE_BEERS,
          breweries  : CELLAR.UNIQUE_BREWERIES
        ]))
          .limit(offset, numberOfRows)
          .fetchInto(Cellar)
      }
    })
  }

  rx.Observable<Cellar> search(String searchTerm, SortCommand sortCommand, int numberOfRows = 20, int offset = 0) {
    observeEach(Blocking.get {
      jooq { DSLContext create ->
        create.select()
          .from(CELLAR)
          .where(CELLAR.SCREEN_NAME.likeIgnoreCase("%${searchTerm}%").and(CELLAR.PRIVATE.isFalse()))
          .orderBy(makeSortField(sortCommand, CELLAR.SCREEN_NAME, [
          name       : CELLAR.SCREEN_NAME,
          totalBeers : CELLAR.TOTAL_BEERS,
          uniqueBeers: CELLAR.UNIQUE_BEERS,
          breweries  : CELLAR.UNIQUE_BREWERIES
        ]))
          .limit(offset, numberOfRows)
          .fetchInto(Cellar)
      }
    })
  }

  rx.Observable<Cellar> validateScreenName(String screenName) {
    observe(Blocking.get {
      jooq { DSLContext create ->
        create.select()
          .from(CELLAR)
          .where(CELLAR.SCREEN_NAME.equalIgnoreCase(screenName))
          .limit(1)
          .fetchOneInto(Cellar)
      }
    })
  }

  rx.Observable<Integer> count(String searchTerm = null) {
    observe(Blocking.get {
      jooq { DSLContext create ->
        SelectJoinStep select = create.selectCount()
          .from(CELLAR)

        if (searchTerm) {
          select.where(CELLAR.SCREEN_NAME.likeIgnoreCase("%${searchTerm}%"))
        }

        select.fetchOneInto(Integer)
      }
    })
  }

  Cellar saveBlocking(Cellar cellar, UploadedFile photo = null) {
    jooq { DSLContext create ->
      create.transactionResult { TransactionalCallable transactionalCallable ->
        CellarRecord cellarRecord = create.newRecord(CELLAR, cellar)

        if (photo?.fileName) {
          try {
            PhotoRecord photoRecord = photoService.createPhotoRecord(create, Photo.Type.CELLAR, photo, [
              new ResizeCommand(Photo.Size.THUMB, 64),
              new ResizeCommand(Photo.Size.LARGE, 256)
            ])
            photoRecord.description = Photo.DESCRIPTION_SETTINGS_UPLOAD
            photoRecord.store()
            cellarRecord.photoId = photoRecord.id
          } catch (PhotoWriteFailedException e) {
            log.error(LogUtil.toLog('CellarService', [
              msg: 'Photo write failed while saving cellar'
            ]), e)
          }
        }

        if (cellar.id) {
          create.executeUpdate(cellarRecord)
        } else {
          create.executeInsert(cellarRecord)
        }
        cellarRecord.into(Cellar)
      }
    }
  }

  void updateLoginStats(Cellar cellar, TwitterProfile twitterProfile = null) {
    cellar.lastLogin = Timestamp.valueOf(LocalDateTime.now())
    if (cellar.updateFromNetwork && twitterProfile) {
      cellar.with {
        displayName = twitterProfile.displayName
        location = twitterProfile.location
        website = twitterProfile.profileUrl
        bio = twitterProfile.description
      }
    }
    saveBlocking(cellar)
  }

  Operation deleteCellar(Cellar cellar) {
    Blocking.op {
      jooq { DSLContext create ->
        //## Deleting source roles
        //## Deleting source password recovery requests
        //## Deleting source accounts
        //## Deleting source cellar
        create.delete(CELLARED_DRINK).where(CELLARED_DRINK.CELLAR_ID.eq(cellar.id)).execute()
        create.delete(ACCOUNT_EMAIL).where(ACCOUNT_EMAIL.CELLAR_ID.eq(cellar.id)).execute()
        create.delete(ACCOUNT_OAUTH).where(ACCOUNT_OAUTH.CELLAR_ID.eq(cellar.id)).execute()
        create.delete(CELLAR).where(CELLAR.ID.eq(cellar.id)).execute()
      }
    }
  }

  Operation emptyCellar(Cellar cellar) {
    Blocking.op {
      jooq { DSLContext create ->
        create.delete(CELLARED_DRINK).where(CELLARED_DRINK.CELLAR_ID.eq(cellar.id)).execute()
      }
    }
  }
}
