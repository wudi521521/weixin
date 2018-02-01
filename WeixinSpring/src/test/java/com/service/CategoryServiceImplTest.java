package com.service;

import com.dataoObject.ProductCategory;
import com.repository.CategoryDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Wudi
 * @Description:
 * @date 22:02  2017/12/20
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void findAll() throws Exception {

        List<ProductCategory> all = categoryDao.findAll();

        Assert.assertNotNull(all.size());

    }

    @Test
    public void findOne() throws Exception {

        ProductCategory one = categoryDao.findOne(1);

        Assert.assertNotNull("1",one.getCategoryId().toString());

    }

    @Test
    public void save() throws Exception {

        ProductCategory category = new ProductCategory("莫在意思停",10);

        ProductCategory save = categoryDao.save(category);

        Assert.assertNotNull(save);

    }

    @Test
    public void findAllByCategoryTypeIn() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2);

        List<ProductCategory> allByCategoryTypeIn = categoryDao.findAllByCategoryTypeIn(integers);

        Assert.assertNotNull(allByCategoryTypeIn.get(0));
    }

}