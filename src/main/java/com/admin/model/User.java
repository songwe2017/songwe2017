package com.admin.model;

import com.admin.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author songwe
 * @since 2022-06-03
 */
@Getter
@Setter
@ApiModel(value = "User对象", description = "用户表")
public class User extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("登录名称")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("性别：0-男、1-女")
    private Integer sex;

    @ApiModelProperty("密码的盐")
    private String salt;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String phone;

}
