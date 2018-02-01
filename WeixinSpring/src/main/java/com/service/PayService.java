package com.service;

import com.DTO.OrderMasterDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @author Wudi
 * @Description:
 * @date 13:24  2018/1/8
 */
public interface PayService {

    /**微信公众号的下单*/
    public PayResponse create(OrderMasterDTO orderMasterDTO);

    /**微信公众号的支付回调*/
    public PayResponse returnUrl(String notifyData);

    /**微信公众号取消退款*/
    RefundResponse refund(OrderMasterDTO orderMasterDTO);
}
