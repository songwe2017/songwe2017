package com.auguigu.demo.aop.aspect;

import com.auguigu.demo.aop.annotation.AOPDemo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Songwe
 * @date 2021/7/15 16:52
 */
@Aspect
@Component
public class DemoAspect {

    @Around("@annotation(aopDemo)")
    public Object execute(ProceedingJoinPoint joinPoint, AOPDemo aopDemo) {
        System.out.println("执行前置增强");
        Object proceed = null;
        try {
            Object[] args = joinPoint.getArgs();
            System.out.println("方法参数..." + args[0]);
            String value = aopDemo.value();
            System.out.println("注解参数" + value);
            proceed = joinPoint.proceed(args);
        }
        catch (Throwable e) {
        }
        System.out.println("执行后置处理");
        return proceed;
    }
}
