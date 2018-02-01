package com.service;

import com.DTO.OrderMasterDTO;

/**
 * @author Wudi
 * @Description: 买家
 * @date 12:09  2018/1/3
 */
public interface BuyerService {

    /**查询一个数据*/
    public OrderMasterDTO findOrderMasterOne(String openid,String orderId);

    /**取消订单*/
    public OrderMasterDTO cancelOrderMaster(String openid,String orderId);
}
