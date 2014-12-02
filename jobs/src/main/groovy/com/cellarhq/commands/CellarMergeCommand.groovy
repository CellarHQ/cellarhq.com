package com.cellarhq.commands

import static com.cellarhq.generated.Tables.CELLAR

import com.cellarhq.generated.tables.records.CellarRecord
import com.cellarhq.repair.merger.CellarMerger
import com.cellarhq.support.DatabaseSupport
import com.cellarhq.support.DryRunSupport

/**
 * Allows merging one cellar into another.
 *
 * This command will not setup redirects; the source Cellar will be removed and any links that lead to it externally
 * will cease to resolve correctly.
 */
class CellarMergeCommand implements NamedCommand, DatabaseSupport, DryRunSupport {

    Long targetCellarId
    Long sourceCellarId

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
        CellarRecord sourceCellar = getCellar(sourceCellarId)
        CellarRecord targetCellar = getCellar(targetCellarId)

        println('SOURCE: ' + sourceCellar.screenName)
        println('TARGET: ' + targetCellar.screenName)

        dryRunBanner(5)

        CellarMerger merger = new CellarMerger(create)
        merger.dryRun = dryRun

        return merger.merge(sourceCellar, targetCellar)
    }

    CellarRecord getCellar(Long id) {
        create.selectFrom(CELLAR)
                .where(CELLAR.ID.eq(id))
                .fetchOne()
    }
}
