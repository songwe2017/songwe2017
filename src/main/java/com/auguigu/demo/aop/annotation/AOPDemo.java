package com.auguigu.demo.aop.annotation;

import java.lang.annotation.*;

/**
 * @author Songwe
 * @date 2021/7/15 16:49
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AOPDemo {
    String value() default "";
}
