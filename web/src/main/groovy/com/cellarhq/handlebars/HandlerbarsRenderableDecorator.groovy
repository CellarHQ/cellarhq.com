package com.cellarhq.handlebars

import com.cellarhq.CellarHQConfig
import com.cellarhq.auth.CellarHQFormClient
import com.cellarhq.session.FlashMessage
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.client.Clients
import org.pac4j.core.profile.CommonProfile
import org.pac4j.oauth.client.TwitterClient
import ratpack.handlebars.Template
import ratpack.handling.Context
import ratpack.http.Request
import ratpack.pac4j.internal.RatpackWebContext
import ratpack.render.RenderableDecorator
import ratpack.session.store.SessionStorage

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
    Object decorate(Context context, Object template) {
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

        model[MODEL_LOGGED_IN] = SessionUtil.isLoggedIn(context.request.maybeGet(CommonProfile))
        applyFlashMessages(context.request, model)

        if (GOOGLE_ANALYTICS_CODE) {
            model[MODEL_GA_TRACKING_CODE] = GOOGLE_ANALYTICS_CODE
        }

        model[MODEL_REQUEST] = context.request

        model[MODEL_HOSTNAME] = HOSTNAME

        Clients clients = context.request.get(Clients)
        RatpackWebContext webContext = new RatpackWebContext(context)
        model[MODEL_AUTH_FORM_URL] = clients.findClient(CellarHQFormClient).getRedirectionUrl(webContext)

        try {
            model[MODEL_AUTH_TWITTER_URL] = clients.findClient(TwitterClient).getRedirectionUrl(webContext)
        } catch (e) {
            log.warn('WTF', e)
        }

        return template
    }

    static void applyFlashMessages(Request request, Map<String, ?> model) {
        SessionStorage session = request.get(SessionStorage)

        if (request.queryParams.error) {
            FlashMessage msg = FlashMessage.error(request.queryParams.error)
            session.put(msg.type.name, msg)
        }
        if (request.queryParams.success) {
            FlashMessage msg = FlashMessage.success(request.queryParams.success)
            session.put(msg.type.name, msg)
        }

        FlashMessage.Type.values().each { FlashMessage.Type type ->
            String name = type.name
            String messagesName = type.messagesName

            if (session.containsKey(name)) {
                FlashMessage flashMessage = (FlashMessage) session.remove(name)
                model[name] = flashMessage.message
                model[messagesName] = flashMessage.embeddedMessages

                if (flashMessage.socialButton) {
                    model[(String) "show${name.capitalize()}SocialButton"] = true
                    model[(String) "${name}SocialButtonMessage"] = flashMessage.socialButton.message
                    model[(String) "${name}SocialButtonUrl"] = flashMessage.socialButton.url
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
