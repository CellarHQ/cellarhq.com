package com.cellarhq.endpoints

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.Messages
import com.cellarhq.domain.jooq.Cellar
import com.cellarhq.services.JooqCellarService
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.core.profile.UserProfile
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.form.Form
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

@SuppressWarnings(['AbcMetric', 'NestedBlockDepth'])
@Slf4j
class SettingsEndpoint extends GroovyHandler {

    private final JooqCellarService cellarService
    private final ValidatorFactory validatorFactory

    @Inject
    SettingsEndpoint(JooqCellarService cellarService, ValidatorFactory validatorFactory) {
        this.cellarService = cellarService
        this.validatorFactory = validatorFactory
    }

    @Override
    protected void handle(GroovyContext context) {
        context.with {
            byMethod {
                get {
                    UserProfile profile = request.get(UserProfile)
                    blocking {
                        cellarService.get(Long.valueOf(profile.id))
                    } then { Cellar cellar ->
                        render handlebarsTemplate('settings.html',
                                title: 'Account Settings',
                                isOauthAccount: profile instanceof TwitterProfile,
                                loggedIn: true,
                                error: request.queryParams.error ?: '',
                                errorMessages: SessionUtil.getFlashMessages(request).collect { [message: it] },
                                success: request.queryParams.success ?: '',
                                pageId: 'settings',
                                cellar: cellar)
                    }
                }

                post {
                    Form form = parse(Form)
                    Validator validator = validatorFactory.validator
                    UserProfile profile = request.get(UserProfile)

                    blocking {
                        Map result = [
                                success: false,
                                message: '',
                                cellar: (Cellar) null
                        ]

                        Cellar cellar = cellarService.get(Long.valueOf(profile.id))
                        if (cellar) {
                            cellar.with {
                                displayName = form.displayName
                                location = form.location
                                website = form.website
                                bio = form.bio
                                updateFromNetwork = form.updateFromNetwork
                                setPrivate((Boolean) form.private)
                            }

                            // TODO Photo

                            Set<ConstraintViolation<Cellar>> cellarViolations = validator.validate(cellar)
                            if (cellarViolations.size() > 0) {
                                result.message = Messages.FORM_VALIDATION_ERROR
                                result.cellar = cellar
                                result.violations = cellarViolations.collect {
                                    [message: "${it.propertyPath.toString()} ${it.message}"]
                                }
                            } else {
                                cellarService.save(cellar)
                                result.success = true
                            }
                        }
                        result
                    } onError { Throwable t ->
                        log.error(LogUtil.toLog('SaveSettingsFailure', [
                                exception: t
                        ]))
                        redirect('/settings?error=' + Messages.UNEXPECTED_SERVER_ERROR)
                    } then { Map result ->
                        if (result.success) {
                            redirect('/settings?success=' + Messages.SETTINGS_SAVED)
                        } else {
                            render handlebarsTemplate('settings.html', renderPageSettings([
                                    isOauthAccount: profile instanceof TwitterProfile,
                                    error: result.message,
                                    errorMessages: result.violations,
                                    cellar: result.cellar
                            ]))
                        }
                    }
                }
            }
        }
    }

    private Map renderPageSettings(Map options) {
        Map settings = [
                title: 'Account Settings',
                pageId: 'settings',
                isLoggedIn: true
        ]
        settings.putAll(options)
        return settings
    }
}
