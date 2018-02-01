package com.service;

import com.DTO.CartDTO;
import com.dataoObject.ProductInfo;
import com.domain.Results;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Wudi
 * @Description: 商品信息
 * @date 21:23  2017/12/21
 */
public interface ProductInfoService {

    /**
     * 通过id获取数据
     * @param id
     * @return
     */
    public ProductInfo findOne(String id);

    /**
     * 保存数据
     * @param productInfo
     * @return
     */
    public ProductInfo save(ProductInfo productInfo);

    /**
     * 分页获取所有数据
     * @return
     */
    public Page<ProductInfo> findAll(Pageable pageable);

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

    /**
     * 上架商品
     * @return
     */
    public List<ProductInfo> findUpByStatus();

    /**
     * 下架商品
     * @param status
     * @return
     */
    public List<ProductInfo> findDownByStatus(Integer status);

    /**
     * 获取商品列表
     */
    public Results getProductList();

    /**
     * 增加库存
     *
     */
    public void addProductStock(List<CartDTO> cartDTOs);

    /**
     * 减少库存
     *
     */
    public void cutProductStock(List<CartDTO> cartDTOs);

    /**
     * 下架商品
     * @param productId 商品id
     * @return
     */
    public ProductInfo onSale(String productId);

    /**
     * 上架商品
     * @param productId 商品id
     * @return
     */
    public ProductInfo offSale(String productId);

    /**
     * 通过商品id进行秒杀商品
     * @param productId 订单id
     * @return
     */
    public void cutProudct(String productId);

    /**
     * 通过订单id查询数据返回到页码
     * @param productId 订单id
     * @return
     */
    public String queryProductData(String productId);

    /**
     * 通过商品id修改商品的名
     * @param productId
     * @param prodcutName
     */
    public void updateProductName(String productId,String prodcutName);

}
