package com.cellarhq.functional

import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount
import com.cellarhq.functional.pages.LoginPage
import com.cellarhq.auth.services.AccountService
import groovy.sql.Sql
import org.h2.jdbc.JdbcSQLException
import ratpack.test.remote.RemoteControl

import javax.sql.DataSource

trait  LogInUserTrait {


    void logInUser(String email, String password) {
        LoginPage page = to LoginPage
        page.fillForm(email, password)
        page.submitForm()
    }

}
