package com.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Wudi
 * @Description:
 * @date 23:31  2018/1/2
 */
@Data
public class OrderForm {

    /**姓名*/
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**手机号*/
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**地址*/
    @NotBlank(message = "地址不能为空")
    private String address;

    /**微信openid*/
    @NotBlank(message = "微信openid不能为空")
    private String openid;

    /**json串，含有productId,productQuantity*/
    @NotBlank(message = "订单id和商品数量不能为空")
    private String items;
}
