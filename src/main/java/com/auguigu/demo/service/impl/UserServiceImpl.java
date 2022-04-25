package com.auguigu.demo.service.impl;

import com.auguigu.demo.mapper.UserMapper;
import com.auguigu.demo.model.User;
import com.auguigu.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author Songwe
 * @date 2021/7/20 16:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ApplicationArguments applicationArguments;

    @Override
    public User getUser(Long id) {
        try {
            String s = new ObjectMapper().writeValueAsString(applicationArguments);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userMapper.find(id);
    }
}
