package com.auguigu.demo.service;

import com.auguigu.demo.model.User;

import javax.validation.constraints.NotNull;

/**
 * @author Songwe
 * @date 2021/7/20 16:25
 */
public interface UserService {

    public User getUser(@NotNull Long id);
}
