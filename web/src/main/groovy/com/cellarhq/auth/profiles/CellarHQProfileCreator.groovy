package com.cellarhq.auth.profiles

import com.cellarhq.auth.services.AccountService
import com.cellarhq.domain.EmailAccount
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.credentials.Credentials
import org.pac4j.core.profile.CommonProfile
import org.pac4j.core.profile.UserProfile
import org.pac4j.http.credentials.HttpCredentials
import org.pac4j.http.profile.creator.ProfileCreator

@Slf4j
class CellarHQProfileCreator implements ProfileCreator<HttpCredentials, UserProfile> {
  AccountService accountService

  @Inject
  CellarHQProfileCreator(AccountService accountService) {
    this.accountService = accountService
  }

  @Override
  UserProfile create(HttpCredentials credentials) {
    HttpCellarHQProfile profile = new HttpCellarHQProfile()

    EmailAccount emailAccount = accountService.findByEmail(credentials.userProfile.id)
    profile.cellarId = emailAccount.cellarId
    profile.setId(credentials.userProfile.id)
    profile.addAttribute(CommonProfile.USERNAME, credentials.userProfile.id)

    log.info("Created profile for cellar: ${profile.cellarId}")

    return profile
  }
}
