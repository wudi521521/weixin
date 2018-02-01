package com.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Wudi
 * @Description: sell项目中的主要配置文件
 * @date 11:24  2018/1/18
 */
@Data
@Component
@ConfigurationProperties(prefix = "sellProject")
public class SellProjectConfig {
    /**项目域名*/
    private String projectUrl;

    /**错误提示静态页路径*/
    private String errorUrl;

    /**成功提示静态页路径*/
    private String successUrl;

    /**卖家商品展示路径*/
    private String sellerProudctListUrl;

    /**卖家商品编辑路径*/
    private String sellerProductIndexUrl;

    /**卖家订单展示路径*/
    private String sellerOrderListUrl;

    /**卖家订单编辑路径*/
    private String sellerOrderIndexUrl;

    /**卖家类目展示路径*/
    private String sellerCategoryListUrl;

    /**卖家类目编辑路径*/
    private String sellerCategoryIndexUrl;
}
