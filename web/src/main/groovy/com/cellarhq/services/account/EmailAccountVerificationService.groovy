package com.cellarhq.services.account

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observe

import com.cellarhq.CellarHQModule
import com.cellarhq.Messages
import com.cellarhq.auth.PasswordService
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.generated.tables.records.AccountEmailRecord
import com.cellarhq.generated.tables.records.AccountLinkRequestRecord
import com.cellarhq.services.BaseJooqService
import com.cellarhq.services.email.EmailService
import com.cellarhq.util.JooqUtil
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import ratpack.exec.ExecControl
import rx.Observable

import javax.sql.DataSource
import java.sql.Timestamp
import java.time.LocalDateTime

@Slf4j
@CompileStatic
class EmailAccountVerificationService extends BaseJooqService {

    EmailService emailService

    @Inject
    EmailAccountVerificationService(DataSource dataSource, ExecControl execControl, EmailService emailService) {
        super(dataSource, execControl)
        this.emailService = emailService
    }

    Boolean linkAllowed(EmailAccount pendingAccount) {
        int count = jooq { DSLContext create ->
            create.selectCount()
                    .from(ACCOUNT_EMAIL)
                    .where(ACCOUNT_EMAIL.EMAIL.eq(pendingAccount.email))
                    .fetchOneInto(Integer)
        }

        return count == 0
    }

    Observable<VerificationResult> sendVerification(Cellar cellar, EmailAccount pendingAccount) {
        return observe(execControl.blocking {
            if (!linkAllowed(pendingAccount)) {
                return VerificationResult.failure(String.format(
                        Messages.ACCOUNT_LINK_EMAIL_UNAVAILABLE,
                        pendingAccount.email
                ))
            }

            String uuid = JooqUtil.uuid()

            jooq { DSLContext create ->
                AccountLinkRequestRecord record = create.newRecord(ACCOUNT_LINK_REQUEST)
                record.with {
                    id = uuid
                    accountClass = EmailAccount.simpleName
                    accountIdentifier = pendingAccount.email
                    cellarId = cellar.id
                    createdDate = Timestamp.valueOf(LocalDateTime.now())
                }
                record.store()
            }

            emailService.sendEmail(pendingAccount.email, 'Account Link Request', (String) """\
                |Hello ${cellar.displayName},
                |
                |We got a request to link this email account on CellarHQ. If you did
                |not request this, you can ignore this email or report it to us at
                |team@cellarhq.com
                |
                |To link this email to your account, please follow the link below:
                |
                |https://${CellarHQModule.hostname}/settings/link-email/${uuid}
                |
                |Cheers!
                |Kyle and Rob
                |""".stripMargin())

            return VerificationResult.success()
        })
    }

    Observable<Boolean> verify(Cellar cellar, String token) {
        return observe(execControl.blocking {
            jooq { DSLContext create ->
                AccountLinkRequestRecord requestRecord = create.selectFrom(ACCOUNT_LINK_REQUEST)
                        .where(ACCOUNT_LINK_REQUEST.ID.eq(token))
                        .and(ACCOUNT_LINK_REQUEST.CELLAR_ID.eq(cellar.id))
                        .fetchOne()

                return requestRecord != null
            }
        })
    }

    Observable<VerificationResult> verifyAndCommit(Cellar cellar, String token, String rawPassword) {
        return observe(execControl.blocking {
            jooq { DSLContext create ->
                AccountLinkRequestRecord requestRecord = create.selectFrom(ACCOUNT_LINK_REQUEST)
                        .where(ACCOUNT_LINK_REQUEST.ID.eq(token))
                        .and(ACCOUNT_LINK_REQUEST.CELLAR_ID.eq(cellar.id))
                        .fetchOne()

                if (!requestRecord) {
                    return VerificationResult.failure(Messages.ACCOUNT_LINK_TOKEN_UNKNOWN)
                }

                create.transactionResult {
                    EmailAccount emailAccount = new EmailAccount().with { EmailAccount self ->
                        cellarId = cellar.id
                        email = requestRecord.accountIdentifier
                        password = new PasswordService().hashPassword(rawPassword)
                        return self
                    }

                    AccountEmailRecord record = create.newRecord(ACCOUNT_EMAIL, emailAccount)
                    record.reset(ACCOUNT_OAUTH.ID)
                    record.store()

                    requestRecord.delete()

                    return VerificationResult.success()
                }
            }
        })
    }
}
