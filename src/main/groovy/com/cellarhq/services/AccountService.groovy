package com.cellarhq.services

import static com.cellarhq.util.DataSourceUtil.withDslContext
import static com.cellarhq.generated.Tables.*

import com.cellarhq.auth.Role
import com.cellarhq.commands.RegistrationFormCommand
import com.cellarhq.generated.Tables
import com.cellarhq.generated.tables.records.AccountEmailRecord
import com.cellarhq.generated.tables.records.AccountOauthRecord
import com.cellarhq.generated.tables.records.CellarRecord
import com.cellarhq.generated.tables.records.CellarRoleRecord
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import org.mindrot.jbcrypt.BCrypt

import javax.sql.DataSource
import java.sql.Timestamp
import java.time.LocalDateTime

@Slf4j
@CompileStatic
class AccountService {

    final static String OAUTH_TWITTER_CLIENT = 'TwitterClient'

    private final DataSource dataSource

    @Inject
    AccountService(DataSource dataSource) {
        this.dataSource = dataSource
    }

    AccountEmailRecord findByCredentials(String username, String password) {
        withDslContext(dataSource) { DSLContext create ->
            AccountEmailRecord record = create.selectFrom(ACCOUNT_EMAIL)
                    .where(ACCOUNT_EMAIL.EMAIL.eq(username))
                    .fetchOne()

            if (record && BCrypt.checkpw(password, record.password)) {
                return record
            }
            null
        }
    }

    AccountOauthRecord findByCredentials(String username) {
        withDslContext(dataSource) { DSLContext create ->
             create.selectFrom(ACCOUNT_OAUTH)
                    .where(ACCOUNT_OAUTH.CLIENT.eq(OAUTH_TWITTER_CLIENT))
                    .and(ACCOUNT_OAUTH.USERNAME.eq(username))
                    .fetchOne()
        }
    }

    /**
     * Style A: Pass commands into the services.
     */
    void register(RegistrationFormCommand registration) {
        String encryptedPassword = BCrypt.hashpw(registration.password, BCrypt.gensalt(16))

        withDslContext(dataSource) { DSLContext create ->
            create.transaction {
                CellarRecord cellar = create
                        .insertInto(CELLAR, CELLAR.SCREEN_NAME, CELLAR.CONTACT_EMAIL, CELLAR.LAST_LOGIN)
                        .values(registration.screenName, registration.email, Timestamp.valueOf(LocalDateTime.now()))
                        .returning()
                        .fetchOne()

                create.insertInto(CELLAR_ROLE, CELLAR_ROLE.CELLAR_ID, CELLAR_ROLE.ROLE)
                        .values(cellar.id, Role.MEMBER.toString())
                        .execute()

                create.insertInto(ACCOUNT_EMAIL, ACCOUNT_EMAIL.CELLAR_ID, ACCOUNT_EMAIL.EMAIL, ACCOUNT_EMAIL.PASSWORD)
                        .values(cellar.id, registration.email, registration.password)
                        .execute()
            }
        }
    }

    /**
     * Style B: Pass in Records into the
     */
    void register(AccountOauthRecord accountRecord, CellarRecord cellarRecord) {
        withDslContext(dataSource) { DSLContext create ->
            accountRecord.attach(create.configuration())
            cellarRecord.attach(create.configuration())

            create.transaction {
                cellarRecord.store()

                CellarRoleRecord cellarRoleRecord = create.newRecord(CELLAR_ROLE)
                cellarRoleRecord.cellarId = cellarRecord.id
                cellarRoleRecord.role = Role.MEMBER.toString()
                cellarRoleRecord.store()

                accountRecord.cellarId = cellarRecord.id
                accountRecord.store()
            }
        }
    }
}
