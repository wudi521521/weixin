package com.DTO;

import com.Utils.EnumUntil;
import com.Utils.serialize.Date2LongSerialize;
import com.dataoObject.OrderDetail;
import com.enums.OrderMasterEnum;
import com.enums.PayStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



/**
 * @author Wudi
 * @Description: 订单参数
 * @date 14:47  2017/12/28
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)//返回的数据为null
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderMasterDTO {

    /**订单id*/
    @Id
    private String orderId;

    /**买家名字*/
    private String buyerName;

    /**买家电话*/
    private String buyerPhone;

    /**买家地址*/
    private String buyerAddress;

    /**买家微信openId*/
    private String buyerOpenid;

    /**买家金额*/
    private BigDecimal buyerAmount;

    /**订单状态，默认为0下单*/
    private Integer buyerStatus;

    /**支付状态，默认0是未支付*/
    private Integer payStatus;

    /**创建时间*/
    @JsonSerialize(using = Date2LongSerialize.class)
    private Date createTime;

    /**修改的时间*/
    @JsonSerialize(using = Date2LongSerialize.class)
    private Date updateTime;

    /**商品详情列表*/
    List<OrderDetail> orderDetailList;

    //通过status的参数判断enum中对应的参数
    @JsonIgnore //返回时忽略
    public OrderMasterEnum getOrderMasterEnum(){

        return EnumUntil.getCodeEnum(buyerStatus,OrderMasterEnum.class);

    }
    @JsonIgnore //返回时忽略
    public PayStatusEnum getPayStatusEnum(){

        return EnumUntil.getCodeEnum(payStatus,PayStatusEnum.class);
    }
}
