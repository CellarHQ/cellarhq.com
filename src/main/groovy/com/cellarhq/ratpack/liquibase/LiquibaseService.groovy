package com.cellarhq.ratpack.liquibase

import com.google.inject.Inject
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import liquibase.Contexts
import liquibase.Liquibase
import liquibase.configuration.ConfigurationProperty
import liquibase.configuration.ConfigurationValueProvider
import liquibase.configuration.GlobalConfiguration
import liquibase.configuration.LiquibaseConfiguration
import liquibase.database.Database
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import liquibase.resource.CompositeResourceAccessor
import liquibase.resource.FileSystemResourceAccessor
import liquibase.resource.ResourceAccessor
import liquibase.util.NetUtil
import ratpack.launch.LaunchConfig

import javax.sql.DataSource
@java.lang.SuppressWarnings('JdbcConnectionReference') // Jeez...
import java.sql.Connection

/**
 * Adapted from the LiquibaseServletListener class.
 * @see https://github.com/liquibase/liquibase
 */
@SuppressWarnings('JdbcConnectionReference')
@Slf4j
@CompileStatic
class LiquibaseService {

    private final static String LIQUIBASE_CHANGELOG = 'liquibase.changelog'
    private final static String LIQUIBASE_CONTEXTS = 'liquibase.contexts'
    private final static String LIQUIBASE_HOST_EXCLUDES = 'liquibase.host.excludes'
    private final static String LIQUIBASE_HOST_INCLUDES = 'liquibase.host.includes'
    private final static String LIQUIBASE_ONERROR_FAIL = 'liquibase.onerror.fail'
    private final static String LIQUIBASE_SCHEMA_DEFAULT = 'liquibase.schema.default'

    RatpackConfigurationValueProvider configurationValueProvider = new RatpackConfigurationValueProvider()

    DataSource dataSource

    String hostName

    LaunchConfig launchConfig

    @Inject
    LiquibaseService(DataSource dataSource) {
        this.dataSource = dataSource
    }

    @SuppressWarnings(['CatchException', 'UnnecessaryNullCheck'])
    void run() {
        log.info('Running Liquibase...')
        LiquibaseConfiguration.instance.init(configurationValueProvider)

        try {
            hostName = NetUtil.localHostName
        } catch (Exception e) {
            log.error('Cannot find hostname: ' + e.message)
        }

        if (hostName) {
            String failOnError = null
            try {
                failOnError = configurationValueProvider.getValue(LIQUIBASE_ONERROR_FAIL)
                if (checkPreconditions()) {
                    executeUpdate()
                }
            } catch (Exception e) {
                if (failOnError != null && failOnError.asBoolean()) {
                    throw new FailedExecutionException(e)
                }
            }
        }
        log.info('Liquibase done!')
    }

    protected boolean checkPreconditions() {
        GlobalConfiguration globalConfiguration = LiquibaseConfiguration.instance.getConfiguration(GlobalConfiguration)
        if (!globalConfiguration.shouldRun) {
            log.info("Liquibase did not run on ${hostName} because "
                    + LiquibaseConfiguration.instance.describeValueLookupLogic(
                            globalConfiguration.getProperty(GlobalConfiguration.SHOULD_RUN))
                    + ' was set to false')
            return false
        }

        String machineIncludes = (String) configurationValueProvider.getValue(LIQUIBASE_HOST_INCLUDES)
        String machineExcludes = (String) configurationValueProvider.getValue(LIQUIBASE_HOST_EXCLUDES)

        boolean shouldRun = false
        if (machineIncludes == null && machineExcludes == null) {
            shouldRun = true
        } else if (machineIncludes != null) {
            machineIncludes.split(',')*.trim().each { String machine ->
                if (hostName.equalsIgnoreCase(machine)) {
                    shouldRun = true
                }
            }
        } else if (machineExcludes != null) {
            shouldRun = true
            machineExcludes.split(',')*.trim().each { String machine ->
                if (hostName.equalsIgnoreCase(machine)) {
                    shouldRun = false
                }
            }
        }

        boolean wasOverridden = globalConfiguration.getProperty(GlobalConfiguration.SHOULD_RUN).wasOverridden
        if (globalConfiguration.shouldRun && wasOverridden) {
            shouldRun = true
            log.info("Ignoring ${LIQUIBASE_HOST_INCLUDES} and ${LIQUIBASE_HOST_EXCLUDES}, since "
                    + LiquibaseConfiguration.instance.describeValueLookupLogic(
                            globalConfiguration.getProperty(GlobalConfiguration.SHOULD_RUN))
                    + '=true')
        }
        if (!shouldRun) {
            log.info("LiquibaseService did not run due to ${LIQUIBASE_HOST_INCLUDES} and/or ${LIQUIBASE_HOST_EXCLUDES}")
            return false
        }
        return true
    }

    protected void executeUpdate() {
        String changeLogFile = configurationValueProvider.getValue(LIQUIBASE_CHANGELOG)
        if (changeLogFile == null) {
            throw new MissingConfigurationException("Cannot run Liquibase: 'changeLogFile' is not set")
        }

        String contexts = configurationValueProvider.getValue(LIQUIBASE_CONTEXTS)
        String defaultSchema = configurationValueProvider.getValue(LIQUIBASE_SCHEMA_DEFAULT)

        Connection connection = null
        Database database = null
        try {
            connection = dataSource.connection

            Thread currentThread = Thread.currentThread()
            ClassLoader contextClassLoader = currentThread.contextClassLoader
            ResourceAccessor threadClFO = new ClassLoaderResourceAccessor(contextClassLoader)

            ResourceAccessor clFO = new ClassLoaderResourceAccessor()
            ResourceAccessor fsFO = new FileSystemResourceAccessor()

            database = DatabaseFactory.instance.findCorrectDatabaseImplementation(new JdbcConnection(connection))
            database.defaultSchemaName = defaultSchema

            Liquibase liquibase = new Liquibase(changeLogFile,
                                                new CompositeResourceAccessor(clFO, fsFO, threadClFO), database)
            liquibase.update(new Contexts(contexts))
        } finally {
            if (database != null) {
                database.close()
            } else if (connection != null) {
                connection.close()
            }
        }
    }

    /**
     * Gets configuration values from the Ratpack registry.
     */
    class RatpackConfigurationValueProvider implements ConfigurationValueProvider {

        Object getValue(String property) {
            launchConfig.getOther(property, null)
        }

        @Override
        Object getValue(String namespace, String property) {
            return getValue("${namespace}.${property}")
        }

        @Override
        String describeValueLookupLogic(ConfigurationProperty property) {
            return "Ratpack configuration '${property.namespace}.${property.name}'"
        }
    }
}
