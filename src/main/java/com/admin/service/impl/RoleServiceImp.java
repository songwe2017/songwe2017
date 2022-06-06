package com.admin.service.impl;

import com.admin.model.Role;
import com.admin.mapper.RoleMapper;
import com.admin.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author songwe
 * @since 2022-06-03
 */
@Service
public class RoleServiceImp extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
