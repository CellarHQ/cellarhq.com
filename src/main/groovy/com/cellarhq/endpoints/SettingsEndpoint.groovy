package com.cellarhq.endpoints

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.Messages
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import com.cellarhq.services.CellarService
import com.cellarhq.session.FlashMessage
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

    private final CellarService cellarService
    private final ValidatorFactory validatorFactory

    @Inject
    SettingsEndpoint(CellarService cellarService, ValidatorFactory validatorFactory) {
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
                        cellarService.getBlocking((Long) profile.getAttribute(SecurityModule.SESSION_CELLAR_ID))
                    } then { Cellar cellar ->
                        render handlebarsTemplate('settings.html',
                                title: 'Account Settings',
                                isOauthAccount: profile instanceof TwitterProfile,
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
                                cellar: (Cellar) null
                        ]

                        Cellar cellar = cellarService.getBlocking(
                                (Long) profile.getAttribute(SecurityModule.SESSION_CELLAR_ID))
                        if (cellar) {
                            cellar.with {
                                displayName = form.displayName
                                location = form.location
                                website = form.website
                                bio = form.bio
                                updateFromNetwork = form.updateFromNetwork
                                setPrivate((Boolean) form.private)
                                reddit = form.reddit
                                twitter = form.twitter
                                beeradvocate = form.beeradvocate
                                ratebeer = form.ratebeer
                            }

                            // TODO Photo

                            Set<ConstraintViolation<Cellar>> cellarViolations = validator.validate(cellar)
                            if (cellarViolations.size() > 0) {
                                SessionUtil.setFlash(
                                        request,
                                        FlashMessage.error(Messages.FORM_VALIDATION_ERROR, cellarViolations.collect {
                                            "${it.propertyPath.toString()} ${it.message}"
                                        })
                                )
                                result.cellar = cellar
                            } else {
                                cellarService.saveBlocking(cellar)
                                result.success = true
                            }
                        }
                        result
                    } onError { Throwable t ->
                        log.error(LogUtil.toLog('SaveSettingsFailure', [
                                exception: t
                        ]))
                        SessionUtil.setFlash(request, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
                        redirect('/settings')
                    } then { Map result ->
                        if (result.success) {
                            SessionUtil.setFlash(request, FlashMessage.success(Messages.SETTINGS_SAVED))
                            redirect('/settings')
                        } else {
                            render handlebarsTemplate('settings.html', renderPageSettings([
                                    title: 'Account Settings',
                                    pageId: 'settings',
                                    isOauthAccount: profile instanceof TwitterProfile,
                                    cellar: result.cellar
                            ]))
                        }
                    }
                }
            }
        }
    }
}
