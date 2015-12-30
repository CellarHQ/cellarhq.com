package com.cellarhq.functional

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.guice.Guice
import ratpack.registry.Registry
import ratpack.remote.RemoteControl

class CellarHqApplication extends GroovyRatpackMainApplicationUnderTest {
  protected Registry createOverrides(Registry serverRegistry) throws Exception {
    return Guice.registry {
      it.bindInstance RemoteControl.handlerDecorator()
    }.apply(serverRegistry)
  }
}
