package com.example.wagemanagement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.wagemanagement.model.domain.Departments;
import com.example.wagemanagement.model.domain.Employees;
import com.example.wagemanagement.service.DepartmentsService;
import com.example.wagemanagement.service.EmployeesService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 人员接口
 */
@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeesController {

    @Resource
    private EmployeesService employeesService;

//    @GetMapping("/search")
//    public List<Employees> searchEmployees(String name, HttpServletRequest request) {
//        QueryWrapper<Employees> queryWrapper = new QueryWrapper<>();
//        if (StringUtils.isNotBlank(name)) {
//            queryWrapper.like("name", name);
//        }
//        List<Employees> employeeList = employeesService.list(queryWrapper);
//        return employeeList;
//    }

    @PostMapping("/search")
    public List<Employees> searchDepartments(@RequestBody Employees employee) {
        QueryWrapper<Employees> queryWrapper = new QueryWrapper<>();
        if( employee.getEmployeeId() != null )
            queryWrapper.eq("employeeId", employee.getEmployeeId());

        if( employee.getName() != null )
            queryWrapper.eq("name", employee.getName());

//        if( employee.get)

        List<Employees> employeestList = employeesService.list(queryWrapper);

        return employeestList;
    }

    @PostMapping("/delete")
    public boolean deleteEmployee(@RequestBody Employees employee) {
        if (employee == null) {
            return false;
        }
        QueryWrapper<Employees> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employeeId", employee.getEmployeeId());
        try {
            int id = employeesService.getOne(queryWrapper).getEmployeeId();
            employeesService.removeById(id);
        }catch (Exception e){
        }
        return true;
    }

    @PostMapping("/add")
    public boolean addEmployee(@RequestBody Employees employee) {
        if (employee == null) {
            return false;
        }
        QueryWrapper<Employees> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", employee.getName());
        try {
            int id = employeesService.getOne(queryWrapper).getEmployeeId();
            employeesService.removeById(id);
        }catch (Exception e){
        }
        return employeesService.save(employee);
    }
}
