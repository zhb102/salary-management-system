package com.example.wagemanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wagemanagement.model.domain.Departments;
import com.example.wagemanagement.service.DepartmentsService;
import com.example.wagemanagement.mapper.DepartmentsMapper;
import org.springframework.stereotype.Service;

/**
* @author zhb
* @description 针对表【departments】的数据库操作Service实现
* @createDate 2024-06-12 12:58:07
*/
@Service
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments>
    implements DepartmentsService{

}




