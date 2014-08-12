package com.cellarhq.services

import com.cellarhq.dao.EmailAccountDAO
import com.cellarhq.domain.EmailAccount
import org.mindrot.jbcrypt.BCrypt
import spock.lang.Specification

class AccountServiceSpec extends Specification {

    def 'create email account encrypts password'() {
        given:
        EmailAccountDAO emailAccountDAO = Mock()

        and:
        AccountService accountService = new AccountService(emailAccountDAO, null)

        and:
        EmailAccount emailAccount = new EmailAccount(
                email: 'rob@cellarhq.com',
                password: 'password'
        )

        when:
        accountService.create(emailAccount)

        then:
        noExceptionThrown()
        assert BCrypt.checkpw('password', emailAccount.password)
        1 * emailAccountDAO.save(_)
        0 * _
    }
}
