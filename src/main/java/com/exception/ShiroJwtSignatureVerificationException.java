package com.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by LadyLady on 2018-09-25.
 */
public class ShiroJwtSignatureVerificationException extends AuthenticationException {

    public ShiroJwtSignatureVerificationException() {

        super();
    }

    public ShiroJwtSignatureVerificationException(String message) {

        super(message);
    }

    public ShiroJwtSignatureVerificationException(Throwable cause) {

        super(cause);
    }

    public ShiroJwtSignatureVerificationException(String message, Throwable cause) {

        super(message, cause);
    }

}
