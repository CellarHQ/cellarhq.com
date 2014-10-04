package com.cellarhq.auth

import groovy.transform.CompileStatic
import org.mindrot.jbcrypt.BCrypt

/**
 * Separated this out into a separate service so it's faster to run tests.
 */
@CompileStatic
class PasswordService {

    private final static int BCRYPT_LOG_ROUNDS = 16
    private final static String UNCLAIMED_MARKER = 'unclaimed'

    String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(BCRYPT_LOG_ROUNDS))
    }

    boolean checkPassword(String plaintext, String hashed) {
        if (hashed.trim() == UNCLAIMED_MARKER) {
            throw new UnclaimedAccountException()
        }

        return BCrypt.checkpw(plaintext, hashed)
    }
}
