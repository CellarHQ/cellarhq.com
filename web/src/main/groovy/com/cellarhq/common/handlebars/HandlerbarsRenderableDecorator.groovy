package com.cellarhq.common.handlebars

import com.cellarhq.auth.profiles.CellarHQProfile
import com.cellarhq.common.CellarHQConfig
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.client.Clients
import org.pac4j.core.context.WebContext
import org.pac4j.http.client.FormClient
import org.pac4j.oauth.client.TwitterClient
import ratpack.exec.Promise
import ratpack.handlebars.Template
import ratpack.handling.Context
import ratpack.pac4j.RatpackPac4j
import ratpack.render.RenderableDecorator
import ratpack.session.Session

@Slf4j
class HandlerbarsRenderableDecorator implements RenderableDecorator {
  private final static String DEFAULT_PAGE_ID = 'generic'
  private final static String DEFAULT_TITLE = 'CellarHQ'

  private final static String MODEL_PAGE_ID = 'pageId'
  private final static String MODEL_PAGE_ID_CSS_CLASS = 'pageIdCssClass'
  private final static String MODEL_TITLE = 'title'
  private final static String MODEL_LOGGED_IN = 'loggedIn'
  private final static String MODEL_PAGE_URI = 'pageUri'
  private final static String MODEL_REQUEST = 'request'
  private final static String MODEL_GA_TRACKING_CODE = 'gaTrackingCode'
  private final static String MODEL_HOSTNAME = 'hostname'
  private final static String MODEL_AUTH_FORM_URL = 'authFormUrl'
  private final static String MODEL_AUTH_TWITTER_URL = 'authTwitterUrl'

  private final String HOSTNAME
  private final String GOOGLE_ANALYTICS_CODE

  @Inject
  HandlerbarsRenderableDecorator(CellarHQConfig config) {
    HOSTNAME = config.hostName
    GOOGLE_ANALYTICS_CODE = config.googleAnalyticsTrackingCode
  }

  @Override
  Class getType() {
    return Template
  }

  @Override
  Promise decorate(Context context, Object template) {
    Map<String, ?> model = (Map<String, ?>) template.model
    if (model == null) {
      model = [:]
    }

    if (!model.containsKey(MODEL_PAGE_ID)) {
      logMissingVariable(MODEL_PAGE_ID, context.request.uri)
      model[MODEL_PAGE_ID] = DEFAULT_PAGE_ID
    }
    model[MODEL_PAGE_ID_CSS_CLASS] = ((String) model[MODEL_PAGE_ID]).replaceAll(/\./, '')

    if (!model.containsKey(MODEL_TITLE)) {
      logMissingVariable(MODEL_TITLE, context.request.uri)
      model[MODEL_TITLE] = DEFAULT_TITLE
    }

    if (!model.containsKey(MODEL_PAGE_URI)) {
      model[MODEL_PAGE_URI] = "/${context.request.path}"
    }

    model[MODEL_LOGGED_IN] = context.maybeGet(CellarHQProfile).isPresent()
    applyFlashMessages(context, model)

    if (GOOGLE_ANALYTICS_CODE) {
      model[MODEL_GA_TRACKING_CODE] = GOOGLE_ANALYTICS_CODE
    }

    model[MODEL_REQUEST] = context.request

    model[MODEL_HOSTNAME] = HOSTNAME

    return RatpackPac4j.webContext(context).flatMap { WebContext webContext ->

      final Optional<Clients> optionalClients = context.maybeGet(Clients.class);

      optionalClients.ifPresent { Clients clients ->
        final TwitterClient twClient = clients.findClient(TwitterClient.class);
        final FormClient formClient = clients.findClient(FormClient)
        final String twUrl = twClient.getRedirectionUrl(webContext);
        final String formUrl = formClient.getRedirectionUrl(webContext);
        model.put("twitterUrl", twUrl);
        model[MODEL_AUTH_FORM_URL] = formUrl
        model[MODEL_AUTH_TWITTER_URL] = twUrl
      }

      context.execution.promiseOf(template)
    }
  }

  static void applyFlashMessages(Context ctx, Map<String, ?> model) {
    ctx.get(Session).getData().then { sessionData ->
      if (ctx.request.queryParams.error) {
        log.debug("Setting error message in session")
        FlashMessage msg = FlashMessage.error(ctx.request.queryParams.error)
        sessionData.set(msg.type.name, msg)
      }

      if (ctx.request.queryParams.success) {
        log.debug("Setting success message in session")
        FlashMessage msg = FlashMessage.success(ctx.request.queryParams.success)
        sessionData.set(msg.type.name, msg)
      }

      FlashMessage.Type.values().each { FlashMessage.Type type ->
        String name = type.name
        String messagesName = type.messagesName

        Optional<FlashMessage> optionalFlashMessage = sessionData.get(name)
        log.debug("Getting ${name} flash messages")
        optionalFlashMessage.ifPresent { FlashMessage flashMessage ->
          log.debug("${name} flash messages are present")
          model[name] = flashMessage.message
          model[messagesName] = flashMessage.embeddedMessages

          if (flashMessage.socialButton) {
            model[(String) "show${name.capitalize()}SocialButton"] = true
            model[(String) "${name}SocialButtonMessage"] = flashMessage.socialButton.message
            model[(String) "${name}SocialButtonUrl"] = flashMessage.socialButton.url
          }

          sessionData.remove(name)
        }
      }
    }
  }

  private static void logMissingVariable(String variableName, String uri) {
    log.warn(LogUtil.toLog('HandlebarsTemplateRendererImpl', [
      uri: uri,
      msg: "Missing '${variableName}' model variable"
    ]))
  }

}
