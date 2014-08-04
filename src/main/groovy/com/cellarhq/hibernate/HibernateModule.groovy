package com.cellarhq.hibernate

import com.google.common.collect.ImmutableList
import com.google.inject.AbstractModule
import com.google.inject.Scopes
import groovy.transform.CompileStatic
import org.hibernate.SessionFactory

@CompileStatic
class HibernateModule extends AbstractModule {

    private final ImmutableList<Class<?>> entities

    HibernateModule(Class<?>... entities) {
        this.entities = ImmutableList.builder().add(entities).build()
    }

    @Override
    protected void configure() {
        bind(AnnotatedEntities).toInstance(new AnnotatedEntities(entities))
        bind(SessionFactory).toProvider(SessionFactoryProvider).in(Scopes.SINGLETON)
    }
}
