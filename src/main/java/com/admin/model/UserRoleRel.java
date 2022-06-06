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
@TableName("user_role_rel")
@ApiModel(value = "UserRoleRel对象", description = "用户角色关联表")
public class UserRoleRel extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("角色ID")
    private Long roleId;


}
