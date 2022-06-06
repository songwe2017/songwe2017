package com.admin.service.impl;

import com.admin.model.User;
import com.admin.mapper.UserMapper;
import com.admin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author songwe
 * @since 2022-06-03
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

}
