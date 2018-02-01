package com.enums;

import lombok.Getter;

/**
 * @author Wudi
 * @Description:
 * @date 21:19  2017/12/265
 */
@Getter
public enum OrderMasterEnum implements CodeEnum{

    MASTER_NEW(0,"新订单"),
    MASTER_FINISHED(1,"订单结束"),
    MASTER_CANCLE(2,"订单取消"),
    ;
    private Integer code;

    private String msg;

    //无参构造
    OrderMasterEnum() {}

    OrderMasterEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
