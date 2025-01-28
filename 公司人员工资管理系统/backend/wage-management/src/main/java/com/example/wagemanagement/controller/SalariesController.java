package com.example.wagemanagement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.wagemanagement.model.domain.Employees;
import com.example.wagemanagement.model.domain.Salaries;
import com.example.wagemanagement.service.EmployeesService;
import com.example.wagemanagement.service.SalariesService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工资接口
 */
@RestController
@RequestMapping("/salaries")
@Slf4j
public class SalariesController {

    @Resource
    private SalariesService salariesService;

    @PostMapping("/search")
    public List<Salaries> searchEmployees(@RequestBody Salaries salary) {
        QueryWrapper<Salaries> queryWrapper = new QueryWrapper<>();

        if( salary.getSalaryId() != null )
            queryWrapper.eq("salaryId", salary.getSalaryId());
        if( salary.getEmployeeId() != null )
            queryWrapper.eq("employeeId", salary.getEmployeeId());
        if (salary.getYear() != null )
            queryWrapper.eq("year", salary.getYear());
        if (salary.getMonth() != null )
            queryWrapper.eq("month", salary.getMonth());
        if (salary.getNetSalary() != null)
            queryWrapper.eq("netSalary", salary.getNetSalary());

        List<Salaries> salaryList = salariesService.list(queryWrapper);
        return salaryList;
    }

    @PostMapping("/add")
    public boolean addSalary(@RequestBody Salaries salary) {
        if (salary == null) {
            return false;
        }
        QueryWrapper<Salaries> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employeeId", salary.getEmployeeId());
        try {
            int id = salariesService.getOne(queryWrapper).getSalaryId();
            salariesService.removeById(id);
        }catch (Exception e){
        }
        return salariesService.save(salary);
    }

    @PostMapping("/delete")
    public boolean deleteSalary(@RequestBody Salaries salary) {
        if (salary == null) {
            return false;
        }
        QueryWrapper<Salaries> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("salaryId", salary.getSalaryId());
        try {
            int id = salariesService.getOne(queryWrapper).getSalaryId();
            salariesService.removeById(id);
        }catch (Exception e){
        }
        return true;
    }

}
