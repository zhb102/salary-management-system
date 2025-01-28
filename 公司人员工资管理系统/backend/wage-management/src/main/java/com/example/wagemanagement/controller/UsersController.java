package com.example.wagemanagement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.wagemanagement.model.domain.Users;
import com.example.wagemanagement.model.domain.request.UsersRegisterRequest;
import com.example.wagemanagement.model.domain.request.UsersLoginRequest;
import com.example.wagemanagement.service.UsersService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.wagemanagement.constant.UsersConstant.ADMIN_ROLE;
import static com.example.wagemanagement.constant.UsersConstant.USER_LOGIGN_STATE;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {

    @Resource
    private UsersService usersService;

    @PostMapping("/register")
    public int usersRegister(@RequestBody UsersRegisterRequest usersRegisterRequest) {
        if (usersRegisterRequest == null) {
            return -1;
        }
        String userAccount = usersRegisterRequest.getUserAccount();
        String userPassword = usersRegisterRequest.getUserPassword();
        String checkPassword = usersRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        return usersService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("/login")
    public Users usersLogin(@RequestBody UsersLoginRequest usersLoginRequest, HttpServletRequest request) {
        if (usersLoginRequest == null) {
            return null;
        }
        String userAccount = usersLoginRequest.getUserAccount();
        String userPassword = usersLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return usersService.userLogin(userAccount, userPassword, request);
    }

    @GetMapping("/current")
    public Users getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIGN_STATE);
        Users currentUser = (Users) userObj;
        if (currentUser == null) {
            return null;
        }
        int id = currentUser.getUserid();
        Users user = usersService.getById(id);
        return usersService.getSafetyUser(user);
    }

    @GetMapping("/search")
    public List<Users> searchUsers(String username, HttpServletRequest request) {
        // 仅管理员可查询
        if (!isAdmin(request)){
            return new ArrayList<>();
        }
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<Users> userList = usersService.list(queryWrapper);
        return userList.stream().map(user -> usersService.getSafetyUser(user)).collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody int id, HttpServletRequest request) {
        // 仅管理员可删除
        if (!isAdmin(request)){
            return false;
        }
        if (id <= 0) {
            return false;
        }
        return usersService.removeById(id);
    }

    /**
     * 判断是否为管理员
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIGN_STATE);
        Users users = (Users) userObj;
        return users != null && users.getUserrole() == ADMIN_ROLE;
    }

}
