package com.repository;

import com.dataoObject.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Wudi
 * @Description:
 * @date 18:08  2017/12/28
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    //通过订单号查询查询订单详情，分页
     Page<OrderDetail> findAllByOrderId(String orderId, Pageable pageable);

    //通过商品id获取订单详情
    Page<OrderDetail> findAllByProductId(String productId, Pageable pageable);

    //通过订单号查询订单详情，不分页
    List<OrderDetail> findAllByOrderId(String orderId);
}
