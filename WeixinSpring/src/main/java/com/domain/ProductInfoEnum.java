package com.domain;

import com.enums.CodeEnum;
import lombok.Getter;

/**
 * @author Wudi
 * @Description:商品枚举
 * @date 21:47  2017/12/21
 */
@Getter
public enum ProductInfoEnum implements CodeEnum{

    UP(0,"上架商品"),
    DOWN(1,"下架商品");

    private Integer code;

    private String msg;

    //无参构造
    ProductInfoEnum() {
    }

    //有参构造
    ProductInfoEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
