package com.cellarhq.services

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observe
import static ratpack.rx.RxRatpack.observeEach

import com.cellarhq.domain.Cellar
import com.cellarhq.domain.Photo
import com.cellarhq.generated.tables.records.CellarRecord
import com.cellarhq.generated.tables.records.PhotoRecord
import com.cellarhq.services.photo.PhotoService
import com.cellarhq.services.photo.model.ResizeCommand
import com.cellarhq.services.photo.writer.PhotoWriteFailedException
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import org.jooq.JoinType
import org.jooq.Record
import org.jooq.SelectJoinStep
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.exec.ExecControl
import ratpack.form.UploadedFile

import javax.sql.DataSource
import java.sql.Timestamp
import java.time.LocalDateTime

@Slf4j
@CompileStatic
class CellarService extends BaseJooqService {

    private final PhotoService photoService

    @Inject
    CellarService(DataSource dataSource, ExecControl execControl, PhotoService photoService) {
        super(dataSource, execControl)
        this.photoService = photoService
    }

    rx.Observable<Cellar> save(Cellar cellar) {
        observe(execControl.blocking {
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
        observe(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                    .from(CELLAR)
                    .where(CELLAR.ID.eq(id))
                    .fetchOneInto(Cellar)
            }
        }).asObservable()
    }

    rx.Observable<Cellar> findBySlug(String slug) {
        observe(execControl.blocking {
            jooq { DSLContext create ->
                Record record = create.select()
                        .from(CELLAR)
                        .join(PHOTO, JoinType.LEFT_OUTER_JOIN).onKey()
                        .where(CELLAR.SCREEN_NAME.eq(slug))
                        .fetchOne()

                if (record) {
                    Cellar cellar = record.into(Cellar)
                    cellar.photo = record.into(Photo)
                    return cellar
                }
                return (Cellar) null
            }
        }).asObservable()
    }

    rx.Observable<Cellar> all(int numberOfRows = 20, int offset = 0) {
        observeEach(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                        .from(CELLAR)
                        .orderBy(CELLAR.SCREEN_NAME)
                        .limit(offset, numberOfRows)
                        .fetchInto(Cellar)
            }
        })
    }

    rx.Observable<Cellar> search(String searchTerm, int numberOfRows = 20, int offset = 0) {
        observeEach(execControl.blocking {
            jooq { DSLContext create ->
                create.select()
                    .from(CELLAR)
                    .where(CELLAR.SCREEN_NAME.likeIgnoreCase("%${searchTerm}%"))
                    .orderBy(CELLAR.SCREEN_NAME)
                    .limit(offset, numberOfRows)
                    .fetchInto(Cellar)
            }
        })
    }

    rx.Observable<Integer> count(String searchTerm = null) {
        observe(execControl.blocking {
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

    Cellar getBlocking(Long id) {
        (Cellar) jooq { DSLContext create ->
            create.select()
                    .from(CELLAR)
                    .where(CELLAR.ID.eq(id))
                    .fetchOneInto(Cellar)
        }
    }

    Cellar saveBlocking(Cellar cellar, UploadedFile photo = null) {
        jooq { DSLContext create ->
            create.transactionResult {
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
}
