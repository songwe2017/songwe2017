package com.admin.model;

import com.admin.common.base.BaseModel;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author songwe
 * @since 2022-06-03
 */
@Getter
@Setter
@ApiModel(value = "Role对象", description = "角色表")
public class Role extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色")
    private String name;

    @ApiModelProperty("角色标识")
    private String label;

    @ApiModelProperty("描述")
    private String description;


}
