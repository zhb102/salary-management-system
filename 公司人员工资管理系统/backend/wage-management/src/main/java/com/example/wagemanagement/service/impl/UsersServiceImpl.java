package com.example.wagemanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wagemanagement.mapper.UsersMapper;
import com.example.wagemanagement.model.domain.Users;
import com.example.wagemanagement.service.UsersService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.wagemanagement.constant.UsersConstant.USER_LOGIGN_STATE;

/**
* @author zhb
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-06-05 13:59:39
*/
@Service
@Slf4j
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

    @Resource
    private UsersMapper usersMapper;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "zhb";

    @Override
    public int userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        // 账户不能重复
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userAccount);
        long count = usersMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 3. 插入数据
        Users users = new Users();
        users.setUsername(userAccount);
        users.setPassword(encryptPassword);
        boolean saveResult = this.save(users);
        if (!saveResult) {
            return -1;
        }
        return users.getUserid();
    }

    @Override
    public Users userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        if (userAccount.length() < 4) {
            return null;
        }
        if (userPassword.length() < 8) {
            return null;
        }
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "test");
        queryWrapper.eq("password", encryptPassword);
        Users users = usersMapper.selectOne(queryWrapper);
        if(users == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            return null;
        }
        System.out.println(users.getUserid());
        // 3. 用户信息脱敏
        Users safetyUsers = getSafetyUser(users);
        // 4. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIGN_STATE, safetyUsers);
        return safetyUsers;
    }

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    @Override
    public Users getSafetyUser(Users originUser) {
        if (originUser == null) {
            return null;
        }
        Users safetyUsers = new Users();
        safetyUsers.setUserid(originUser.getUserid());
        safetyUsers.setUsername(originUser.getUsername());
        safetyUsers.setCreatedat(originUser.getCreatedat());
        safetyUsers.setUserrole(originUser.getUserrole());
        return safetyUsers;
    }


}




