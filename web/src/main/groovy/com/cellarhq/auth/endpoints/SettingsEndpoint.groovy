package com.cellarhq.auth.endpoints

import com.cellarhq.api.services.CellarService
import com.cellarhq.auth.profiles.CellarHQProfile
import com.cellarhq.auth.services.photo.PhotoService
import com.cellarhq.common.Messages
import com.cellarhq.common.session.FlashMessage
import com.cellarhq.domain.Cellar
import com.cellarhq.domain.Photo
import com.cellarhq.util.LogUtil
import com.cellarhq.util.SessionUtil
import com.google.inject.Inject
import groovy.util.logging.Slf4j
import org.pac4j.oauth.profile.twitter.TwitterProfile
import ratpack.form.Form
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

import javax.validation.ConstraintViolation
import javax.validation.Validator
import javax.validation.ValidatorFactory

import static ratpack.handlebars.Template.handlebarsTemplate

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
        get {
          CellarHQProfile profile = context.get(CellarHQProfile)
          rx.Observable.zip(
            cellarService.get(profile.cellarId),
            photoService.findByCellarId(profile.cellarId)
          ) { Cellar cellar, Photo photo ->
            [
              cellar: cellar,
              photo : photo
            ]
          }.subscribe { Map map ->
            render handlebarsTemplate('settings.html',
              [title : 'Account Settings',
               pageId: 'settings',
               cellar: map.cellar,
               photo : map.photo])
          }
        }

        post {
          CellarHQProfile profile = context.get(CellarHQProfile)
          parse(Form).then { Form form ->
            Validator validator = validatorFactory.validator

            Blocking.get {
              Map result = [
                success: false,
                cellar : (Cellar) null
              ]

              Cellar cellar = cellarService.getBlocking(profile.cellarId)
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
                    context,
                    FlashMessage.error(Messages.FORM_VALIDATION_ERROR, cellarViolations.collect {
                      "${it.propertyPath.toString()} ${it.message}"
                    })
                  )
                } else {
                  cellarService.saveBlocking(cellar, form.file('photo'))
                  result.success = true
                }
                result.cellar = cellar
              }
              result
            }.onError { Throwable t ->
              log.error(LogUtil.toLog(request, 'SaveSettingsFailure', [
                exception: t
              ]), t)
              SessionUtil.setFlash(context, FlashMessage.error(Messages.UNEXPECTED_SERVER_ERROR))
              redirect('/settings')
            }.then { Map result ->
              if (result.success) {
                SessionUtil.setFlash(context, FlashMessage.success(Messages.SETTINGS_SAVED))
                redirect('/settings')
              } else {
                render handlebarsTemplate('settings.html',
                  [title         : 'Account Settings',
                   pageId        : 'settings',
                   isOauthAccount: profile instanceof TwitterProfile,
                   cellar        : result.cellar]
                )
              }
            }
          }
        }
      }
    }
  }
}
