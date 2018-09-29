package com.redis;

import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created by LadyLady on 2018-09-29.
 */
public class StringSimpleValueRedisSerializer implements RedisSerializer<Object> {

    private final Charset charset;

    public StringSimpleValueRedisSerializer() {

        this(Charset.forName("UTF8"));
    }

    public StringSimpleValueRedisSerializer(Charset charset) {

        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object param) throws SerializationException {

        String string = null;
        if (param instanceof Integer) {
            string = param.toString();
        } else if (param instanceof String) {
            string = param.toString();
        } else if (param instanceof Double) {
            string = param.toString();
        } else if (param instanceof Float) {
            string = param.toString();
        } else if (param instanceof Long) {
            string = param.toString();
        } else if (param instanceof Boolean) {
            string = param.toString();
        } else if (param instanceof Date) {
            string = param.toString();
        } else if (param instanceof SimpleKey) {
            string = param.toString();
        }

        if (string == null) {
            throw new SerializationException("Param key must not be an Object");
        }

        return string.getBytes(this.charset);

    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {

        return bytes == null ? null : new String(bytes, this.charset);
    }
}
