package com.cellarhq.functional

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.guice.Guice
import ratpack.impose.ImpositionsSpec
import ratpack.impose.UserRegistryImposition
import ratpack.remote.RemoteControl

class CellarHqApplication extends GroovyRatpackMainApplicationUnderTest {
  @Override
  protected void addImpositions(ImpositionsSpec impositions) {
    impositions.add(UserRegistryImposition.of(
      Guice.registry {
        it.bindInstance RemoteControl.handlerDecorator()
      }
    ))
  }
}
