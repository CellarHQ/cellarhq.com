package com.cellarhq.auth.rememberme


class InvalidCookieException extends RuntimeException {
    InvalidCookieException(String s) {
        super(s)
    }
}
