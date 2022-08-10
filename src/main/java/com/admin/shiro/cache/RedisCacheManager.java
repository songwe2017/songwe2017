package com.admin.shiro.cache;


import com.admin.redis.RedissonManager;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Songwe
 * @since 2022/6/4 22:15
 */
public class RedisCacheManager extends AbstractCacheManager {
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Autowired
    private RedissonManager redissonManager;
    
    @Override
    public Cache createCache(String name) throws CacheException {
        return new RedisCache(name, redisTemplate);
    }
}
