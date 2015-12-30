package com.cellarhq.common.services.email

import groovy.transform.CompileStatic

@CompileStatic
public interface EmailService {

  final static String DEFAULT_FROM = 'team@cellarhq.com'

  void sendEmail(String to, String subject, String body)

  void sendEmail(String from, String to, String subject, String body)
}
