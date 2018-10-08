package com.exception;

/**
 * Created by LadyLady on 2018-09-25.
 */
public class ShiroJwtSignatureVerificationException extends ShiroJwtException {

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
