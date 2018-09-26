package com.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.exception.JwtAuthorizedException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static Long accessTokenExpireTime;

    private static String secret;

    @Value("${shiro.jwt.secret}")
    public void setSecret(String secret) {

        JwtUtil.secret = secret;
    }

    @Value("${shiro.jwt.accessTokenExpireTime}")
    public void setAccessTokenExpireTime(Long accessTokenExpireTime) {

        JwtUtil.accessTokenExpireTime = accessTokenExpireTime;
    }

    public static void verify(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWTVerifier verifier = JWT.require(algorithm).build();

            verifier.verify(token);
        } catch (JWTVerificationException e) {

            throw new JwtAuthorizedException(e.getMessage());
        }

    }

    public static String sign(String uuid, String username, Date createDate) {

        try {
            Long sysTime = System.currentTimeMillis();

            Date expireDate = new Date(sysTime + accessTokenExpireTime * 1000);

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create().withClaim("uuid", uuid).withSubject(username).withIssuedAt(createDate).withExpiresAt(expireDate).sign(algorithm);
        } catch (JWTVerificationException e) {
            throw new JwtAuthorizedException(e.getMessage());
        }

    }

    public static String getCreateTime(String token) {

        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            return decodedJWT.getIssuedAt().toString();
        } catch (JWTVerificationException e) {
            throw new JwtAuthorizedException(e.getMessage());
        }

    }

    public static String getUsername(String token) {

        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            throw new JwtAuthorizedException(e.getMessage());
        }

    }

    public static String getUuID(String token) {

        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            return decodedJWT.getClaim("uuid").asString();

        } catch (JWTVerificationException e) {
            throw new JwtAuthorizedException(e.getMessage());
        }

    }

    public static String randomUuID() {

        return UUID.randomUUID().toString().replace("-", "");
    }
}
