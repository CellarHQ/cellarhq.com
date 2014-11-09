package com.cellarhq.commands

import static com.cellarhq.generated.Tables.*

import com.cellarhq.commands.support.DatabaseSupport
import com.cellarhq.generated.tables.records.AccountEmailRecord
import com.cellarhq.generated.tables.records.AccountOauthRecord
import com.cellarhq.generated.tables.records.CellarRecord
import com.cellarhq.jooq.CellarStatsUpdater

import java.sql.Timestamp
import java.time.LocalDateTime

/**
 * Allows merging one cellar into another.
 *
 * This command will not setup redirects; the source Cellar will be removed and any links that lead to it externally
 * will cease to resolve correctly.
 */
class CellarMergeCommand implements NamedCommand, DatabaseSupport {

    Long targetCellarId
    Long sourceCellarId
    boolean dryRun

    void configure(String[] args) {
        CliBuilder cli = new CliBuilder(
                usage: 'chq cellarMerge [-d|--dryrun] [target_cellar_id] [source_cellar_id]',
                header: 'This command will merge the given "source" Cellar record and all of its associated data to' +
                        'the "target" cellar. This command will use the target\'s Cellar metadata, and will attempt' +
                        'to keep all associated accounts. In cases where the target and source Cellars share either' +
                        'email or oauth credentials, the target cellar\'s accounts will take precedence and the' +
                        'source accounts will be deleted.'
        )
        cli.d(longOpt: 'dryrun', required: false, 'Dry run; no actions will be taken')

        OptionAccessor opt = cli.parse(args)
        if (!opt) {
            throw new IllegalStateException('CLI opts could not be parsed')
        }

        if (opt.arguments().size() != 2) {
            throw new IllegalStateException('You must provide a target and source')
        }

        targetCellarId = Long.valueOf(opt.arguments().get(0))
        sourceCellarId = Long.valueOf(opt.arguments().get(1))
        dryRun = (boolean) opt.getProperty('d')

        if (targetCellarId == sourceCellarId) {
            throw new IllegalStateException('Source and target cannot be the same')
        }
    }

    boolean run() {
        if (!dryRun) {
            println('###################################################')
            println('## THIS IS NOT A DRY RUN (starting in 5 seconds) ##')
            println('###################################################')
            sleep(5000)
        }

        CellarRecord targetCellar = getCellar(targetCellarId)
        CellarRecord sourceCellar = getCellar(sourceCellarId)

        if (!targetCellar || !sourceCellar) {
            throw new ExecutionFailedException(
                    "One of the cellars does not exist (target: ${targetCellar}, source: ${sourceCellar})")
        }

        println('')
        println('## SOURCE #########################################')
        println("ID: ${sourceCellar.id}")
        println("Screenname: ${sourceCellar.screenName}")
        println('## TARGET #########################################')
        println("ID: ${targetCellar.id}")
        println("Screenname: ${targetCellar.screenName}")
        println('###################################################')
        sleep(20000)

        List<AccountEmailRecord> targetEmailAccounts = getEmailAccounts(targetCellar.id)
        List<AccountEmailRecord> sourceEmailAccounts = getEmailAccounts(sourceCellar.id)
        List<AccountEmailRecord> diffEmailAccounts = sourceEmailAccounts.findAll { sourceEmail ->
            !targetEmailAccounts.any { it.email == sourceEmail.email }
        }.toList()

        List<AccountOauthRecord> targetOauthAccounts = getOauthAccounts(targetCellar.id)
        List<AccountOauthRecord> sourceOauthAccounts = getOauthAccounts(sourceCellar.id)
        List<AccountOauthRecord> diffOauthAccounts = sourceOauthAccounts.findAll { sourceOauth ->
            !targetOauthAccounts.any { it.username == sourceOauth.username }
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

    CellarRecord getCellar(Long id) {
        create.selectFrom(CELLAR)
                .where(CELLAR.ID.eq(id))
                .fetchOne()
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

    void destructive(String description, Closure<Integer> operation) {
        println("## ${description}")
        if (!dryRun) {
            int rowsAffected = operation.call()
            println("  ${rowsAffected} rows affected")
        }
    }
}
