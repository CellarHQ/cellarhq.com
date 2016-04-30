package com.cellarhq.auth.profiles

import com.cellarhq.auth.services.AccountService
import com.cellarhq.domain.EmailAccount
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.CommonProfile
import org.pac4j.http.credentials.UsernamePasswordCredentials
import org.pac4j.http.profile.HttpProfile
import org.pac4j.http.profile.creator.ProfileCreator

@Slf4j
class CellarHQProfileCreator implements ProfileCreator<UsernamePasswordCredentials, HttpProfile> {
  AccountService accountService

  @Inject
  CellarHQProfileCreator(AccountService accountService) {
    this.accountService = accountService
  }

  @Override
  HttpProfile create(UsernamePasswordCredentials credentials) {
    return credentials.userProfile
  }

  HttpProfile create(String username) {
    final HttpProfile PROFILE = new HttpProfile()

    EmailAccount emailAccount = accountService.findByEmail(username)
    PROFILE.setId(username)
    PROFILE.addAttribute(CommonProfile.USERNAME, username)
    PROFILE.addAttribute('CELLARID', emailAccount.cellarId)

    log.info("Created profile for cellar: ${emailAccount.cellarId}")

    return PROFILE
  }
}
