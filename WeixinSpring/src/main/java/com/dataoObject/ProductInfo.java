package com.dataoObject;

import com.Utils.EnumUntil;
import com.domain.ProductInfoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Wudi
 * @Description:商品详情表
 * @date 20:47  2017/12/21
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {

    @Id
    private String productId;

    /**商品名*/
    private String productName;

    /**单价*/
    private BigDecimal productPrice;

    /**库存*/
    private Integer productStock;

    /**商品描述*/
    private String productDescribtion;

    /**图片链接*/
    private String productIcon;

    /**类目编号*/
    private Integer  categoryType;

    /**创建时间*/
    private Date createTime;

    /**修改的时间*/
    private Date updateTime;

    /**0是上架1是下架(默认上架)*/
    private Integer status = ProductInfoEnum.UP.getCode();

    /**返回的是商品上架还是下架的展示*/
    @JsonIgnore
    public ProductInfoEnum getProductInfoEnum(){

        return EnumUntil.getCodeEnum(status,ProductInfoEnum.class);
    }


}
