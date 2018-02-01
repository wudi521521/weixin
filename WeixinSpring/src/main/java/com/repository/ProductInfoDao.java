package com.repository;

import com.dataoObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Wudi
 * @Description: 商品dao层，Jpa中前一个参数是实体，后一个参数是id
 * @date 20:53  2017/12/21
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String>{

    /**
     * 通过类目标识获取productInfo
     * @param type
     * @return
     */
    public List<ProductInfo> findAllByCategoryTypeIn(List<Integer> type);

    /**
     * 根据销售状态来获取数据
     * @param status
     * @return
     */
    public List<ProductInfo> findAllByStatus(Integer status);


}
