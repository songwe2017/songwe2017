package com.auguigu.demo.service;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Songwe
 * @date 2021/1/11 20:18
 */
public class CustomerAction {

    public static void main(String[] args) {
        //生成短信验证码
        String randomCode = RandomStringUtils.randomNumeric(4);

        System.out.println(randomCode);


    }
}
