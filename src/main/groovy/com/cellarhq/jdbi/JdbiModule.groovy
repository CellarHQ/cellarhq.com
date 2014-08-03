package com.cellarhq.jdbi

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import org.skife.jdbi.v2.DBI

class JdbiModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DBI).toProvider(JdbiProvider).in(Scopes.SINGLETON)
    }
}
