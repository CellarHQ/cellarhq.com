package com.cellarhq.support

import com.amazonaws.auth.BasicAWSCredentials


trait AmazonSupport {
  private getBasicCredentials() {
    new BasicAWSCredentials(System.getenv('AWS_ACCESS_KEY'), System.getenv('AWS_SECRET_ACCESS_KEY'))
  }

}
