package com.cellarhq.support

import groovy.transform.CompileStatic

@CompileStatic
trait DryRunSupport {

  boolean dryRun = true

  void dryRunBanner(int waitSeconds) {
    if (!dryRun) {
      println('###################################################')
      println("## THIS IS NOT A DRY RUN (starting in ${waitSeconds} seconds) ##")
      println('###################################################')
      sleep(waitSeconds * 1000)
    }
  }

  void destructive(String description, Closure<Integer> operation) {
    println("## ${description}")
    if (!dryRun) {
      int rowsAffected = operation.call()
      println("  ${rowsAffected} rows affected")
    }
  }
}
