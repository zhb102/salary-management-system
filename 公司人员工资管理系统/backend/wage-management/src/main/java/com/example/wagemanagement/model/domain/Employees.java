package com.example.wagemanagement.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName employees
 */
@TableName(value ="employees")
@Data
public class Employees implements Serializable {
    /**
     * 员工ID
     */
    @TableId(type = IdType.AUTO)
    private Integer employeeId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门ID
     */
    private Integer departmentId;

    /**
     * 逻辑删除标志
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}