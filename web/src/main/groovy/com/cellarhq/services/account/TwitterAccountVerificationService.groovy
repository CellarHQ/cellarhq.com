package com.cellarhq.services.account

import static com.cellarhq.generated.Tables.*
import static ratpack.rx.RxRatpack.observe

import com.cellarhq.domain.Cellar
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.domain.OAuthClient
import com.cellarhq.generated.tables.records.AccountOauthRecord
import com.cellarhq.services.BaseJooqService
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.exec.ExecControl
import rx.Observable

import javax.sql.DataSource

@Slf4j
@CompileStatic
class TwitterAccountVerificationService extends BaseJooqService {

    @Inject
    TwitterAccountVerificationService(DataSource dataSource, ExecControl execControl) {
        super(dataSource, execControl)
    }

    Observable<Boolean> linkAllowed(OAuthAccount pendingAccount) {
        return observe(execControl.blocking {
            int count = jooq { DSLContext create ->
                create.selectCount()
                        .from(ACCOUNT_OAUTH)
                        .where(ACCOUNT_OAUTH.USERNAME.eq(pendingAccount.username))
                        .fetchOneInto(Integer)
            }

            return count == 0
        })
    }

    Observable<Boolean> commit(Cellar cellar, TwitterProfile profile) {
        return observe(execControl.blocking {
            jooq { DSLContext create ->
                create.transactionResult {
                    OAuthAccount oAuthAccount = new OAuthAccount().with { OAuthAccount self ->
                        cellarId = cellar.id
                        client = OAuthClient.TWITTER.toString()
                        username = profile.username
                        return self
                    }

                    AccountOauthRecord record = create.newRecord(ACCOUNT_OAUTH, oAuthAccount)
                    record.reset(ACCOUNT_OAUTH.ID)
                    record.store()

                    return true
                }
            }
        })
    }
}
