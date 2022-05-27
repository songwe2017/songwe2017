package com.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author songwe
 * @since 2022-05-20
 */
@Getter
@Setter
@ToString
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父权限
     */
    private String parentLabel;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限路径
     */
    private String path;

    /**
     * 权限标识
     */
    private String label;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否叶子节点
     */
    private Integer isLeaf;

    /**
     * 是否根节点
     */
    private Integer isRoot;

    /**
     * 权限类型
     */
    private String type;

    /**
     * 系统标识
     */
    private String system;

    /**
     * 描述
     */
    private String description;

    private Integer disabled;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;


}
