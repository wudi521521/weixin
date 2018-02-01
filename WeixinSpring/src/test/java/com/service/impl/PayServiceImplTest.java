package com.service.impl;

import com.DTO.OrderMasterDTO;
import com.service.OrderMasterService;
import com.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Wudi
 * @Description:
 * @date 16:09  2018/1/8
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class PayServiceImplTest {


    private static  final String orderId ="1514944547413413750";

    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private PayService payService;

    @Test
    public void create(){

        OrderMasterDTO one = orderMasterService.findOne(orderId);
        payService.create(one);

    }

    @Test
    public void refund() throws Exception {
        OrderMasterDTO one = orderMasterService.findOne(orderId);
        payService.refund(one);
    }

}