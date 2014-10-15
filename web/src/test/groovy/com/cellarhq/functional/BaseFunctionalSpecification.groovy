package com.cellarhq.functional

import com.cellarhq.LiquibaseSupport
import geb.spock.GebReportingSpec

class BaseFunctionalSpecification extends GebReportingSpec implements LiquibaseSupport {

    def setupSpec() {
        runLiquibase()
    }
}
