package com.cellarhq.common.services.email

import com.cellarhq.util.LogUtil
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class LogEmailService implements EmailService {

    @Override
    void sendEmail(String to, String subject, String body) {
        sendEmail(DEFAULT_FROM, to, subject, body)
    }

    @Override
    void sendEmail(String from, String to, String subject, String body) {
        log.info(LogUtil.toLog('Sent email', [
                from: from,
                subject: subject,
                to: to,
                body: body
        ]))
    }
}
