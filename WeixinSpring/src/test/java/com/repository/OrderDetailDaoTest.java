package com.repository;

import com.alibaba.fastjson.JSON;
import com.dataoObject.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author Wudi
 * @Description:
 * @date 14:17  2017/12/29
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void findAllByOrderId() throws Exception {
      OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1111");
        orderDetail.setProductId("22");
        orderDetail.setOrderId("33");
        orderDetail.setProductIcon("图片");
        orderDetail.setProductPrice(new BigDecimal(11));
        orderDetail.setProductName("商品名字");
        orderDetail.setProductQuantity(11);//商品数量
        OrderDetail save = orderDetailDao.save(orderDetail);
        log.info("【数据打印】"+ JSON.toJSONString(save));
        Assert.assertNotNull(save);
      /*  //设置分页
        orderDetailDao.findAllByOrderId("",)*/
    }

    @Test
    public void findAllByProductId() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10);//第一个参数是当前参数，第二个参数是当前页的数据

       /* Page<OrderDetail> allByOrderId = orderDetailDao.findAllByOrderId(33 + "", pageRequest);

        log.info("【总的页数】"+allByOrderId.getTotalPages());
        log.info("【总的数据】"+allByOrderId.getTotalElements());
        log.info("【数据】"+allByOrderId.getContent());*/
      /*  Assert.assertNotNull(allByOrderId);*/
        Page<OrderDetail> allByProductId = orderDetailDao.findAllByProductId("22", pageRequest);
        log.info("【总的页数】"+allByProductId.getTotalPages());
        log.info("【总的数据】"+allByProductId.getTotalElements());
        log.info("【数据】"+allByProductId.getContent());
    }

}