package com.Exception;

import com.enums.ResultEnum;

/**
 * @author Wudi
 * @Description: 自定义SellException异常
 * @date 15:54  2017/12/28
 */

public class SellException extends RuntimeException {

    private Integer code;

    private String msg;

    public SellException(ResultEnum result){

        super(result.getMsg());

        this.code = result.getCode();

    }

    public SellException(Integer code,String msg){

        super(msg);

        this.code = code;
    }
}
