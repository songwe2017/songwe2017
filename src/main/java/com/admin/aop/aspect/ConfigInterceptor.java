package com.admin.aop.aspect;

import com.admin.aop.annotation.AOPDemo;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;

/**
 * 这里是通过非 spring配置文件方式注入 MethodInterceptor的用法。
 * 
 * @author Songwe
 * @date 2022/4/12 20:48
 */
//@Configuration
public class ConfigInterceptor {
    private static final AnnotationMatchingPointcut POINTCUT = AnnotationMatchingPointcut.forMethodAnnotation(AOPDemo.class);
    
    @Bean
    public Advisor defaultPointcutAdvisor(final MethodInterceptor demoMethodInterceptor) {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(POINTCUT);
        advisor.setAdvice(demoMethodInterceptor);
        return advisor;
    }
}
