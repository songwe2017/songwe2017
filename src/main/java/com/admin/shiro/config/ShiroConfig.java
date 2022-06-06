package com.admin.shiro.config;

import com.admin.shiro.UserRealm;
import com.admin.shiro.cache.RedisCacheManager;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Songwe
 * @date 2022/5/21 1:09
 */
@Configuration
public class ShiroConfig {
        
    // 创建 cookie 
    @Bean
    public SimpleCookie cookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        // 每次浏览器访问都会在 cookie 里把这个值作为 key 传过来
        simpleCookie.setName("ShiroSession");
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

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
    public DefaultWebSessionManager sessionManager(SimpleCookie cookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 开启 cookie
        sessionManager.setSessionIdCookieEnabled(true);
        // 关闭会话更新
        sessionManager.setSessionValidationSchedulerEnabled(false);
        // cookie 生成策略
        sessionManager.setSessionIdCookie(cookie);
        // 全局会话超时时间
        sessionManager.setGlobalSessionTimeout(360000);
        
        return sessionManager;
    } 
    
    @Bean
    public DefaultWebSecurityManager securityManager(UserRealm realm,
                                                     CacheManager cacheManager,
                                                     DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(cacheManager);
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
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/assets/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/admin/login", "anon");
        filterMap.put("/admin/logout", "logout");
        filterMap.put("/login", "anon");
        filterMap.put("/**", "authc");
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login"); // 设置未登录时访问未授权站点调转
        shiroFilterFactoryBean.setUnauthorizedUrl("/"); // 设置登录后未授权站点跳转
        return shiroFilterFactoryBean;
    }
    
}
