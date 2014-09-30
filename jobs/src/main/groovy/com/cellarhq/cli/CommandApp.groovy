package com.cellarhq.cli

import com.cellarhq.commands.CellarMergeCommand
import com.cellarhq.commands.NamedCommand
import groovy.transform.CompileStatic

/**
 * Offers an entry point into CLI jobs.
 */
@CompileStatic
class CommandApp {

    final static void main(String[] args) {
        if (args.size() == 0) {
            println('usage: chq [command]')
            System.exit(1)
        }

        NamedCommand command = [
                new CellarMergeCommand()
        ].find { it.name == args[0] }
        if (!command) {
            throw new IllegalArgumentException("Cannot find command with name '${args[0]}'")
        }

        String[] commandArgs = []
        if (args.size() > 1) {
            commandArgs = args[1..args.size()-1]
        }
        command.configure((String[]) commandArgs)
        System.exit(command.run() ? 0 : 1)
    }
}
