package com.admin.aop.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Songwe
 * @date 2022/4/12 19:59
 */
@Component
public class DemoMethodInterceptor implements MethodInterceptor {
    
    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation i) throws Throwable {
        
        System.out.println("method "+i.getMethod()+" is called on "+ i.getThis()+" with args "+i.getArguments());
        Object result = i.proceed();
        System.out.println("method "+i.getMethod()+" returns "+ result);
        return result;
    }
}
