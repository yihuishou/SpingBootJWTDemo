package com.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by LadyLady on 2018-09-25.
 */
public class ShiroJwtException extends AuthenticationException {

    public ShiroJwtException() {

        super();
    }

    public ShiroJwtException(String message) {

        super(message);
    }

    public ShiroJwtException(Throwable cause) {

        super(cause);
    }

    public ShiroJwtException(String message, Throwable cause) {

        super(message, cause);
    }

}
