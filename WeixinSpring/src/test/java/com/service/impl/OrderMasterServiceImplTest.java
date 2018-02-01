package com.service.impl;

import com.DTO.OrderMasterDTO;
import com.dataoObject.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wudi
 * @Description:
 * @date 17:58  2017/12/29
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {


    private final String buyer_openid="1234567890111";

    @Autowired
    private OrderMasterServiceImpl orderMasterService;

    /**通过创建订单*/
    @Test
    public void createOrder() throws Exception {

        //创建DTO
        OrderMasterDTO dto = new OrderMasterDTO();
        dto.setBuyerAddress("购买人的地址");
        dto.setBuyerAmount(new BigDecimal("2.2"));
        dto.setBuyerOpenid(buyer_openid);
        dto.setBuyerName("买家名");
        dto.setBuyerPhone("13241372414");

        //创建orderDetail
        OrderDetail detail = new OrderDetail();
        detail.setProductId("12");//商品id
        detail.setProductQuantity(1);//商品数量
        detail.setProductPrice(new BigDecimal(1));

        //创建HashMap
        List<OrderDetail> map = new ArrayList<OrderDetail>();
        map.add(detail);
        dto.setOrderDetailList(map);

        orderMasterService.createOrder(dto);

    }

    /**通过id获取订单数据*/
    @Test
    public void findOne() throws Exception {
        OrderMasterDTO one = orderMasterService.findOne("1514801780969883693");

        log.info("【通过订单id查询" +
                "】"+one.toString());
    }
    /**通过openid测试订单列表*/
    @Test
    public void findAllByBuyerOpenid() throws Exception {
        //分页
        PageRequest pageRequest = new PageRequest(0, 3);
        //获取数据
        Page<OrderMasterDTO> allByBuyerOpenid = orderMasterService.findAllByBuyerOpenid(buyer_openid, pageRequest);

        Assert.assertNotNull(allByBuyerOpenid.getContent());

    }

     /**取消订单*/
    @Test
    public void cancelOrder() throws Exception {
        //创建DTO
        OrderMasterDTO dto = new OrderMasterDTO();
        dto.setBuyerAddress("购买人的地址");
        dto.setBuyerAmount(new BigDecimal("2.2"));
        dto.setBuyerOpenid(buyer_openid);
        dto.setBuyerName("买家名");
        dto.setBuyerPhone("13241372414");
        dto.setOrderId("1514887243561239213");

        //创建orderDetail
        OrderDetail detail = new OrderDetail();
        detail.setProductId("12");//商品id
        detail.setProductQuantity(1);//商品数量
        detail.setProductPrice(new BigDecimal(1));

        //创建HashMap
        List<OrderDetail> map = new ArrayList<OrderDetail>();
        map.add(detail);
        dto.setOrderDetailList(map);

        orderMasterService.cancelOrder(dto);

    }
    /**修改订单的状态   */
    @Test
    public void finnishOrder() throws Exception {
        OrderMasterDTO dto = new OrderMasterDTO();
        dto.setOrderId("1514879748587550599");
        OrderMasterDTO orderMasterDTO = orderMasterService.finishOrder(dto);


    }
    /**修改支付状态*/
    @Test
    public void paidOrder() throws Exception {
        OrderMasterDTO dto = new OrderMasterDTO();
        dto.setOrderId("1514879748587550599");
        OrderMasterDTO orderMasterDTO = orderMasterService.paidOrder(dto);
    }

    /**卖家后台列表*/
    @Test
    public void findAll(){
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMasterDTO> all = orderMasterService.findAll(pageRequest);
        Assert.assertTrue("卖家后台查询列表",all.getTotalPages()>0);
    }




}