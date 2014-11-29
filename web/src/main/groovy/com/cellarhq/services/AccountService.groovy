package com.cellarhq.services

import static com.cellarhq.generated.Tables.*

import com.cellarhq.auth.PasswordService
import com.cellarhq.auth.Role
import com.cellarhq.domain.OAuthClient
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.domain.Photo
import com.cellarhq.generated.tables.records.AccountEmailRecord
import com.cellarhq.generated.tables.records.AccountOauthRecord
import com.cellarhq.generated.tables.records.CellarRecord
import com.cellarhq.generated.tables.records.CellarRoleRecord
import com.cellarhq.generated.tables.records.PasswordChangeRequestRecord
import com.cellarhq.generated.tables.records.PhotoRecord
import com.cellarhq.services.photo.PhotoService
import com.cellarhq.services.photo.writer.PhotoWriteFailedException
import com.cellarhq.services.photo.model.ResizeCommand
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.exception.DataAccessException
import org.jooq.impl.DSL
import ratpack.exec.ExecControl
import ratpack.form.UploadedFile

import javax.sql.DataSource
import java.sql.Timestamp
import java.time.LocalDateTime

@Slf4j
@CompileStatic
class AccountService extends BaseJooqService {

    private final PhotoService photoService
    private final PasswordService passwordService

    @Inject
    AccountService(DataSource dataSource,
                   ExecControl execControl,
                   PhotoService photoService,
                   PasswordService passwordService) {
        super(dataSource, execControl)
        this.photoService = photoService
        this.passwordService = passwordService
    }

    @Deprecated
    OAuthAccount findByCredentials(String username, OAuthClient client = OAuthClient.TWITTER) {
        (OAuthAccount) jooq { DSLContext create ->
            Record result = create.select()
                    .from(ACCOUNT_OAUTH)
                    .join(CELLAR).onKey()
                    .where(ACCOUNT_OAUTH.CLIENT.eq(client.toString())
                        .and(ACCOUNT_OAUTH.USERNAME.equalIgnoreCase(username)))
                    .fetchOne()

            if (result) {
                OAuthAccount oAuthAccount = result.into(OAuthAccount)
                oAuthAccount.cellar = result.into(Cellar)
                return oAuthAccount
            }
            return null
        }
    }

    EmailAccount findByEmail(String email) {
        (EmailAccount) jooq { DSLContext create ->
            create.select()
                .from(ACCOUNT_EMAIL)
                .where(ACCOUNT_EMAIL.EMAIL.equalIgnoreCase(email))
                .fetchOneInto(EmailAccount)
        }
    }

    EmailAccount findByEmailWithCellar(String email) {
        (EmailAccount) jooq { DSLContext create ->
            Record result = create.select()
                    .from(ACCOUNT_EMAIL)
                    .join(CELLAR).onKey()
                    .where(ACCOUNT_EMAIL.EMAIL.equalIgnoreCase(email))
                    .fetchOne()

            if (result) {
                EmailAccount emailAccount = result.into(EmailAccount)
                emailAccount.cellar = result.into(Cellar)
                return emailAccount
            }
            return null
        }
    }

    EmailAccount create(EmailAccount emailAccount, UploadedFile picture) {
        emailAccount.password = passwordService.hashPassword(emailAccount.password)
        (EmailAccount) jooq { DSLContext create ->
            create.transactionResult {
                CellarRecord cellarRecord = create.newRecord(CELLAR, emailAccount.cellar)

                if (picture?.fileName) {
                    PhotoRecord photoRecord = new PhotoRecordBuilder(picture).makePhoto(create)
                    if (photoRecord) {
                        photoRecord.description = Photo.DESCRIPTION_SETTINGS_UPLOAD
                        photoRecord.store()
                        cellarRecord.photoId = photoRecord.id
                    }
                }

                cellarRecord.reset(CELLAR.ID)
                cellarRecord.store()
                emailAccount.cellarId = cellarRecord.id

                bindRoleToCellar(create, Role.MEMBER, cellarRecord.id)

                AccountEmailRecord accountEmailRecord = create.newRecord(ACCOUNT_EMAIL, emailAccount)
                accountEmailRecord.reset(ACCOUNT_EMAIL.ID)
                accountEmailRecord.store()

                EmailAccount resultEmailAccount = accountEmailRecord.into(EmailAccount)
                resultEmailAccount.cellar = cellarRecord.into(Cellar)
                resultEmailAccount.id ? resultEmailAccount : null
            }
        }
    }

    OAuthAccount create(OAuthAccount oAuthAccount, String pictureUrl) {
        (OAuthAccount) jooq { DSLContext create ->
            create.transactionResult {
                CellarRecord cellarRecord = create.newRecord(CELLAR, oAuthAccount.cellar)

                maybeLinkTwitterPhotoRecord(create, cellarRecord, pictureUrl)

                cellarRecord.reset(CELLAR.ID)
                cellarRecord.store()
                oAuthAccount.cellarId = cellarRecord.id

                bindRoleToCellar(create, Role.MEMBER, cellarRecord.id)

                AccountOauthRecord record = create.newRecord(ACCOUNT_OAUTH, oAuthAccount)
                record.reset(ACCOUNT_OAUTH.ID)
                record.store()

                OAuthAccount resultOAuthAccount = record.into(OAuthAccount)
                resultOAuthAccount.cellar = cellarRecord.into(Cellar)
                resultOAuthAccount.id ? resultOAuthAccount : null
            }
        }
    }

