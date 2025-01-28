package com.example.wagemanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.wagemanagement.mapper")
public class WageManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(WageManagementApplication.class, args);
    }

}
