package com.shiro;

import com.exception.CustomUnauthorizedException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by LadyLady on 2018-09-27.
 */
@Component
public class ShiroAESUtil {

    private static final String KEY_ALGORITHM = "AES";

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

    private static final String DEFAULT_CHARSET = "UTF-8";//默认的加密算法

    private static String passwordSecret;

    @Value("${shiro.passwordsecret:passwordSecret}")
    public static void setPasswordSecret(String passwordSecret) {

        ShiroAESUtil.passwordSecret = passwordSecret;
    }

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content) {

        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes(DEFAULT_CHARSET);

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(passwordSecret));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64.encodeBase64String(result);//通过Base64转码返回
        } catch (NoSuchAlgorithmException e) {
            throw new CustomUnauthorizedException("getInstance()方法异常:" + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new CustomUnauthorizedException("初始化Cipher对象异常:" + e.getMessage());
        } catch (NoSuchPaddingException e) {
            throw new CustomUnauthorizedException("getInstance()方法异常:" + e.getMessage());
        } catch (BadPaddingException e) {
            throw new CustomUnauthorizedException("加密异常，密钥有误:" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new CustomUnauthorizedException("Base64加密异常:" + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new CustomUnauthorizedException("加密异常，密钥有误:" + e.getMessage());
        }

    }

    /**
     * AES 解密操作
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(passwordSecret));

            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, DEFAULT_CHARSET);
        } catch (NoSuchAlgorithmException e) {
            throw new CustomUnauthorizedException("getInstance()方法异常:" + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new CustomUnauthorizedException("初始化Cipher对象异常:" + e.getMessage());
        } catch (NoSuchPaddingException e) {
            throw new CustomUnauthorizedException("getInstance()方法异常:" + e.getMessage());
        } catch (BadPaddingException e) {
            throw new CustomUnauthorizedException("加密异常，密钥有误:" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new CustomUnauthorizedException("Base64加密异常:" + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new CustomUnauthorizedException("加密异常，密钥有误:" + e.getMessage());
        }

    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(String passwordSecret) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象

        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);

        //AES 要求密钥长度为 128
        keyGenerator.init(128, new SecureRandom(passwordSecret.getBytes(DEFAULT_CHARSET)));

        //生成一个密钥
        SecretKey secretKey = keyGenerator.generateKey();

        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥

    }
}