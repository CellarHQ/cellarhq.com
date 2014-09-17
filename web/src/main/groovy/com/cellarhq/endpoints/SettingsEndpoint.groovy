package com.cellarhq.endpoints

import static ratpack.handlebars.Template.handlebarsTemplate

import com.cellarhq.Messages
import com.cellarhq.auth.SecurityModule
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.Photo
import com.cellarhq.services.CellarService
import com.cellarhq.services.photo.PhotoService
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
import ratpack.session.store.SessionStorage

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

@Slf4j
class SettingsEndpoint extends GroovyHandler {

    private final CellarService cellarService
    private final PhotoService photoService
    private final ValidatorFactory validatorFactory

    @Inject
    SettingsEndpoint(ValidatorFactory validatorFactory, CellarService cellarService, PhotoService photoService) {
        this.validatorFactory = validatorFactory
        this.cellarService = cellarService
        this.photoService = photoService
    }

    @Override
    protected void handle(GroovyContext context) {
        context.with {
            byMethod {
                Long cellarId = (Long) request.get(SessionStorage).get(SecurityModule.SESSION_CELLAR_ID)
                UserProfile profile = request.get(UserProfile)
                get {
                    rx.Observable.zip(
                            cellarService.find(cellarId),
                            photoService.findByCellarId(cellarId)
                    ) { Cellar cellar, Photo photo ->
                        [
                                cellar: cellar,
                                photo: photo
                        ]
                    }.subscribe { Map map ->
                        render handlebarsTemplate('settings.html',
                                title: 'Account Settings',
                                isOauthAccount: profile instanceof TwitterProfile,
                                pageId: 'settings',
                                cellar: map.cellar,
                                photo: map.photo)
                    }
                }

                post {
                    Form form = parse(Form)
                    Validator validator = validatorFactory.validator

                    blocking {
                        Map result = [
                                success: false,
                                cellar: (Cellar) null
                        ]

                        Cellar cellar = cellarService.getBlocking(cellarId)
                        if (cellar) {
                            cellar.with {
                                displayName = form.displayName
                                location = form.location
                                website = form.website
                                bio = form.bio
                                contactEmail = form.contactEmail
                                updateFromNetwork = form.updateFromNetwork
                                setPrivate((Boolean) form.private)
                                reddit = form.reddit
                                twitter = form.twitter
                                beeradvocate = form.beeradvocate
                                ratebeer = form.ratebeer
                            }

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
                                cellarService.saveBlocking(cellar, form.file('photo'))
                                result.success = true
                            }
                        }
                        result
                    } onError { Throwable t ->
                        log.error(LogUtil.toLog('SaveSettingsFailure', [
                                exception: t
                        ]), t)
                        SessionUtil.setFlash(request, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
                        redirect('/settings')
                    } then { Map result ->
                        if (result.success) {
                            SessionUtil.setFlash(request, FlashMessage.success(Messages.SETTINGS_SAVED))
                            redirect('/settings')
                        } else {
                            render handlebarsTemplate('settings.html',
                                    title: 'Account Settings',
                                    pageId: 'settings',
                                    isOauthAccount: profile instanceof TwitterProfile,
                                    cellar: result.cellar
                            )
                        }
                    }
                }
            }
        }
    }
}
