package com.auguigu.demo.service;

import com.auguigu.demo.model.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Songwe
 * @date 2021/7/20 16:25
 */
@Validated
public interface UserService {

    User getUser(@NotNull Long id);
}
