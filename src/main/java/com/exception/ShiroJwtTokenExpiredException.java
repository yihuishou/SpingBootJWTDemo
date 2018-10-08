package com.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by LadyLady on 2018-09-25.
 */
public class ShiroJwtTokenExpiredException extends ShiroJwtException {

    public ShiroJwtTokenExpiredException() {

        super();
    }

    public ShiroJwtTokenExpiredException(String message) {

        super(message);
    }

    public ShiroJwtTokenExpiredException(Throwable cause) {

        super(cause);
    }

    public ShiroJwtTokenExpiredException(String message, Throwable cause) {

        super(message, cause);
    }

}
