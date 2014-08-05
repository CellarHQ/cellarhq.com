package com.cellarhq.ratpack.hibernate

import com.google.common.collect.Sets
import com.google.inject.Inject
import com.google.inject.Provider
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import com.zaxxer.hikari.hibernate.HikariConnectionProvider
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.AvailableSettings
import org.hibernate.cfg.Configuration
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider
import org.hibernate.service.ServiceRegistry

import javax.sql.DataSource

/**
 * Builds and provides the Hibernate Session Factory.
 */
@SuppressWarnings(['VariableName'])
@Slf4j
@CompileStatic
class SessionFactoryProvider implements Provider<SessionFactory> {

    private final AnnotatedEntities annotatedEntities
    private final DataSource dataSource
    private final HikariConfig hikariConfig

    @Inject
    SessionFactoryProvider(AnnotatedEntities entities, DataSource dataSource, HikariConfig hikariConfig) {
        annotatedEntities = entities
        this.dataSource = dataSource
        this.hikariConfig = hikariConfig
    }

    @Override
    SessionFactory get() {
        ConnectionProvider connectionProvider = buildConnectionProvider()

        return buildSessionFactory(connectionProvider)
    }

    private ConnectionProvider buildConnectionProvider() {
        final HikariConnectionProvider connectionProvider = new HikariConnectionProvider(
                hds: (HikariDataSource) dataSource,
                hcfg: hikariConfig
        )
        return connectionProvider
    }

    SessionFactory buildSessionFactory(ConnectionProvider connectionProvider) {
        final Configuration configuration = new Configuration()

        // Thread contexts seem the best choice since we'll be putting DB interactions away inside of blocking threads
        configuration.with {
            setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, 'thread')
            setProperty(AvailableSettings.USE_SQL_COMMENTS, 'true')
            setProperty(AvailableSettings.USE_GET_GENERATED_KEYS, 'true')
            setProperty(AvailableSettings.GENERATE_STATISTICS, 'true')
            setProperty(AvailableSettings.USE_REFLECTION_OPTIMIZER, 'true')
            setProperty(AvailableSettings.ORDER_UPDATES, 'true')
            setProperty(AvailableSettings.ORDER_INSERTS, 'true')
            setProperty(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, 'true')
            setProperty('jadira.usertype.autoRegisterUserTypes', 'true')
        }

        addAnnotatedClasses(configuration, annotatedEntities.entities)

        final ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .addService(ConnectionProvider, connectionProvider)
                .build()

        return configuration.buildSessionFactory(registry)
    }

    private void addAnnotatedClasses(Configuration configuration, Iterable<Class<?>> entities) {
        final SortedSet<String> entityClasses = Sets.newTreeSet()
        entities.each { Class<?> clazz ->
            configuration.addAnnotatedClass(clazz)
            entityClasses.add(clazz.canonicalName)
        }
        log.info("Entity classes: ${entityClasses.join(',')}")
    }
}
