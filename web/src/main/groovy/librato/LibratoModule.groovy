package librato

import com.codahale.metrics.MetricRegistry
import com.google.inject.Provides
import com.google.inject.Singleton
import ratpack.guice.ConfigurableModule
/**
 * Created by kyleboon on 1/19/16.
 */
class LibratoModule extends ConfigurableModule<LibratoConfig> {
  @Override
  protected void configure() { }

  @Provides
  @Singleton
  LibratoService libratoService(LibratoConfig config, final MetricRegistry metricRegistry) {
    return new LibratoService(config, metricRegistry)
  }
}
