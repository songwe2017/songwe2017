package com.admin.demo.service.impl;

import com.admin.demo.entity.Role;
import com.admin.demo.mapper.RoleMapper;
import com.admin.demo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author songwe
 * @since 2022-05-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
