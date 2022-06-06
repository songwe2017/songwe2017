package com.admin.model;

import com.admin.common.base.BaseModel;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author songwe
 * @since 2022-06-03
 */
@Getter
@Setter
@ApiModel(value = "Permission对象", description = "权限表")
public class Permission extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("父权限")
    private String parentLabel;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("权限路径")
    private String path;

    @ApiModelProperty("权限标识")
    private String label;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("是否叶子节点")
    private Integer isLeaf;

    @ApiModelProperty("是否根节点")
    private Integer isRoot;

    @ApiModelProperty("权限类型")
    private String type;

    @ApiModelProperty("系统标识")
    private String system;

    @ApiModelProperty("描述")
    private String description;


}
