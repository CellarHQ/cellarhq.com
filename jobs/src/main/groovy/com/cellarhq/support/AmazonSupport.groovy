package com.cellarhq.support

import com.amazonaws.auth.BasicAWSCredentials


trait AmazonSupport {
    private final String accessKeyId = 'AKIAIXBP2ORLESIX5CIQ'
    private final String secretKey = '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'

    private getBasicCredentials() {
        new BasicAWSCredentials(accessKeyId, secretKey)
    }

}