    private void maybeLinkTwitterPhotoRecord(DSLContext create, CellarRecord cellarRecord, String pictureUrl) {
        if (pictureUrl) {
            PhotoRecord photoRecord = new PhotoRecordBuilder(pictureUrl).makePhoto(create)
            if (photoRecord) {
                photoRecord.description = Photo.DESCRIPTION_TWITTER_UPLOAD

                try {
                    photoRecord.store()
                    cellarRecord.photoId = photoRecord.id
                } catch (DataAccessException dae) {
                    if (dae.message.contains('unq_photo_original_url')) {
                        cellarRecord.photoId = create.select(PHOTO.ID)
                                .from(PHOTO)
                                .where(PHOTO.ORIGINAL_URL.eq(pictureUrl))
                                .fetchOneInto(Long)
                    }
                }
            }
        }
    }

    EmailAccount attachEmailAccount(EmailAccount emailAccount, Cellar cellar) {
        jooq { DSLContext create ->
            AccountEmailRecord record = create.newRecord(ACCOUNT_EMAIL, emailAccount)
            record.reset(ACCOUNT_EMAIL.ID)
            record.cellarId = cellar.id
            record.store()

            record.into(EmailAccount)
        }
    }

    OAuthAccount attachOAuthAccount(OAuthAccount oAuthAccount, Cellar cellar) {
        jooq { DSLContext create ->
            AccountOauthRecord record = create.newRecord(ACCOUNT_OAUTH, oAuthAccount)
            record.reset(ACCOUNT_OAUTH.ID)
            record.cellarId = cellar.id
            record.store()

            record.into(OAuthAccount)
        }
    }

    private void bindRoleToCellar(DSLContext create, Role role, long cellarId) {
        CellarRoleRecord cellarRoleRecord = create.newRecord(CELLAR_ROLE)
        cellarRoleRecord.cellarId = cellarId
        cellarRoleRecord.role = role.toString()
        cellarRoleRecord.store()
    }

    void trackFailedLoginAttempt(EmailAccount emailAccount) {
        log.info(LogUtil.toLog('FailedLoginAttempt', [
            emailAccountId: emailAccount.id
        ]))
        jooq { DSLContext create ->
            create.update(ACCOUNT_EMAIL)
                    .set(ACCOUNT_EMAIL.LOGIN_ATTEMPT_COUNTER, ACCOUNT_EMAIL.LOGIN_ATTEMPT_COUNTER.add(1))
                    .set(ACCOUNT_EMAIL.LAST_LOGIN_ATTEMPT, Timestamp.valueOf(LocalDateTime.now()))
                    .where(ACCOUNT_EMAIL.ID.eq(emailAccount.id))
        }
    }

    void resetFailedLoginAttempts(EmailAccount emailAccount) {
        jooq { DSLContext create ->
            create.update(ACCOUNT_EMAIL)
                    .set(ACCOUNT_EMAIL.LOGIN_ATTEMPT_COUNTER, (short) 0)
                    .set(ACCOUNT_EMAIL.LAST_LOGIN_ATTEMPT, DSL.castNull(ACCOUNT_EMAIL.LAST_LOGIN_ATTEMPT))
                    .where(ACCOUNT_EMAIL.ID.eq(emailAccount.id))
        }
    }

    String startPasswordRecovery(EmailAccount email) {
        String uuid = UUID.randomUUID().toString().replaceAll(/\W/, '')
        jooq { DSLContext create ->
            PasswordChangeRequestRecord record = create.newRecord(PASSWORD_CHANGE_REQUEST)
            record.id = uuid
            record.accountEmailId = email.id
            record.createdDate = Timestamp.valueOf(LocalDateTime.now())
            record.store()
        }
        return uuid
    }

    EmailAccount findByPasswordChangeRequestHash(String hash) {
        jooq { DSLContext create ->
            create.select(ACCOUNT_EMAIL.fields())
                    .from(ACCOUNT_EMAIL)
                    .join(PASSWORD_CHANGE_REQUEST).onKey()
                    .where(PASSWORD_CHANGE_REQUEST.ID.eq(hash))
                    .fetchOne()?.into(EmailAccount)
        }
    }

    void changePassword(EmailAccount emailAccount, Optional<String> requestUuid) {
        String password = passwordService.hashPassword(emailAccount.password)
        jooq { DSLContext create ->
            create.transaction {
                create.update(ACCOUNT_EMAIL)
                        .set(ACCOUNT_EMAIL.PASSWORD, password)
                        .where(ACCOUNT_EMAIL.ID.eq(emailAccount.id))
                        .execute()

                if (requestUuid.isPresent()) {
                    create.delete(PASSWORD_CHANGE_REQUEST)
                            .where(PASSWORD_CHANGE_REQUEST.ID.eq(requestUuid.get()))
                            .execute()
                }
            }
        }
    }

    private class PhotoRecordBuilder {

        private final UploadedFile file
        private final String url

        PhotoRecordBuilder(UploadedFile file) {
            this.file = file
            this.url = null
        }

        PhotoRecordBuilder(String url) {
            this.url = url
            this.file = null
        }

        PhotoRecord makePhoto(DSLContext create) {
            List<ResizeCommand> resizeCommands = [
                    new ResizeCommand(Photo.Size.THUMB, 64),
                    new ResizeCommand(Photo.Size.LARGE, 256)
            ]
            try {
                return file ?
                        photoService.createPhotoRecord(create, Photo.Type.CELLAR, file, resizeCommands) :
                        photoService.createPhotoRecord(create, Photo.Type.CELLAR, url, resizeCommands)
            } catch (PhotoWriteFailedException e) {
                log.error(LogUtil.toLog('AccountService', [
                        msg: 'Photo write failed while creating an account'
                ]))
            }
            return null
        }
    }
}
