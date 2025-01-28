package com.example.wagemanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wagemanagement.model.domain.Employees;
import com.example.wagemanagement.service.EmployeesService;
import com.example.wagemanagement.mapper.EmployeesMapper;
import org.springframework.stereotype.Service;

/**
* @author zhb
* @description 针对表【employees】的数据库操作Service实现
* @createDate 2024-06-12 12:58:17
*/
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees>
    implements EmployeesService{

}




