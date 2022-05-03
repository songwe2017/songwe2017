package com.auguigu.demo.redisson.config;

import com.auguigu.demo.redisson.RedisLock;
import com.auguigu.demo.redisson.RedissonManager;
import com.auguigu.demo.redisson.property.RedisProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author songwe
 * @date 2019/6/19 下午11:55
 */
@Slf4j
@Configuration
@ConditionalOnClass(Redisson.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfiguration {
    
    private final RedisProperties properties;
    
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

