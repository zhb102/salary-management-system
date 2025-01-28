package com.example.wagemanagement.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName departments
 */
@TableName(value ="departments")
@Data
public class Departments implements Serializable {
    /**
     * 部门ID
     */
    @TableId(type = IdType.AUTO)
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 逻辑删除标志
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}