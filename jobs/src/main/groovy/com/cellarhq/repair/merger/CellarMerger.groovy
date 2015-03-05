package com.cellarhq.repair.merger

import static com.cellarhq.generated.Tables.ACCOUNT_EMAIL
import static com.cellarhq.generated.Tables.ACCOUNT_OAUTH
import static com.cellarhq.generated.Tables.CELLAR
import static com.cellarhq.generated.Tables.CELLARED_DRINK
import static com.cellarhq.generated.Tables.CELLAR_ROLE
import static com.cellarhq.generated.Tables.PASSWORD_CHANGE_REQUEST

import com.cellarhq.commands.ExecutionFailedException
import com.cellarhq.generated.tables.records.AccountEmailRecord
import com.cellarhq.generated.tables.records.AccountOauthRecord
import com.cellarhq.generated.tables.records.CellarRecord
import com.cellarhq.jooq.CellarStatsUpdater
import com.cellarhq.support.DryRunSupport
import org.jooq.DSLContext
import org.jooq.Table

import java.sql.Timestamp
import java.time.LocalDateTime

class CellarMerger implements Merger<CellarRecord>, DryRunSupport {

    DSLContext create

    CellarMerger(DSLContext create) {
        this.create = create
    }

    @Override
    Map<CellarRecord, CellarRecord> conflicts() {
        Table t1 = CELLAR.as('t1')
        Table t2 = CELLAR.as('t2')

        List<CellarRecord> records = create.select(t1.fields())
                .from(t1)
                .join(t2).on(t1.SCREEN_NAME.equalIgnoreCase(t2.SCREEN_NAME))
                .where(t1.ID.notEqual(t2.ID))
                .fetchInto(CellarRecord)

        return (Map<CellarRecord, CellarRecord>) buildConflictMap(records, [CELLAR.SCREEN_NAME])
    }

