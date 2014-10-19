package com.cellarhq.functional

import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.functional.pages.LoginPage
import groovy.sql.Sql
import org.h2.jdbc.JdbcSQLException
import ratpack.test.remote.RemoteControl

import javax.sql.DataSource

trait  LogInUserTrait {
    EmailAccount anEmailAccountUser(RemoteControl remote, String screenName, String email, String password) {
        remote.exec {
            Cellar cellar = new Cellar(screenName: screenName, displayName: screenName)
            EmailAccount emailAccount = new EmailAccount(email: email, password: password)
            emailAccount.cellar = cellar
            get(com.cellarhq.services.AccountService).create(emailAccount, null)
        }
    }

    void cleanUpUsers(RemoteControl remote) {
        remote.exec {
            try {
                Sql sql = new Sql(get(DataSource))
                sql.execute('delete from account_email where 1=1')
                sql.execute('delete from cellar_role where 1=1')
                sql.execute('delete from cellar where 1=1')
                sql.close()
            } catch (JdbcSQLException e) {
            }
        }

    }

    void logInUser(String email, String password) {
        LoginPage page = to LoginPage
        page.fillForm(email, password)
        page.submitForm()
    }

}
