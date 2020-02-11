package cellarhq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;

public class SlowRequestLogger implements Handler {
  Logger LOGGER = LoggerFactory.getLogger(SlowRequestLogger.class);

  @Override
  public void handle(Context ctx) throws Exception {
    ctx.onClose( requestOutcome -> {
      if (requestOutcome.getDuration().toMillis() > 500) {
        LOGGER.warn("Request over 500ms " + requestOutcome .getRequest().getMethod().getName() + " "  + requestOutcome.getRequest().getUri());
      }
      });
    ctx.next();
  }
}
