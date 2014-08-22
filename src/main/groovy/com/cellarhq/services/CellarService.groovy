package com.cellarhq.services

import com.cellarhq.domain.Cellar
import com.cellarhq.generated.Tables
import com.cellarhq.generated.tables.records.CellarRecord
import com.google.inject.Inject
import groovy.transform.CompileStatic
import org.jooq.DSLContext
import org.pac4j.oauth.profile.twitter.TwitterProfile

import javax.sql.DataSource
import java.sql.Timestamp
import java.time.LocalDateTime

@CompileStatic
class CellarService extends BaseJooqService {

    @Inject
    CellarService(DataSource dataSource) {
        super(dataSource)
    }

    Cellar get(Long id) {
        (Cellar) jooq { DSLContext create ->
            create.select()
                    .from(Tables.CELLAR)
                    .where(Tables.CELLAR.ID.eq(id))
                    .fetchOneInto(Cellar)
        }
    }

    Cellar save(Cellar cellar) {
        jooq { DSLContext create ->
            CellarRecord cellarRecord = create.newRecord(Tables.CELLAR, cellar)
            if (cellar.id) {
                create.executeUpdate(cellarRecord)
            } else {
                create.executeInsert(cellarRecord)
            }
            cellarRecord.into(Cellar)
        }
    }

    void updateLoginStats(Cellar cellar, TwitterProfile twitterProfile = null) {
        cellar.lastLogin = Timestamp.valueOf(LocalDateTime.now())
        if (cellar.updateFromNetwork && twitterProfile) {
            cellar.with {
                displayName = twitterProfile.displayName
                location = twitterProfile.location
                website = twitterProfile.profileUrl
                bio = twitterProfile.description
            }
        }
        save(cellar)
    }
}
