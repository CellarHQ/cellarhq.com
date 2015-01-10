package com.cellarhq.endpoints.api

import com.cellarhq.domain.BreweryDbSync
import com.cellarhq.services.BreweryDbSyncService
import com.cellarhq.util.LogUtil
import groovy.util.logging.Slf4j
import ratpack.form.Form
import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain
import ratpack.parse.ParserException

import java.security.MessageDigest

@Slf4j
class BreweryDbEndpoint implements Action<Chain> {

    private final String breweryDbApiKey
    private final BreweryDbSyncService breweryDbSyncService

    BreweryDbEndpoint(String breweryDbApiKey, BreweryDbSyncService breweryDbSyncService) {
        this.breweryDbApiKey = breweryDbApiKey
        this.breweryDbSyncService = breweryDbSyncService
    }

    @Override
    void execute(Chain chain) throws Exception {
        Groovy.chain(chain) {
            post('sync/brewerydb') {
                if (!validRequest(request.queryParams.nonce, request.queryParams.key)) {
                    log.info(LogUtil.toLog(request, 'Invalid BreweryDB request'))
                    clientError 401
                }

                Form hookRequest = null
                try {
                    hookRequest = parse(Form)
                } catch (ParserException e) {
                    log.error(LogUtil.toLog(request, 'Could not parse BreweryDB web hook request', [
                            body: request.body
                    ]))
                    error e
                }

                if (hookRequest) {
                    BreweryDbSync sync = BreweryDbSync.makeFrom(hookRequest)
                    breweryDbSyncService.queueSyncRequest(sync).subscribe { Boolean success ->
                        if (success) {
                            log.info(LogUtil.toLog(request, 'BreweryDB web hook request accepted'))
                            response.status(202)
                        } else {
                            log.warn(LogUtil.toLog(request, 'BreweryDB web hook request was not accepted', [
                                    body: request.body
                            ]))
                            response.status(507)
                        }
                    }
                } else {
                    log.warn(LogUtil.toLog(request, 'Empty BreweryDB web hook request: This should never happen', [
                            body: request.body
                    ]))
                    response.status(501)
                }
            }
        }
    }

    boolean validRequest(String nonce, String key) {
        MessageDigest digest = MessageDigest.getInstance('SHA1')
        digest.update("${breweryDbApiKey}${nonce}".bytes)
        return digest.digest().toString() == key
    }
}
