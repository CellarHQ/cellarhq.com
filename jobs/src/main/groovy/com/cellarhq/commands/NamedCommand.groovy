package com.cellarhq.commands

import groovy.transform.CompileStatic

@CompileStatic
trait NamedCommand {

  /**
   * Get the name of a command; this must be unique. By default, this method will return a unique name.
   * @return
   */
  String getName() {
    String name = getClass().simpleName.replace('Command', '')
    return name[0].toLowerCase() + name.substring(1)
  }

  /**
   * Allows configuration of the command; passing in all arguments from the command line.
   * @param args
   */
  void configure(String[] args) {
  }

  /**
   * Runs the command.
   * @return Whether or not the command was successful.
   */
  abstract boolean run()
}
