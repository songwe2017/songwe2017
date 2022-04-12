package com.auguigu.demo.service;

import com.auguigu.demo.aop.annotation.AOPDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Songwe
 * @date 2022/3/28 15:57
 */
@Component
public class A {
    @Autowired
    private B b;
    
    @AOPDemo
    public void testAop(String caller) {
        System.out.println(caller + "... testAop");
    }
}
