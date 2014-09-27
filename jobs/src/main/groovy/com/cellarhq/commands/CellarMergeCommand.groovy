package com.cellarhq.commands

import com.cellarhq.commands.support.DatabaseSupport
import groovy.transform.CompileStatic

/**
 * Allows merging one cellar into another.
 *
 * This command will not setup redirects; the source Cellar will be removed and any links that lead to it externally
 * will cease to resolve correctly.
 */
@CompileStatic
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
        cli.option('d', [longOpt: 'dryrun', required: false], 'Dry run; no actions will be taken')

        OptionAccessor opt = cli.parse(args)
        if (!opt) {
            throw new IllegalStateException('CLI opts could not be parsed')
        }

        targetCellarId = Long.valueOf(opt.arguments().get(0))
        sourceCellarId = Long.valueOf(opt.arguments().get(1))
        dryRun = (boolean) opt.getProperty('d')

        if (targetCellarId == sourceCellarId) {
            throw new IllegalStateException('Source and target cannot be the same')
        }
    }

    boolean run() {
        println(targetCellarId)
        println(sourceCellarId)
        return true
    }
}
