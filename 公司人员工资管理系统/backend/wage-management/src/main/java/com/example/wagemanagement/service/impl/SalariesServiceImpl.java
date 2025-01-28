package com.example.wagemanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wagemanagement.model.domain.Salaries;
import com.example.wagemanagement.service.SalariesService;
import com.example.wagemanagement.mapper.SalariesMapper;
import org.springframework.stereotype.Service;

/**
* @author zhb
* @description 针对表【salaries】的数据库操作Service实现
* @createDate 2024-06-12 12:58:21
*/
@Service
public class SalariesServiceImpl extends ServiceImpl<SalariesMapper, Salaries>
    implements SalariesService{

}




