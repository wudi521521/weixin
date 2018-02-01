package com.service.impl;

import com.dataoObject.ProductInfo;
import com.domain.ProductInfoEnum;
import com.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Wudi
 * @Description:
 * @date 21:55  2017/12/21
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ProductInfoServiceImplTest {


    @Autowired
    private ProductInfoService dao;

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findDownByStatus() throws Exception {

        List<ProductInfo> downByStatus = dao.findDownByStatus(ProductInfoEnum.DOWN.getCode());

        Assert.assertNotNull(downByStatus.get(0));
    }

    //使用分页来获取数据
    @Test
    public void findAll() throws Exception {

        PageRequest pageRequest = new PageRequest(0, 2);

        Page<ProductInfo> all = dao.findAll(pageRequest);

        System.out.println("======:"+all.getTotalElements());
        System.out.println("======:"+all.getTotalElements());

    }
    @Test
    public void onSale(){
        ProductInfo productInfo = productInfoService.onSale("12");
        Assert.assertNotNull(productInfo);

    }


}