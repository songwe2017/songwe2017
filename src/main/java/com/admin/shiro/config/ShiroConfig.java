package com.admin.shiro.config;

import com.admin.shiro.realm.DefaultRealm;
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
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("ShiroSession");
        return simpleCookie;
    }
    
    // 创建权限管理器
    @Bean
    public DefaultWebSecurityManager securityManager(DefaultRealm realm,
                                                     DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        return defaultWebSecurityManager;
    }
    
    // 自定义 relam
    @Bean
    public DefaultRealm defaultRelam() {
        return new DefaultRealm();
    }
    
    // 会话管理器
    @Bean
    public DefaultWebSessionManager dsessionManager(SimpleCookie cookie) {
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
    
    // 创建声明周期管理器
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    
    // AOP 方式方法级权限检查，使用注解鉴权方式
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisor = new DefaultAdvisorAutoProxyCreator();
        advisor.setProxyTargetClass(true);
        return advisor;
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
        filterMap.put("/statics/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/login.html", "anon");
        filterMap.put("/**", "authc");
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login.html"); // 设置未登录时访问未授权站点调转
        shiroFilterFactoryBean.setUnauthorizedUrl("/"); // 设置登录后未授权站点跳转
        return shiroFilterFactoryBean;
    }
    
}
