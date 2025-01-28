package com.example.wagemanagement.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName salaries
 */
@TableName(value ="salaries")
@Data
public class Salaries implements Serializable {
    /**
     * 工资ID
     */
    @TableId(type = IdType.AUTO)
    private Integer salaryId;

    /**
     * 员工ID
     */
    private Integer employeeId;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 本月应出勤天数
     */
    private Integer workDays;

    /**
     * 实际出勤天数
     */
    private Integer actualWorkDays;

    /**
     * 基本工资
     */
    private BigDecimal basicSalary;

    /**
     * 岗位津贴
     */
    private BigDecimal positionAllowance;

    /**
     * 午餐补贴
     */
    private BigDecimal lunchAllowance;

    /**
     * 加班工资
     */
    private BigDecimal overtimeSalary;

    /**
     * 全勤工资
     */
    private BigDecimal fullAttendanceBonus;

    /**
     * 扣社保
     */
    private BigDecimal socialInsurance;

    /**
     * 扣公积金
     */
    private BigDecimal housingFund;

    /**
     * 扣税
     */
    private BigDecimal tax;

    /**
     * 迟到请假等扣除
     */
    private BigDecimal deductions;

    /**
     * 实发工资
     */
    private BigDecimal netSalary;

    /**
     * 逻辑删除标志
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}