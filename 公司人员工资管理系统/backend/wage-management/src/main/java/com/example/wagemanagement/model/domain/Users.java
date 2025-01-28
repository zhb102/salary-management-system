package com.example.wagemanagement.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Integer userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码哈希
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createdat;

    /**
     * 更新时间
     */
    private Date updatedat;

    /**
     * 逻辑删除标志
     */
    @TableLogic
    private Integer isdeleted;

    /**
     * 用户角色 0-普通用户 1-用户管理员
     */
    private Integer userrole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}