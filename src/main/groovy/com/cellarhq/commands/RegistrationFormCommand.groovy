package com.cellarhq.commands

import com.cellarhq.generated.tables.records.AccountEmailRecord
import com.cellarhq.generated.tables.records.CellarRecord
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.Length

import javax.validation.constraints.NotNull

class RegistrationFormCommand {

    @NotNull
    @Length(min = 4, max = 20)
    final String screenName

    @NotNull
    @Email
    final String email

    @NotNull
    final String password

    final String passwordConfirm

    RegistrationFormCommand(CellarRecord cellarRecord, AccountEmailRecord accountEmailRecord) {
        screenName = cellarRecord.screenName
        email = accountEmailRecord.email

    }

}
