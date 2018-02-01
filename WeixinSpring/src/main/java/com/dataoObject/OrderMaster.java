package com.dataoObject;

import com.enums.OrderMasterEnum;
import com.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Wudi
 * @Description: 订单表实体
 * @date 20:38  2017/12/26
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

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

    /**订单状态，默认为0下单,1结束订单，2是取消订单*/
    private Integer buyerStatus = OrderMasterEnum.MASTER_NEW.getCode();

    /**支付状态，默认2是待支付，0支付成功，1是支付失败*/
    private Integer payStatus = PayStatusEnum.PAY_WAIT.getCode();

    /**创建时间*/
    private Date createTime;

    /**修改的时间*/
    private Date updateTime;


}
