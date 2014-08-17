package com.cellarhq.services

import com.cellarhq.dao.PasswordChangeRequestDAO
import com.cellarhq.dao.EmailAccountDAO
import com.cellarhq.dao.OAuthAccountDAO
import com.cellarhq.domain.EmailAccount
import com.cellarhq.domain.OAuthAccount
import com.cellarhq.domain.PasswordChangeRequest
import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.mindrot.jbcrypt.BCrypt

@Slf4j
@CompileStatic
class AccountService {

    private final EmailAccountDAO emailAccountDAO
    private final OAuthAccountDAO oAuthAccountDAO
    private final PasswordChangeRequestDAO passwordChangeRequestDAO

    @Inject
    AccountService(EmailAccountDAO emailAccountDAO,
                   OAuthAccountDAO oAuthAccountDAO,
                   PasswordChangeRequestDAO passwordChangeRequestDAO) {
        this.emailAccountDAO = emailAccountDAO
        this.oAuthAccountDAO = oAuthAccountDAO
        this.passwordChangeRequestDAO = passwordChangeRequestDAO
    }

    EmailAccount findByCredentials(String username, String password) {
        EmailAccount emailAccount = emailAccountDAO.findByEmail(username)
        if (emailAccount && BCrypt.checkpw(password, emailAccount.password)) {
            return emailAccount
        }
        return null
    }

    OAuthAccount findByCredentials(String username, OAuthAccount.Client client = OAuthAccount.Client.TWITTER) {
        return oAuthAccountDAO.findByClientAndUsername(client, username)
    }

    EmailAccount findByEmail(String email) {
        return emailAccountDAO.findByEmail(email)
    }

    EmailAccount create(EmailAccount emailAccount) {
        emailAccount.password = BCrypt.hashpw(emailAccount.password, BCrypt.gensalt(16))
        emailAccountDAO.save(emailAccount)
        return emailAccount
    }

    OAuthAccount create(OAuthAccount oAuthAccount) {
        oAuthAccountDAO.save(oAuthAccount)
        return oAuthAccount
    }

    String startPasswordRecovery(EmailAccount emailAccount) {
        String uuid = UUID.randomUUID().toString().replaceAll(/\W/, '')
        passwordChangeRequestDAO.save(new PasswordChangeRequest(
                id: uuid,
                emailAccount: emailAccount
        ))
        return uuid
    }

    EmailAccount findByPasswordChangeRequestHash(String hash) {
        return passwordChangeRequestDAO.find(hash)?.emailAccount
    }

    void changePassword(EmailAccount emailAccount) {
        emailAccount.password = BCrypt.hashpw(emailAccount.password, BCrypt.gensalt(16))
        emailAccountDAO.save(emailAccount)
    }
}
