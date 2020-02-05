package com.cellarhq.domain

import com.cellarhq.generated.tables.pojos.AccountEmail
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import org.hibernate.validator.constraints.Length

import javax.persistence.Column
import javax.persistence.Transient
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import java.time.LocalDateTime

@CompileStatic
@InheritConstructors
class EmailAccount extends AccountEmail implements Account {

  @Transient
  Cellar cellar

  @Transient
  String passwordConfirm

  EmailAccount() {
    createdDate = LocalDateTime.now()
    modifiedDate = createdDate
    loginAttemptCounter = 0
  }

  @Override
  void setEmail(String email) {
    super.setEmail(email.toLowerCase())
  }

  @Override
  @Email
  @NotBlank
  @Column(name = 'email')
  String getEmail() {
    return super.getEmail()
  }

  @Override
  @NotBlank
  @Length(min = 6, max = 64)
  @Column(name = 'password')
  String getPassword() {
    return super.getPassword()
  }
}
