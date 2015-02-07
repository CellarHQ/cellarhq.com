package com.cellarhq.domain

import com.cellarhq.generated.tables.pojos.AccountEmail
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.Column
import javax.persistence.Transient
import java.sql.Timestamp
import java.time.LocalDateTime

@CompileStatic
@InheritConstructors
class EmailAccount extends AccountEmail implements Account {

    @Transient
    Cellar cellar

    @Transient
    String passwordConfirm

    EmailAccount() {
        createdDate = Timestamp.valueOf(LocalDateTime.now())
        modifiedDate = createdDate
        loginAttemptCounter = 0
    }

    @Override
    void setEmail(String email) {
        super.setEmail(email.toLowerCase())
    }

    //todo: remove when we get to groovy 2.4.1
    @SuppressWarnings(['UnnecessaryOverridingMethod', 'UnnecessaryGetter'])
    @Override
    @Email
    @NotEmpty
    @Column(name = 'email')
    String getEmail() {
        return super.getEmail()
    }

    //todo: remove when we get to groovy 2.4.1
    @SuppressWarnings(['UnnecessaryOverridingMethod', 'UnnecessaryGetter'])
    @Override
    @NotEmpty
    @Length(min = 6, max = 64)
    @Column(name = 'password')
    String getPassword() {
        return super.getPassword()
    }
}
