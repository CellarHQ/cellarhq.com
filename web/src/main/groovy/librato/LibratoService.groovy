package librato

import com.codahale.metrics.MetricRegistry
import com.librato.metrics.LibratoReporter
import groovy.util.logging.Slf4j
import ratpack.server.Service
import ratpack.server.StartEvent

import java.util.concurrent.TimeUnit

@Slf4j
class LibratoService implements Service {
  LibratoConfig config
  MetricRegistry metricRegistry

  public LibratoService(LibratoConfig libratoConfig, final MetricRegistry metricRegistry) {
    this.config = libratoConfig
    this.metricRegistry = metricRegistry
  }

  @Override
  public void onStart(StartEvent event) throws Exception {
    if (config.isValid) {
      LibratoReporter.enable(
        LibratoReporter.builder(
          metricRegistry,
          config.email,
          config.token,
          config.sourceIdentifier),
          10,
          TimeUnit.SECONDS)
      log.info('Librato Metrics reporter configured.')
    } else {
      log.warn('Librato Metrics Reporter not configured.')
    }
  }
}
