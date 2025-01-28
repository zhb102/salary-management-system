package com.example.wagemanagement.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 */
@Data
public class UsersLoginRequest implements Serializable {

    private static final long serialVersionUID = 5393092520068104796L;

    private String userAccount;

    private String userPassword;

}
