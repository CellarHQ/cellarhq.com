package com.cellarhq.functional

import ratpack.guice.Guice
import ratpack.registry.Registry
import ratpack.remote.RemoteControl
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest

class CellarHqApplication extends GroovyRatpackMainApplicationUnderTest {
  protected Registry createOverrides(Registry serverRegistry) throws Exception {
    return Guice.registry {
      it.bindInstance RemoteControl.handlerDecorator()
    }.apply(serverRegistry)
  }
}
