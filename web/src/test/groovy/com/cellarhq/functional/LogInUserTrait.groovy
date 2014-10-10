package com.cellarhq.functional

import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.functional.pages.LoginPage
import groovy.sql.Sql
import org.h2.jdbc.JdbcSQLException

import javax.sql.DataSource

trait  LogInUserTrait {
    abstract def getRemote()

    EmailAccount anEmailAccountUser() {
        remote.exec {
            Cellar cellar = new Cellar(screenName: 'someone', displayName: 'Someone')
            EmailAccount emailAccount = new EmailAccount(email: 'test@example.com', password: 'password1')
            emailAccount.cellar = cellar
            get(com.cellarhq.services.AccountService).create(emailAccount, null)
        }
    }

    void cleanUpUsers() {
        remote.exec {
            try {
                Sql sql = new Sql(get(DataSource))
                sql.execute('delete from account_email where 1=1')
                sql.execute('delete from cellar_role where 1=1')
                sql.execute('delete from cellar where 1=1')
                sql.close()
            } catch (JdbcSQLException e) {
                // I don't think this should make the test fail: Will also be changed moving to jOOQ.
                log.error(e.message)
            }
        }

    }

    void logInUser(EmailAccount emailAccount) {
        LoginPage page = to LoginPage
        page.fillForm('test@example.com', 'password1')
        page.submitForm()
    }

}
