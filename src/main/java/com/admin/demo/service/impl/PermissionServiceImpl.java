package com.admin.demo.service.impl;

import com.admin.demo.entity.Permission;
import com.admin.demo.mapper.PermissionMapper;
import com.admin.demo.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author songwe
 * @since 2022-05-20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
