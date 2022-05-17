package com.admin.demo.service;

import com.admin.demo.model.User;
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
