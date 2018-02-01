package com.dataoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Wudi
 * @Description: 订单详情表
 * @date 15:45  2017/12/28
 */
@Data
@DynamicUpdate
@Entity
public class OrderDetail {
    /**订单详情id*/
    @Id
    private String detailId;

    /**订单id*/
    private String orderId;

    /**商品id*/
    private String productId;

    /**商品名*/
    private String productName;

    /**商品价格*/
    private BigDecimal productPrice;

    /**商品数量*/
    private Integer productQuantity;

    /**图片地址*/
    private String productIcon;

    /**创建时间*/
    private Date createTime;

    /**修改的时间*/
    private Date updateTime;
}
