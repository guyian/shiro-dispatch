package com.hengxc.shiro.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author chenguangxu
 * @since 2019-07-25
 */
@Data
@TableName("t_base_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("USER_ID")
    private Long userId;

    /**
     * 角色ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("ROLE_ID")
    private Long roleId;


}
