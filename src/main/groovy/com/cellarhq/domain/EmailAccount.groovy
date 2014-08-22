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
class EmailAccount extends AccountEmail {

    @Transient
    Cellar cellar

    @Transient
    String passwordConfirm

    EmailAccount() {
        createdDate = Timestamp.valueOf(LocalDateTime.now())
        modifiedDate = createdDate
    }

    @Override
    @Email
    @NotEmpty
    @Column(name = 'email')
    String getEmail() {
        return super.email
    }

    @Override
    @NotEmpty
    @Length(min = 6, max = 64)
    @Column(name = 'password')
    String getPassword() {
        return super.password
    }
}
