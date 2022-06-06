package com.admin.model;

import com.admin.common.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author songwe
 * @since 2022-06-03
 */
@Getter
@Setter
@TableName("role_perm_rel")
@ApiModel(value = "RolePermRel对象", description = "用户角色关联表")
public class RolePermRel extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("权限ID")
    private Long permId;


}
