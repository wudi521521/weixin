package com.enums;

import lombok.Getter;

/**
 * @author Wudi
 * @Description:
 * @date 20:27  2017/12/28
 */
@Getter
public enum ProductEnum implements CodeEnum {
    PRODUCT_NOTPARAM(12281,"数据为空"),
    PRODUCT_NOTRESULT(12282,"没有返回参数"),
    ;
    private Integer code;

    private String msg;

    ProductEnum() {
    }

    ProductEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
