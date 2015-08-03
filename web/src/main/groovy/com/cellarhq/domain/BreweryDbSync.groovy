package com.cellarhq.domain

import com.cellarhq.generated.tables.pojos.BrewerydbSync
import com.cellarhq.util.JooqUtil
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import ratpack.form.Form

@CompileStatic
@InheritConstructors
class BreweryDbSync extends BrewerydbSync {

    static BreweryDbSync makeFrom(Form request) {
        return new BreweryDbSync().with { BreweryDbSync self ->
            id = JooqUtil.uuid()
            attribute = request.attribute
            attributeId = request.attributeId
            action = request.action
            subAttributeId = request.subAttributeId ?: null
            subAction = request.subAction ?: null
            brewerydbChanged = Integer.valueOf((String) request.timestamp ?: '0')

            return self
        }
    }
}
