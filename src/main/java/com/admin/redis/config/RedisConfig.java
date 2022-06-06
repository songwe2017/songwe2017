package com.admin.redis.config;

import com.admin.redis.RedisLock;
import com.admin.redis.RedissonManager;
import com.admin.redis.property.RedisProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author songwe
 * @date 2019/6/19 下午11:55
 */
@Slf4j
@Configuration
//@ConditionalOnClass(Redisson.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
    
    private final RedisProperties properties;

    @Bean
    public RedisTemplate<String, Object>redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object>template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器,此序列化器可以以json方式序列化和反序列化带泛型参数的list，缺点是占用内存大，效率低
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 此序列化器可以以json方式序列化和反序列化，但是无法处理泛型，所以需要在传入value的时候主动转成 json 格式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public RedissonManager redisManager() {
        RedissonManager redissonManager = new RedissonManager(properties);
        log.info("[RedisManager]装配完成，当前连接方式:" + properties.getType() + ",连接地址:" + properties.getAddress());
        return redissonManager;
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisLock redisLock(RedissonManager redissonManager) {
        RedisLock redisLock = new RedisLock(redissonManager);
        return redisLock;
    }
}

