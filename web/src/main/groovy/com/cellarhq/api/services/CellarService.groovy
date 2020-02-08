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
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import org.jooq.JoinType
import org.jooq.Record
import org.jooq.SelectJoinStep
import org.jooq.Table
import org.jooq.TransactionalCallable
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.exec.Blocking
import ratpack.exec.Operation
import ratpack.exec.Promise
import ratpack.form.UploadedFile

import javax.sql.DataSource
import java.time.LocalDateTime

import static com.cellarhq.generated.Tables.*

@Slf4j
@CompileStatic
class CellarService extends BaseJooqService {

  private final PhotoService photoService

  @Inject
  CellarService(DataSource dataSource, PhotoService photoService) {
    super(dataSource)
    this.photoService = photoService
  }

  Promise<Cellar> save(Cellar cellar) {
   Blocking.get {
      jooq { DSLContext create ->
        CellarRecord cellarRecord = create.newRecord(CELLAR as Table<CellarRecord>, cellar)

        if (cellarRecord.id) {
          cellarRecord.update()
        } else {
          cellarRecord.reset(CELLAR.ID)
          cellarRecord.store()
        }

        cellarRecord.into(Cellar)
      }
    }
  }

  Promise<Cellar> get(Long id) {
   Blocking.get {
      getBlocking(id)
    }
  }

  Cellar getBlocking(Long id) {
    jooq { DSLContext create ->
      create.select()
        .from(CELLAR)
        .where(CELLAR.ID.eq(id))
        .fetchOneInto(Cellar)
    }
  }

  Promise<Cellar> findBySlug(String slug) {
   findBySlugPromise(slug)
  }

  Promise<Cellar> findBySlugPromise(String slug) {
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

  Promise<List<Cellar>> all(SortCommand sortCommand = null, int numberOfRows = 20, int offset = 0) {
    Blocking.get {
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
    }
  }

  Promise<List<Cellar>> search(String searchTerm, SortCommand sortCommand, int numberOfRows = 20, int offset = 0) {
    Blocking.get {
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
    }
  }

  Promise<Cellar> validateScreenName(String screenName) {
   Blocking.get {
      jooq { DSLContext create ->
        create.select()
          .from(CELLAR)
          .where(CELLAR.SCREEN_NAME.equalIgnoreCase(screenName))
          .limit(1)
          .fetchOneInto(Cellar)
      }
    }
  }

  Promise<Integer> count(String searchTerm = null) {
   Blocking.get {
      jooq { DSLContext create ->
        SelectJoinStep select = create.selectCount()
          .from(CELLAR)

        if (searchTerm) {
          select.where(CELLAR.SCREEN_NAME.likeIgnoreCase("%${searchTerm}%"))
        }

        select.fetchOneInto(Integer)
      }
    }
  }

  Cellar saveBlocking(Cellar cellar, UploadedFile photo = null) {
    jooq { DSLContext create ->
      create.transactionResult({
        CellarRecord cellarRecord = create.newRecord(CELLAR as Table<CellarRecord>, cellar)

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
        return cellarRecord.into(Cellar)
      } as TransactionalCallable)
    } as Cellar
  }

  void updateLoginStats(Cellar cellar, TwitterProfile twitterProfile = null) {
    cellar.lastLogin = LocalDateTime.now()
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
        create.delete(CELLARED_DRINK as Table).where(CELLARED_DRINK.CELLAR_ID.eq(cellar.id)).execute()
        create.delete(ACCOUNT_EMAIL  as Table).where(ACCOUNT_EMAIL.CELLAR_ID.eq(cellar.id)).execute()
        create.delete(ACCOUNT_OAUTH  as Table).where(ACCOUNT_OAUTH.CELLAR_ID.eq(cellar.id)).execute()
        create.delete(CELLAR as Table).where(CELLAR.ID.eq(cellar.id)).execute()
      }
    }
  }

  Operation emptyCellar(Cellar cellar) {
    Blocking.op {
      jooq { DSLContext create ->
        create.delete(CELLARED_DRINK as Table).where(CELLARED_DRINK.CELLAR_ID.eq(cellar.id)).execute()
      }
    }
  }
}
