package com.enums;

import lombok.Getter;

/**
 * @author Wudi
 * @Description:支付状态
 * @date 21:23  2017/12/26
 */
@Getter
public enum PayStatusEnum implements CodeEnum {

    PAY_WAIT(0,"待支付"),
    PAY_SUCCESS(1,"支付成功"),
    PAY_FAIL(2,"支付失败"),
    ;

    private Integer code;

    private String msg;

    //无参构造
    PayStatusEnum() {
    }

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
