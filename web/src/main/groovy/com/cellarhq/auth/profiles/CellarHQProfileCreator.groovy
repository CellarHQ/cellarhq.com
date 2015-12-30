package com.cellarhq.auth.profiles

import com.cellarhq.auth.services.AccountService
import com.cellarhq.domain.EmailAccount
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.CommonProfile
import org.pac4j.http.profile.ProfileCreator

@Slf4j
class CellarHQProfileCreator implements ProfileCreator {
	AccountService accountService

	@Inject
	CellarHQProfileCreator(AccountService accountService) {
		this.accountService = accountService
	}

	/**
	 * Create a CellarHQProfile profile.
	 *
	 * @param username
	 * @return the created profile
	 */
	public HttpCellarHQProfile create(final String username) {
		HttpCellarHQProfile profile = new HttpCellarHQProfile()

		EmailAccount emailAccount = accountService.findByEmail(username)
		profile.cellarId = emailAccount.cellarId
		profile.setId(username)
		profile.addAttribute(CommonProfile.USERNAME, username)

		log.info("Created profile for cellar: ${profile.cellarId}")

		return profile
	}
}
