package com.admin.demo.service.impl;

import com.admin.demo.mapper.UserMapper;
import com.admin.demo.model.User;
import com.admin.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

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
