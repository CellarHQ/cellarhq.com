package com.cellarhq.auth

import com.cellarhq.util.LogUtil
import groovy.transform.CompileStatic
import org.mindrot.jbcrypt.BCrypt

/**
 * Separated this out into a separate service so it's faster to run tests.
 */
@CompileStatic
class PasswordService {

    private final static int BCRYPT_LOG_ROUNDS = 6
    private final static String UNCLAIMED_MARKER = 'unclaimed'

    String hashPassword(String password) {
        return LogUtil.withPerformance('BCrypt', LogUtil.Level.INFO) {
            BCrypt.hashpw(password, BCrypt.gensalt(BCRYPT_LOG_ROUNDS))
        }
    }

    boolean checkPassword(String plaintext, String hashed) {
        if (hashed.trim() == UNCLAIMED_MARKER) {
            throw new UnclaimedAccountException()
        }

        return LogUtil.withPerformance('BCrypt', LogUtil.Level.INFO) {
            BCrypt.checkpw(plaintext, hashed)
        }
    }

    boolean shouldRehashPassword(String hash) {
        return getHashStrength(hash) != BCRYPT_LOG_ROUNDS
    }

    @SuppressWarnings('EmptyCatchBlock')
    private int getHashStrength(String hash) {
        String[] pieces = hash?.split(/\$/)
        if (pieces && pieces.length == 4) {
            try {
                return Integer.valueOf(pieces[2])
            } catch (NumberFormatException e) {
                // Do nothing.
            }
        }

        return -1
    }
}
