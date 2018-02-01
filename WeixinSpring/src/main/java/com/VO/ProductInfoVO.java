package com.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Wudi
 * @Description:
 * @date 23:50  2017/12/21
 */
@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class ProductInfoVO implements Serializable{

    /**序列化*/
    private static final long serialVersionUID = 6107844061285065573L;

    @JsonProperty("id")
    private String productId;

    /**
     * 商品名
     */
    @JsonProperty("name")
    private String productName;

    /**
     * 单价
     */
    @JsonProperty("price")
    private BigDecimal productPrice;


    /**
     * 商品描述
     */
    @JsonProperty("description")
    private String productDescribtion;

    /**
     * 图片链接
     */
    @JsonProperty("icon")
    private String productIcon;


}
