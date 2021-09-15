package com.auguigu.demo.mapper;

import com.auguigu.demo.model.User;

/**
 * @author Songwe
 * @date 2021/7/20 16:24
 */
public interface UserMapper {
    User find(Long id);
}
