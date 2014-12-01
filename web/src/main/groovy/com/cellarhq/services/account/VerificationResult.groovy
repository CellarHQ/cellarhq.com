package com.cellarhq.services.account

import groovy.transform.CompileStatic

@CompileStatic
class VerificationResult {
    boolean success = false
    String message

    @SuppressWarnings('ConfusingMethodName')
    static VerificationResult success() {
        return new VerificationResult(success: true)
    }

    static VerificationResult failure(String reason) {
        return new VerificationResult(message: reason)
    }
}
