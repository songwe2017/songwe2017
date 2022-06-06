package com.admin.service.impl;

import com.admin.model.Permission;
import com.admin.mapper.PermissionMapper;
import com.admin.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author songwe
 * @since 2022-06-03
 */
@Service
public class PermissionServiceImp extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
