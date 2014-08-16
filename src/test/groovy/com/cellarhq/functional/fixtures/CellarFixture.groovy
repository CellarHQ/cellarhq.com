package com.cellarhq.functional.fixtures

import com.cellarhq.domain.Cellar
import com.cellarhq.domain.EmailAccount

class CellarFixture {

    Cellar cellar
    EmailAccount emailAccount

    static CellarFixture makeDefault(String screenName = 'someone') {
        new CellarFixture().with { CellarFixture self ->
            cellar = new Cellar(
                    screenName: screenName,
                    displayName: screenName,
            )
            emailAccount = new EmailAccount(email: "test-${screenName}@example.com")
            emailAccount.cellar = cellar
            return self
        }
    }
}
