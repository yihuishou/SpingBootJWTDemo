package com.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by LadyLady on 2018-09-17.
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    private Object object;

    JwtToken(String token, Object object) {

        this.object = object;
        this.token = token;
    }

    JwtToken(String token) {

        this.token = token;
    }

    @Override
    public Object getPrincipal() {

        return this.object;
    }

    @Override
    public Object getCredentials() {

        return this.token;
    }

}
