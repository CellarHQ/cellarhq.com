package com.cellarhq.auth

import com.cellarhq.api.services.CellarService
import com.cellarhq.auth.profiles.TwitterCellarHQProfile
import com.cellarhq.auth.services.AccountService
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.OAuthAccount
import com.fasterxml.jackson.databind.JsonNode
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.oauth.client.TwitterClient
import org.pac4j.oauth.profile.JsonHelper
import org.pac4j.oauth.profile.OAuthAttributesDefinitions
import org.pac4j.oauth.profile.twitter.TwitterProfile

@Slf4j
class CustomTwitterClient extends TwitterClient {
  AccountService accountService
  CellarService cellarService

  @Inject
  CustomTwitterClient(AccountService accountService, CellarService cellarService, String key, String secret) {
    super(key, secret)
    this.accountService = accountService
    this.cellarService = cellarService
  }

  @Override
  protected TwitterCellarHQProfile extractUserProfile(String body) {
    TwitterCellarHQProfile profile = new TwitterCellarHQProfile()
    JsonNode json = JsonHelper.getFirstNode(body)
    if (json != null) {
      profile.setId(JsonHelper.get(json, 'id'))

      Iterator i = OAuthAttributesDefinitions.twitterDefinition.allAttributes.iterator()
      while (i.hasNext()) {
        String attribute = (String) i.next()
        profile.addAttribute(attribute, JsonHelper.get(json, attribute))
      }

      OAuthAccount oauthAccount = accountService.findByCredentials(profile.username)

      if (oauthAccount) {
        profile.cellarId = oauthAccount.cellarId
        cellarService.updateLoginStats(oauthAccount.cellar, profile)
      } else {
        Cellar cellar = Cellar.makeFrom(profile)
        log.debug("Making cellar with username ${cellar.screenName}")
        oauthAccount = makeOauthAccountFrom(profile, cellar)

        accountService.create(oauthAccount, profile.pictureUrl?.replace('_normal', ''))
      }
    }

    return profile
  }

  OAuthAccount makeOauthAccountFrom(TwitterProfile profile, Cellar cellar) {
    return new OAuthAccount(
      username: profile.username,
      cellar: cellar
    )
  }
}
