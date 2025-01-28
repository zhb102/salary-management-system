package com.example.wagemanagement.service;

import com.example.wagemanagement.model.domain.Users;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersServiceTest {

    @Resource
    private UsersService usersService;

    @Test
    void testAddUser() {
        Users users = new Users();
        users.setUsername("张三");
        users.setPassword("123456");
        boolean result = usersService.save(users);
    }

    @Test
    void userRegister() {
        String userAccount = "test";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        int i = usersService.userRegister(userAccount, userPassword, checkPassword);
    }

    @Test
    void userLogin() {
        String userAccount = "test";
        String userPassword = "12345678";
        Users users = usersService.userLogin(userAccount, userPassword, null);
        System.out.println(users.getUserid());
    }
}