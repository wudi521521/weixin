package com.dataoObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Wudi
 * @Description: 卖家实体
 * @date 13:09  2018/1/18
 */
@Data
@Entity
@DynamicUpdate
public class SellerInfo {

    @Id
    private String sellerId;

    /**用户名*/
    private String username;

    /**用户密码*/
    private String password;

    /**创建时间*/
    private Date createTime;

    /**修改时间*/
    private Date updateTime;

    /**开放平台的唯一标识*/
    private String openid;
}
