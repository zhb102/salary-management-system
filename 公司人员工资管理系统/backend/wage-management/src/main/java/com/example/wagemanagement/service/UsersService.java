package com.example.wagemanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wagemanagement.model.domain.Users;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author zhb
 * @description 针对表【users】的数据库操作Service
 * @createDate 2024-06-05 13:59:39
 */
public interface UsersService extends IService<Users> {

    /**
     * @param userAccount   用户账号
     * @param userPassword  用户密码
     * @param checkPassword 检验密码
     * @return 新用户id
     */
    int userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */
    Users userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    Users getSafetyUser(Users originUser);
}
