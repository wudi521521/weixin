package com.Exception;

import com.enums.ResultEnum;

/**
 * @author Wudi
 * @Description: 自定义登录登录授权问题
 * @date 10:22  2018/1/30
 */
public class LoginAuthorizedException extends RuntimeException {

    private Integer code;

    private String msg;

    public LoginAuthorizedException(ResultEnum result){

        super(result.getMsg());

        this.code = result.getCode();

    }

    public LoginAuthorizedException(Integer code,String msg){

        super(msg);

        this.code = code;
    }
}
