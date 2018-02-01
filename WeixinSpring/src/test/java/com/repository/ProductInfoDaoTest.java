package com.repository;

import com.dataoObject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wudi
 * @Description:
 * @date 20:56  2017/12/21
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao dao;

    @Test
    public void save(){
        ProductInfo info = new ProductInfo();
        info.setProductId("12312111");
       /* info.setCategoryType("12312211");*/
        info.setProductName("商品名");
        info.setProductPrice(new BigDecimal(1.2));
        info.setProductStock(123);
        info.setProductDescribtion("好商品");
        info.setProductIcon("1234567");
        info.setStatus(0);

        ProductInfo save = dao.save(info);
        Assert.assertNotNull(save);
    }

    @Test
    public void findAllByCategoryTypeIn() throws Exception {

        List<String> strings = Arrays.asList("12", "123");

     /*   List<ProductInfo> allByCategoryTypeIn = dao.findAllByCategoryTypeIn(strings);*/

       /* Assert.assertNotNull(allByCategoryTypeIn.get(0).getCategoryType());*/


    }

    @Test
    public void update(){



    }

}