package com.cellarhq.functional

import ratpack.registry.Registry
import ratpack.registry.Registries
import ratpack.remote.RemoteControl
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest

class CellarHqApplication extends GroovyRatpackMainApplicationUnderTest {
    protected Registry createOverrides(Registry serverRegistry) throws Exception {
        return Registries.registry {
            it.add(RemoteControl.handlerDecorator())
        }
    }
}
