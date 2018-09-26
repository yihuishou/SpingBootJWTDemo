package com.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by LadyLady on 2018-09-25.
 */
public class JwtAuthorizedException extends AuthenticationException {

    public JwtAuthorizedException() {

    }

    public JwtAuthorizedException(String message) {

        super(message);
    }

    public JwtAuthorizedException(Throwable cause) {

        super(cause);
    }

    public JwtAuthorizedException(String message, Throwable cause) {

        super(message, cause);
    }

}
