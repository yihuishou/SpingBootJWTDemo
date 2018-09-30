package com.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by LadyLady on 2018-09-25.
 */
public class ShiroJwtDecodeException extends AuthenticationException {

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
