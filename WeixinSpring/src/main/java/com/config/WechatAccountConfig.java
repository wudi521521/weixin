package com.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Wudi
 * @Description: 获取配置文件
 * @date 20:51  2018/1/4
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")//获取配置文件中的数据
public class WechatAccountConfig {

    /**公众号id*/
    public String mpAppId;

    /**公众号秘钥*/
    public String mpAppSecret;

    /**开放平台id*/
    public String openAppId;

    /**开放平台秘钥*/
    public String openAppSecret;

    /**商户id*/
    public String mchId;

    /**商户秘钥*/
    public String mchKey;

    /**证书路径*/
    public String keyPath;

    /**支付回调地址*/
    public String notifyUrl;
}
