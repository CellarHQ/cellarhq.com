package com.cellarhq.services

import static com.cellarhq.generated.Tables.*

import com.cellarhq.auth.Role
import com.cellarhq.domain.OAuthClient
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.generated.tables.records.AccountEmailRecord
import com.cellarhq.generated.tables.records.AccountOauthRecord
import com.cellarhq.generated.tables.records.CellarRecord
import com.cellarhq.generated.tables.records.CellarRoleRecord
import com.cellarhq.generated.tables.records.PasswordChangeRequestRecord
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.DSLContext
import org.jooq.Record
import org.mindrot.jbcrypt.BCrypt
import ratpack.exec.ExecControl

import javax.sql.DataSource
import java.sql.Timestamp
import java.time.LocalDateTime

@CompileStatic
class AccountService extends BaseJooqService {

    private final static int BCRYPT_LOG_ROUNDS = 16

    @Inject
    AccountService(DataSource dataSource, ExecControl execControl) {
        super(dataSource, execControl)
    }

    EmailAccount findByCredentials(String username, String password) {
        EmailAccount emailAccount = findByEmail(username)
        if (emailAccount && BCrypt.checkpw(password, emailAccount.password)) {
            return emailAccount
        }
        return null
    }

    OAuthAccount findByCredentials(String username, OAuthClient client = OAuthClient.TWITTER) {
        (OAuthAccount) jooq { DSLContext create ->
            Record result = create.select()
                    .from(ACCOUNT_OAUTH)
                    .join(CELLAR).onKey()
                    .where(ACCOUNT_OAUTH.CLIENT.eq(client.toString()))
                        .and(ACCOUNT_OAUTH.USERNAME.eq(username))
                    .fetchOne()

            if (result) {
                OAuthAccount oAuthAccount = result.into(OAuthAccount)
                oAuthAccount.cellar = result.into(Cellar)
                oAuthAccount
            }
        }
    }

    EmailAccount findByEmail(String email) {
        (EmailAccount) jooq { DSLContext create ->
            create.select()
                .from(ACCOUNT_EMAIL)
                .where(ACCOUNT_EMAIL.EMAIL.eq(email))
                .fetchOneInto(EmailAccount)
        }
    }

    EmailAccount findByEmailWithCellar(String email) {
        (EmailAccount) jooq { DSLContext create ->
            Record result = create.select()
                    .from(ACCOUNT_EMAIL)
                    .join(CELLAR).onKey()
                    .where(ACCOUNT_EMAIL.EMAIL.eq(email))
                    .fetchOne()

            if (result) {
                EmailAccount emailAccount = result.into(EmailAccount)
                emailAccount.cellar = result.into(Cellar)
                emailAccount
            }
        }
    }

    EmailAccount create(EmailAccount emailAccount) {
        emailAccount.password = BCrypt.hashpw(emailAccount.password, BCrypt.gensalt(BCRYPT_LOG_ROUNDS))
        (EmailAccount) jooq { DSLContext create ->
            create.transactionResult {
                CellarRecord cellarRecord = create.newRecord(CELLAR, emailAccount.cellar)
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

    OAuthAccount create(OAuthAccount oAuthAccount) {
        (OAuthAccount) jooq { DSLContext create ->
            create.transactionResult {
                CellarRecord cellarRecord = create.newRecord(CELLAR, oAuthAccount.cellar)
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

    private void bindRoleToCellar(DSLContext create, Role role, long cellarId) {
        CellarRoleRecord cellarRoleRecord = create.newRecord(CELLAR_ROLE)
        cellarRoleRecord.cellarId = cellarId
        cellarRoleRecord.role = role.toString()
        cellarRoleRecord.store()
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

    void changePassword(EmailAccount emailAccount, String requestUuid) {
        String password = BCrypt.hashpw(emailAccount.password, BCrypt.gensalt(BCRYPT_LOG_ROUNDS))
        jooq { DSLContext create ->
            create.transaction {
                create.update(ACCOUNT_EMAIL)
                        .set(ACCOUNT_EMAIL.PASSWORD, password)
                        .where(ACCOUNT_EMAIL.ID.eq(emailAccount.id))
                        .execute()

                create.delete(PASSWORD_CHANGE_REQUEST).where(PASSWORD_CHANGE_REQUEST.ID.eq(requestUuid)).execute()
            }
        }
    }
}
