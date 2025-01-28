package com.example.wagemanagement.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 */
@Data
public class UsersRegisterRequest implements Serializable {

    private static final long serialVersionUID = 858014496465336781L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

}
