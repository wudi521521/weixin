package com.repository;

import com.dataoObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Wudi
 * @Description:
 * @date 21:20  2017/12/20
 */
public interface CategoryDao extends JpaRepository<ProductCategory,Integer> {
    /**
     * 通过type获取所有的ProductCategory数据
     * @param type
     * @return
     */
    public List<ProductCategory> findAllByCategoryTypeIn(List<Integer> type);
}
