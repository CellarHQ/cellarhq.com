package com.cellarhq.commands

import com.cellarhq.generated.tables.records.OrganizationRecord
import com.cellarhq.repair.merger.OrganizationMerger

import static com.cellarhq.generated.Tables.*

import com.cellarhq.repair.merger.CellarMerger
import com.cellarhq.support.DatabaseSupport
import com.cellarhq.support.DryRunSupport
import com.cellarhq.support.UserInputSupport
import com.cellarhq.support.userinput.BooleanPromptType
import com.cellarhq.support.userinput.PromptType
import com.cellarhq.generated.tables.records.CellarRecord
import org.jooq.Field
import org.jooq.Record

/**
 * IMPORTANT: This script WILL NOT WORK if run from Gradle.
 */
class RepairDuplicatesCommand extends BaseCommand
        implements NamedCommand, UserInputSupport, DryRunSupport, DatabaseSupport {

    boolean pruneConflictsByUser = false

    void configure(String[] args) {
        CliBuilder cli = new CliBuilder(
                usage: 'chq repairDuplicates [-d|--dryrun]',
                header: 'This command will merge all domains that have conflicting values caused from ' +
                        'case-insensitivty. For example, two cellars with "foo" and "Foo" screennames are ' +
                        'possible from the SimpleDB migration, but should be considered the same.'
        )
        cli.d(longOpt: 'dryrun', required: false, 'Dry run; no actions will be taken')

        OptionAccessor opt = parseCli(cli)

        dryRun = (boolean) opt.getProperty('d')
    }

    boolean run() {
        dryRunBanner(5)

        List<List<Record>> failures = []
        int successful = 0
        int skipped = 0

        String input = promptForInput(PromptType.BOOLEAN, 'Do you want to be prompted for reviewing & confirming conflicts? RECOMMENDED')
        pruneConflictsByUser = input == BooleanPromptType.YES

        onlyIfYes('Do you want to repair cellars?', {
            CellarMerger cellarMerger = new CellarMerger(create)

            Map<CellarRecord, CellarRecord> cellars = cellarMerger.conflicts()
            reviewConflicts(CellarRecord, cellars, CELLAR.SCREEN_NAME)

            cellars.eachWithIndex { Map.Entry<CellarRecord, CellarRecord> entry, int i ->
                println('')
                println('Merging - ' + renderPair(entry.key, entry.value, CELLAR.SCREEN_NAME))

                List<Record> records = cellarMerger.determineSourceAndTarget(entry.key, entry.value)
                if (records.empty) {
                    println('## ERR: No cellars found for merging. This should never happen.')
                    skipped++
                    return
                }
                boolean result = cellarMerger.merge((CellarRecord) records[0], (CellarRecord) records[1])
                if (!result) {
                    println('## ERR: Could not merge cellars')
                    failures.add(records)
                } else {
                    successful++
                }
            }
        })

        //TODO we shouldn't really bother to do organizations until later. This is a pretty daunting task on its own.
        onlyIfYes('Do you want to repair organizations?', {
            OrganizationMerger organizationMerger = new OrganizationMerger(create)

            Map<OrganizationRecord, OrganizationRecord> organizations = organizationMerger.conflicts()
            reviewConflicts(OrganizationRecord, organizations, ORGANIZATION.NAME)

            organizations.eachWithIndex { Map.Entry<OrganizationRecord, OrganizationRecord> entry, int i ->
                println('')
                println('Merging - ' + renderPair(entry.key, entry.value, ORGANIZATION.NAME))

                List<Record> records = organizationMerger.determineSourceAndTarget(entry.key, entry.value)
                if (records.empty) {
                    println('## ERR: No organizations found for merging. This should never happen')
                    skipped++
                    return
                }
                boolean result = organizationMerger.merge((OrganizationRecord) records[0], (OrganizationRecord) records[1])
                if (!result) {
                    println('## ERR: Could not merge organizations')
                    failures.add(records)
                } else {
                    successful++
                }
            }
        })

        println('Done!')

        println('###################################################')
        println('## Repair complete.')
        println('Success:  ' + successful)
        println('Skipped:  ' + skipped)
        println('Failures: ' + failures.size())

        return failures.size() == 0
    }

    void reviewConflicts(Class<Record> type, Map<Record, Record> conflictMap, Field compareField) {
        println('###################################################')
        println("## Conflict Summary: ${type.simpleName}")
        println("Duplicates: ${conflictMap.size()*2}")
        println('')

        if (conflictMap.size()) {
            conflictMap.eachWithIndex { Map.Entry<Record, Record> entry, int i ->
                println(renderPair(entry.key, entry.value, compareField))
            }

            if (pruneConflictsByUser &&
                promptForInput(PromptType.BOOLEAN, 'Do you need to make any modifications?') == BooleanPromptType.YES) {

                println('')
                println('## Manually resolving conflicts:')
                println('Type the LEFT value for any records you want to remove from conflict resolution.')
                println("Once you're done, hit enter.")

                String input
                while (input = promptForInput(PromptType.TEXT, 'next?')) {
                    Map.Entry<Record, Record> entry = conflictMap.find { it.key.getValue(compareField) == input }
                    if (entry) {
                        conflictMap.remove(entry.key)
                    } else {
                        println("WARN: Couldn't find that record. Try again.")
                    }
                }
            }
        }
    }

    String renderPair(Record a, Record b, Field compareField) {
        return "${a.getValue(compareField)} : ${b.getValue(compareField)}"
    }

    void onlyIfYes(String message, Closure op) {
        if (promptForInput(PromptType.BOOLEAN, message) == BooleanPromptType.YES) {
            op()
        }
    }
}
