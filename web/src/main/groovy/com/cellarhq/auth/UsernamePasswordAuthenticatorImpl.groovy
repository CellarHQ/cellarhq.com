package com.cellarhq.auth

import com.cellarhq.auth.services.AccountService
import com.cellarhq.common.Messages
import com.cellarhq.domain.EmailAccount
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.pac4j.core.exception.CredentialsException
import org.pac4j.http.credentials.UsernamePasswordAuthenticator
import org.pac4j.http.credentials.UsernamePasswordCredentials

import java.time.LocalDateTime

/**
 * Authenticates a username & password.
 *
 * To prevent brute-forcing login attempts, the class will increment (to a ceiling) more lengthy delays
 * between bad login attempts. This is done, as opposed to account locking to help prevent against DoS attacks.
 *
 * @todo When ratpack supports remote addresses, we should track by remote addr, as opposed to a blanket login delay.
 */
@Slf4j
@CompileStatic
class UsernamePasswordAuthenticatorImpl implements UsernamePasswordAuthenticator {

  final static int MAX_DELAY_SECONDS = 15
  final static int DELAY_BASE_SECONDS = 2

  private final AccountService accountService
  private final PasswordService passwordService

  @Inject
  UsernamePasswordAuthenticatorImpl(AccountService accountService, PasswordService passwordService) {
    this.accountService = accountService
    this.passwordService = passwordService
  }

  @Override
  void validate(UsernamePasswordCredentials credentials) {
    if (credentials == null) {
      throwsException(Messages.AUTH_NO_CREDENTIALS)
    }

    EmailAccount emailAccount = accountService.findByEmail(credentials.username)

    if (!emailAccount) {
      throwsException(Messages.AUTH_CREDENTIALS_DO_NOT_MATCH)
    }

    boolean passwordMatches = false
    try {
      passwordMatches = passwordService.checkPassword(credentials.password, emailAccount.password)
    } catch (UnclaimedAccountException e) {
      throwsException(e.message)
    }

    if (passwordMatches) {
      accountService.resetFailedLoginAttempts(emailAccount)

      if (passwordService.shouldRehashPassword(emailAccount.password)) {
        log.warn(LogUtil.toLog('AutoPasswordRehash', [
          msg    : 'Automatically rehashing account password',
          account: emailAccount.id
        ]))
        emailAccount.password = credentials.password
        accountService.changePassword(emailAccount, Optional.empty())
      }
      return
    }

    accountService.trackFailedLoginAttempt(emailAccount)

    int delay = calculateDelay(
      emailAccount.lastLoginAttempt?.toLocalDateTime(),
      emailAccount.loginAttemptCounter + 1)

    if (delay > 0) {
      sleep(delay)
    }

    throwsException(Messages.AUTH_CREDENTIALS_DO_NOT_MATCH)
  }

  protected void throwsException(final String message) {
    throw new CredentialsException(message)
  }

  int calculateDelay(LocalDateTime lastLoginAttempt, int loginAttempts) {
    if (loginAttempts > 1) {
      if (lastLoginAttempt.isAfter(LocalDateTime.now().minusMinutes(30))) {
        return Math.max(Math.min((loginAttempts - 1) * DELAY_BASE_SECONDS, MAX_DELAY_SECONDS), 0)
      }
    }
    return 0
  }
}
