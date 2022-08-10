package com.admin.shiro.config;

import com.admin.redis.RedissonManager;
import com.admin.shiro.RedisSessionDao;
import com.admin.shiro.ShiroSessionManager;
import com.admin.shiro.UserRealm;
import com.admin.shiro.cache.RedisCacheManager;
import com.admin.shiro.filter.JwtAuthcFilter;
import com.admin.shiro.filter.KickOutAccessControlFilter;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Songwe
 * @since 2022/5/21 1:09
 */
@Configuration
public class ShiroConfig {
    
    @Bean
    public CacheManager cacheManager() {
        return new RedisCacheManager();
    }
    
    @Bean
    public UserRealm defaultRelam() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCachingEnabled(true);
        userRealm.setAuthenticationCachingEnabled(true);
        userRealm.setAuthenticationCacheName("authenticationCache");
        userRealm.setAuthorizationCachingEnabled(true);
        userRealm.setAuthorizationCacheName("authorizationCache");
        return userRealm;
    }
    
    @Bean
    public SessionDAO sessionDao() {
        return new RedisSessionDao();
    }

    @Bean
    public ShiroSessionManager sessionManager() {
        ShiroSessionManager sessionManager = new ShiroSessionManager();
        sessionManager.setSessionIdCookieEnabled(false);
        sessionManager.setSessionDAO(sessionDao());        sessionManager.setCacheManager(cacheManager());
        return sessionManager;
    }
    
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(defaultRelam());
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }
    
    // 创建声明周期管理器
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /*
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }
    
    @Bean
    public ShiroFilterFactoryBean shiroFilter(RedissonManager redissonManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String, Filter> filters = new HashMap<>();
        filters.put("kick_out", new KickOutAccessControlFilter(redissonManager.getRedisson(), sessionDao(), sessionManager()));
        filters.put("jwt_authc", new JwtAuthcFilter());
        
        shiroFilterFactoryBean.setFilters(filters);
        
        Map<String, String> filterMap = new HashMap<>(16);
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/assets/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/admin/login", "anon");
        filterMap.put("/admin/logout", "logout");
        filterMap.put("/login", "anon");
        filterMap.put("/**", "jwt_authc, kick_out, authc");
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login"); // 设置未登录时访问未授权站点调转
        shiroFilterFactoryBean.setUnauthorizedUrl("/"); // 设置登录后未授权站点跳转
        return shiroFilterFactoryBean;
    }
    
}
