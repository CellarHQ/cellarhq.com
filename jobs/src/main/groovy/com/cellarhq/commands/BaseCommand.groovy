package com.cellarhq.commands

import groovy.transform.CompileStatic

@CompileStatic
abstract class BaseCommand {

    OptionAccessor parseCli(CliBuilder cli, String[] args, int expectedNumArguments = 0) {
        OptionAccessor opt = cli.parse(args)
        if (!opt) {
            throw new IllegalStateException('CLI opts could not be parsed')
        }

        if (expectedNumArguments > 0 && opt.arguments().size() != expectedNumArguments) {
            throw new IllegalStateException("Expected ${expectedNumArguments} arguments, got ${opt.arguments().size()}")
        }

        return opt
    }
}
