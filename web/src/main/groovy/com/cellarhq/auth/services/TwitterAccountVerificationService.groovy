package com.cellarhq.auth.services

import com.cellarhq.domain.Cellar
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.domain.OAuthClient
import com.cellarhq.generated.tables.records.AccountOauthRecord
import com.cellarhq.jooq.BaseJooqService
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.jooq.DSLContext
import org.jooq.TransactionalCallable
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.exec.Blocking
import rx.Observable

import javax.sql.DataSource

import static com.cellarhq.generated.Tables.ACCOUNT_OAUTH
import static ratpack.rx.RxRatpack.observe

@Slf4j
class TwitterAccountVerificationService extends BaseJooqService {

  @Inject
  TwitterAccountVerificationService(DataSource dataSource) {
    super(dataSource)
  }

  Observable<Boolean> linkAllowed(OAuthAccount pendingAccount) {
    return observe(Blocking.get {
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
    return observe(Blocking.get {
      jooq { DSLContext create ->
        create.transactionResult({
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
        } as TransactionalCallable)
      }
    })
  }
}
