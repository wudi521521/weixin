package com.Exception;

import com.enums.ResultEnum;

/**
 * @author Wudi
 * @Description:
 * @date 20:24  2017/12/28
 */
public class ProductException extends RuntimeException {

    private Integer code;

    private String msg;

    public ProductException(ResultEnum result){
        super(result.getMsg());
        this.code = result.getCode();
    }

    public ProductException(Integer code,String msg){
        super(msg);
        this.code = code;
    }
}