    @Override
    boolean merge(CellarRecord sourceCellar, CellarRecord targetCellar) {
        if (!targetCellar || !sourceCellar) {
            throw new ExecutionFailedException(
                    "One of the cellars does not exist (target: ${targetCellar}, source: ${sourceCellar})")
        }

        List<AccountEmailRecord> targetEmailAccounts = getEmailAccounts(targetCellar.id)
        List<AccountEmailRecord> sourceEmailAccounts = getEmailAccounts(sourceCellar.id)
        List<AccountEmailRecord> diffEmailAccounts = sourceEmailAccounts.findAll { sourceEmail ->
            !targetEmailAccounts.any { it.email.toString().toLowerCase() == sourceEmail.email.toString().toLowerCase() }
        }.toList()

        List<AccountOauthRecord> targetOauthAccounts = getOauthAccounts(targetCellar.id)
        List<AccountOauthRecord> sourceOauthAccounts = getOauthAccounts(sourceCellar.id)
        List<AccountOauthRecord> diffOauthAccounts = sourceOauthAccounts.findAll { sourceOauth ->
            !targetOauthAccounts.any { it.username.toString().toLowerCase() == sourceOauth.username.toString().toLowerCase() }
        }.toList()

        Integer cellaredDrinks = create.selectCount()
                .from(CELLARED_DRINK)
                .where(CELLARED_DRINK.CELLAR_ID.eq(sourceCellar.id))
                .fetchOne(0, int)

        boolean result = create.transactionResult {
            if (diffEmailAccounts) {
                destructive("Merging ${diffEmailAccounts.size()} email accounts") {
                    create.update(ACCOUNT_EMAIL)
                            .set(ACCOUNT_EMAIL.CELLAR_ID, targetCellar.id)
                            .set(ACCOUNT_EMAIL.MODIFIED_DATE, Timestamp.valueOf(LocalDateTime.now()))
                            .where(ACCOUNT_EMAIL.ID.in(diffEmailAccounts.collect { it.id }))
                            .execute()
                }
            }

            if (diffOauthAccounts) {
                destructive("Merging ${diffOauthAccounts.size()} oauth accounts") {
                    create.update(ACCOUNT_OAUTH)
                            .set(ACCOUNT_OAUTH.CELLAR_ID, targetCellar.id)
                            .set(ACCOUNT_OAUTH.MODIFIED_DATE, Timestamp.valueOf(LocalDateTime.now()))
                            .where(ACCOUNT_OAUTH.ID.in(diffOauthAccounts.collect { it.id }))
                            .execute()
                }
            }

            if (cellaredDrinks) {
                destructive("Merging ${cellaredDrinks} cellared drinks") {
                    create.update(CELLARED_DRINK)
                            .set(CELLARED_DRINK.CELLAR_ID, targetCellar.id)
                            .set(CELLARED_DRINK.MODIFIED_DATE, Timestamp.valueOf(LocalDateTime.now()))
                            .where(CELLARED_DRINK.CELLAR_ID.eq(sourceCellar.id))
                            .execute()
                }
            }

            destructive('Merging cellar data') {
                [
                        'displayName',
                        'location',
                        'website',
                        'bio',
                        'contactEmail',
                        'twitter',
                        'reddit',
                        'beeradvocate',
                        'ratebeer',
                        'photoId'
                ].each { String fieldName ->
                    if (!targetCellar."$fieldName" && sourceCellar."$fieldName") {
                        targetCellar."$fieldName" = sourceCellar."$fieldName"
                    }
                }
                targetCellar.store()
            }

            destructive('Deleting source roles') {
                create.delete(CELLAR_ROLE)
                        .where(CELLAR_ROLE.CELLAR_ID.eq(sourceCellar.id))
                        .execute()
            }

            destructive('Deleting source password recovery requests') {
                create.delete(PASSWORD_CHANGE_REQUEST)
                        .where(PASSWORD_CHANGE_REQUEST.ACCOUNT_EMAIL_ID.in(create.select(ACCOUNT_EMAIL.ID)
                        .from(ACCOUNT_EMAIL)
                        .join(CELLAR).onKey()
                        .where(CELLAR.ID.eq(sourceCellar.id))
                        .fetch(ACCOUNT_EMAIL.ID)
                ))
                        .execute()
            }

            destructive('Deleting source accounts') {
                int emails = create.delete(ACCOUNT_EMAIL)
                        .where(ACCOUNT_EMAIL.CELLAR_ID.eq(sourceCellar.id))
                        .execute()

                int oauths = create.delete(ACCOUNT_OAUTH)
                        .where(ACCOUNT_OAUTH.CELLAR_ID.eq(sourceCellar.id))
                        .execute()

                return emails + oauths
            }

            destructive('Deleting source cellar') {
                create.delete(CELLAR)
                        .where(CELLAR.ID.eq(sourceCellar.id))
                        .execute()
            }

            destructive('Updating target cellar metadata') {
                CellarStatsUpdater.updateCellarCounts(targetCellar.id, create)
                create.update(CELLAR)
                        .set(CELLAR.MODIFIED_DATE, Timestamp.valueOf(LocalDateTime.now()))
                        .where(CELLAR.ID.eq(targetCellar.id))
                        .execute()
            }

            return true
        }

        return result
    }

    @Override
    List<CellarRecord> detectSourceAndTarget(CellarRecord a, CellarRecord b) {
        if (a.totalBeers == 0 && b.totalBeers > 0) {
            return [a, b]
        } else if (b.totalBeers == 0 && a.totalBeers > 0) {
            return [b, a]
        }

        if (a.lastLogin && b.lastLogin == null) {
            return [b, a]
        } else if (b.lastLogin && a.lastLogin == null) {
            return [a, b]
        }

        return []
    }

    List<AccountEmailRecord> getEmailAccounts(Long cellarId) {
        create.selectFrom(ACCOUNT_EMAIL)
                .where(ACCOUNT_EMAIL.CELLAR_ID.eq(cellarId))
                .fetch()
    }

    List<AccountOauthRecord> getOauthAccounts(Long cellarId) {
        create.selectFrom(ACCOUNT_OAUTH)
                .where(ACCOUNT_OAUTH.CELLAR_ID.eq(cellarId))
                .fetch()
    }
}
