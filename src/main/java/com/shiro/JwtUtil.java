package com.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.exception.ShiroJwtException;
import com.exception.ShiroJwtSignatureVerificationException;
import com.exception.ShiroJwtTokenExpiredException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static Long accessTokenExpireTime;

    private static String secret;

    @Value("${shiro.jwt.secret:secret}")
    public void setSecret(String secret) {

        JwtUtil.secret = secret;
    }

    @Value("${shiro.jwt.accesstokenexpireTime:300}")
    public void setAccessTokenExpireTime(Long accessTokenExpireTime) {

        JwtUtil.accessTokenExpireTime = accessTokenExpireTime;
    }

    public static void verify(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            JWTVerifier verifier = JWT.require(algorithm).build();

            verifier.verify(token);
        } catch (SignatureVerificationException e) {
            throw new ShiroJwtSignatureVerificationException(e.getMessage());
        } catch (TokenExpiredException e) {
            throw new ShiroJwtTokenExpiredException(e.getMessage());
        } catch (JWTVerificationException e) {
            throw new ShiroJwtException(e.getMessage());
        }

    }

    public static String sign(String uuid, String username, Date createDate) {

        try {

            Date expireDate = new Date(createDate.getTime() + accessTokenExpireTime * 1000);

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create().withClaim("uuid", uuid).withSubject(username).withIssuedAt(createDate).withExpiresAt(expireDate).sign(algorithm);

        } catch (JWTVerificationException e) {
            throw new ShiroJwtException(e.getMessage());
        }

    }

    public static String getCreateTime(String token) {

        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            return decodedJWT.getIssuedAt().toString();
        } catch (JWTVerificationException e) {
            throw new ShiroJwtException(e.getMessage());
        }

    }

    public static String getUsername(String token) {

        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            throw new ShiroJwtException(e.getMessage());
        }

    }

    public static String getUuID(String token) {

        try {
            DecodedJWT decodedJWT = JWT.decode(token);

            return decodedJWT.getClaim("uuid").asString();

        } catch (JWTVerificationException e) {
            throw new ShiroJwtException(e.getMessage());
        }

    }

    public static String randomUuID() {

        return UUID.randomUUID().toString().replace("-", "");
    }
}
