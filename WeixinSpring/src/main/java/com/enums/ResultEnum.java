package com.enums;

import lombok.Getter;

/**
 * @author Wudi
 * @Description:
 * @date 16:20  2017/12/28
 */
@Getter
public enum ResultEnum {
               PARAM_NOT_EXIST(-1,"参数不存在"),
               RESULT_ERROR(-2,"数据异常"),
               ORDER_NOTEQUALS_PRICE(12282,"订单的金额不一致"),
               RESULT_SUCCESS(200,"成功"),
               RESULT_NOTEXIST(12283,"商品不存在"),
               PRODUCT_STOCK_LT(12284,"商品库存不足"),
               ORDER_NOT_EXIST(01021,"订单不存在"),
               ORDERDETAIL_NOT_EXIST(01022,"详情订单不存在"),
               ORDER_MASTER_CANCLE(01023,"商品订单状态不符合要求"),
               ORDER_UPDATE_ERROR(01024,"商品订单状态修改失败"),
               PAY_STATUS_ERROR(01025,"支付订单订单状态不对"),
               ORDERFORM_ERROR(01031,"Gson数据转化异常"),
               OPENDID_NOT_NULL(01032,"openId不能为空"),
               ORDERID_NONE_NULL(01033,"订单id不能为空"),
               PERSON_NONE_NULL(01034,"不是本人订单"),
               OPENID_FAIL(01041,"获取openid失败"),
               ACCESSTOKEN_FAIL(01051,"获取access_token失败"),
               ODER_AMOUNT_NOTEQUAL(01151,"订单金额与回调订单金额不一致"),
               ORDER_CANCLE(01161,"取消订单成功"),
               ORDER_FINISH(01161,"完善订单成功"),
               RESULT_PRODUCT_NULL(01171,"没有此订单"),
               PRODUCT_STATUS_NOTEQUAL(01172,"商品的状态上下架状态不符合"),
               SELLER_NULL(01173,"该商户不存在"),
               USER_NOT_LOGIN(01174,"用户没有登录"),

            ;
            private Integer code;

            private String msg;

            //无参构造
            ResultEnum() {
            }

            //有参构造
            ResultEnum(Integer code, String msg) {
                this.code = code;
                this.msg = msg;
            }
}
