package com.cellarhq.services

import com.cellarhq.domain.jooq.Cellar
import com.cellarhq.domain.jooq.EmailAccount
import com.cellarhq.generated.Tables
import com.cellarhq.generated.tables.records.AccountEmailRecord
import com.cellarhq.generated.tables.records.CellarRecord
import org.jooq.DSLContext
import org.jooq.Result
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.tools.jdbc.MockDataProvider
import org.jooq.tools.jdbc.MockExecuteContext
import org.jooq.tools.jdbc.MockResult
import org.mindrot.jbcrypt.BCrypt

import javax.sql.DataSource
import java.sql.SQLException

class AccountServiceSpec extends JooqSpecification {

    def 'create email account encrypts password'() {
        given:
        DataSource dataSource = Stub(DataSource) {
            getConnection() >> { connection }
        }

        and:
        JooqAccountService accountService = new JooqAccountService(dataSource)

        and:
        EmailAccount emailAccount = new EmailAccount(
                email: 'rob@cellarhq.com',
                password: 'password',
                cellar: Mock(Cellar)
        )

        when:
        accountService.create(emailAccount)

        then:
        noExceptionThrown()
        assert BCrypt.checkpw('password', emailAccount.password)
    }

    @Override
    protected MockDataProvider createMockDataProvider() {
        return new MockDataProvider() {
            @Override
            MockResult[] execute(MockExecuteContext ctx) throws SQLException {
                DSLContext create = DSL.using(SQLDialect.POSTGRES)

                MockResult[] mock = new MockResult[2]

                Result<CellarRecord> cellarResult = create.newResult(Tables.CELLAR)
                cellarResult.add(create.newRecord(Tables.CELLAR))
                cellarResult.get(0).setValue(Tables.CELLAR.ID, 1)
                mock[0] = new MockResult(1, cellarResult)

                Result<AccountEmailRecord> accountEmailResult = create.newResult(Tables.ACCOUNT_EMAIL)
                accountEmailResult.add(create.newRecord(Tables.ACCOUNT_EMAIL))
                accountEmailResult.get(0).setValue(Tables.ACCOUNT_EMAIL.ID, 1)
                accountEmailResult.get(0).setValue(Tables.ACCOUNT_EMAIL.CELLAR_ID, 1)
                mock[1] = new MockResult(1, accountEmailResult)

                return mock
            }
        }
    }
}
