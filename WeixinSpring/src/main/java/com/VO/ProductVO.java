package com.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Wudi
 * @Description:商品vo@json Property主要是映射，到页面的字段
 * @Jo
 * * @date 23:18  2017/12/21
 */
@Data
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class ProductVO implements Serializable{

    /**序列化*/
    private static final long serialVersionUID = -7835210837193339322L;

    /**
     * 类目名
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 类目id
     */
    @JsonProperty("type")
    private Integer categoryType;

    /**
     * 商品
     */
    @JsonProperty("foods")
    List<ProductInfoVO> productInfoVOList;
}
