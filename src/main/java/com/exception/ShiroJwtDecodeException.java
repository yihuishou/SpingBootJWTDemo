package com.exception;

/**
 * Created by LadyLady on 2018-09-25.
 */
public class ShiroJwtDecodeException extends ShiroJwtException {

    public ShiroJwtDecodeException() {

        super();
    }

    public ShiroJwtDecodeException(String message) {

        super(message);
    }

    public ShiroJwtDecodeException(Throwable cause) {

        super(cause);
    }

    public ShiroJwtDecodeException(String message, Throwable cause) {

        super(message, cause);
    }

}
