package com.repository;

import com.dataoObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Wudi
 * @Description:
 * @date 21:21  2017/12/20
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void findOne(){

        ProductCategory category = categoryDao.findOne(1);

        category.setCategoryName("宁在一思进213");



        ProductCategory save = categoryDao.save(category);

        Assert.assertNotNull(null,save);
    }

}