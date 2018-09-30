package com.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Created by LadyLady on 2018-09-29.
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.defaultexpiration:600}")
    private Long defaultExpiration;

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // FastJson提供的通用序列化
        // GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        // Jackson提供的通用序列化
        // GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // Jackson 使用 defaultTyping 实现通用的序列化和反序列化
        // 通过 objectMapper.enableDefaultTyping() 方法设置
        // 即使使用 Object.class 作为 jcom.fasterxml.jackson.databind.JavaType 也可以实现相应类型的序列化和反序列化
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringSimpleValueRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {

        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(defaultExpiration);
        redisCacheManager.setUsePrefix(true);
        return redisCacheManager;
    }
}
