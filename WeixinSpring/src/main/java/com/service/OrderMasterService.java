package com.service;

import com.DTO.OrderMasterDTO;
import com.dataoObject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Wudi
 * @Description:
 * @date 14:39  2017/12/28
 */
public interface OrderMasterService {

    /** 查询订单总列表.（买家系统） */
    Page<OrderMasterDTO> findAllByBuyerOpenid(String openid, Pageable pageable);

    /**通过订单id获取数据*/
    List<OrderMaster> findAllByid(String id);

    /** 创建订单. */
    OrderMasterDTO createOrder(OrderMasterDTO dto);

    /** 查询单个订单. */
    OrderMasterDTO findOne(String orderId);

    /** 取消订单. */
    OrderMasterDTO cancelOrder(OrderMasterDTO dto);

    /** 完结订单. */
    OrderMasterDTO finishOrder(OrderMasterDTO dto);

    /** 支付订单. */
    OrderMasterDTO paidOrder(OrderMasterDTO dto);

    /** 查询订单列表.(卖家管理系统)*/
    Page<OrderMasterDTO> findAll(Pageable pageable);

    OrderMasterDTO save(OrderMasterDTO dto);


}
