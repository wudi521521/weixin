package com.service;

import com.dataoObject.ProductCategory;

import java.util.List;

/**
 * @author Wudi
 * @Description:
 * @date 21:52  2017/12/20
 */
public interface CategoryService {

    /**
     * 查询所有
     * @return
     */
    public List<ProductCategory> findAll();

    /**
     * 通过id查询一个
     * @param id
     * @return
     */
    public ProductCategory findOne(Integer id);

    /**
     * Category的保存和修改
     * @param productCategory
     * @return
     */
    public ProductCategory save(ProductCategory productCategory);

    /**
     * 通过categoryType获取所有ProductCategory
     * @param type categoryType
     * @return
     */
    public List<ProductCategory> findAllByCategoryTypeIn(List<Integer> type);
}
