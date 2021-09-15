package com.auguigu.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Songwe
 * @date 2021/2/1 15:43
 */
//@RestController
public class LoginController {

    @GetMapping("/hello")
    public String login(HttpServletRequest request) {
        return "hello";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String get(){
        return "GET请求";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String post(){
        return "POST请求";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String put(){
        return "PUT请求";
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public String delete(){
        return "DELETE请求";
    }
}
