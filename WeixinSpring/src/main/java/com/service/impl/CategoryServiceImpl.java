package com.service.impl;

import com.dataoObject.ProductCategory;
import com.repository.CategoryDao;
import com.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wudi
 * @Description:
 * @date 21:57  2017/12/20
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<ProductCategory> findAll() {

        return categoryDao.findAll();

    }

    @Override
    public ProductCategory findOne(Integer id) {
        log.info("【数据打印查找】"+id);

        return categoryDao.findOne(id);

    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        log.info("【数据打印保存】"+productCategory.toString());

        return categoryDao.save(productCategory);

    }

    @Override
    public List<ProductCategory> findAllByCategoryTypeIn(List<Integer> type) {
        //log.info("【数据打印通过类目】"+type.toString());

        return categoryDao.findAllByCategoryTypeIn(type);

    }
}
