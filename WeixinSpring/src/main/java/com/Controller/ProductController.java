package com.Controller;

import com.domain.Results;
import com.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wudi
 * @Description:
 * @date 23:00  2017/12/21
 */
@RestController
@RequestMapping("buyer/product")
@Slf4j
public class ProductController {



    @Autowired
    private ProductInfoService productInfoService;

    /**
     *商品展示列表
     * @return
     */
    @GetMapping("list")
    @Cacheable(cacheNames = "sell",key = "#number",condition = "#number.length()<3",unless = "#result.getCode() !=0")
    public Results getProductList(@RequestParam(value = "number",defaultValue = "23") String number){

        Results result = productInfoService.getProductList();

        return result;
    }

    /**
     * 修改商品名称
     * @param productId
     * @param productName
     */
    @GetMapping("update")
    @CacheEvict(cacheNames = "sell",key = "12345")
    public void updateProductName(@RequestParam(value = "productId",defaultValue = "123") String productId,
                                  @RequestParam(value = "productName",defaultValue = "月上柳梢头") String productName){

        productInfoService.updateProductName(productId,productName);
    }
    /**秒杀商品*/
    @GetMapping("cut/{productId}")
    public String cutProduct(@PathVariable("productId") String productId){
        log.info("【减少库存productId={}】",productId);
        //1:减少库存
        productInfoService.cutProudct(productId);
        //2:查询数据
         return productInfoService.queryProductData(productId);

    }
}
