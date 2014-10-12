package com.cellarhq.commands.support

import groovy.transform.CompileStatic

@CompileStatic
trait ProgressSupport {

    private long iteration = 0
    private int grouping = 0
    private int currentColumns = 0

    void resetProgressAnts(Integer groupBy) {
        iteration = 0
        currentColumns = 0
        grouping = groupBy?:1
    }

    void incrementProgressAnts() {
        iteration += 1
        if (iteration == grouping) {
            currentColumns += 1
            if (currentColumns == 60) {
                currentColumns = 0
                println('.')
            } else {
                print('.')
            }
        }
    }
}
