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
 * 部门接口
 */
@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmensController {

    @Resource
    private DepartmentsService departmentsService;

    @Resource
    private EmployeesService employeesService;

    @PostMapping("/search")
    public List<Departments> searchDepartments(@RequestBody Departments department) {
        QueryWrapper<Departments> queryWrapper = new QueryWrapper<>();
        if( department.getDepartmentId() != null )
            queryWrapper.eq("departmentId", department.getDepartmentId());

        if( department.getDepartmentName() != null )
            queryWrapper.eq("departmentName", department.getDepartmentName());

        List<Departments> departmentList = departmentsService.list(queryWrapper);

        return departmentList;
    }

    @PostMapping("/add")
    public boolean addDepartment(@RequestBody Departments department) {
        if (department == null) {
            return false;
        }
        QueryWrapper<Departments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("departmentName", department.getDepartmentName());
        try {
            int id = departmentsService.getOne(queryWrapper).getDepartmentId();
            departmentsService.removeById(id);
        }catch (Exception e){
        }
        return departmentsService.save(department);
    }

    @PostMapping("/delete")
    public boolean deleteDepartment(@RequestBody Departments department) {
        if (department == null) {
            return false;
        }
        QueryWrapper<Departments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("departmentName", department.getDepartmentName());
        int id = departmentsService.getOne(queryWrapper).getDepartmentId();
        QueryWrapper<Employees> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("departmentId", id);
        employeesService.remove(queryWrapper1);
        return departmentsService.removeById(id);
    }

}
