package com.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Wudi
 * @Description: 支付获取配置文件
 * @date 13:42  2018/1/8
 */
@Component
public class WeChatPayConfig {

    @Autowired
    public WechatAccountConfig wechatAccountConfig;

    /**容器中注入pay支付的配置文件*/
    @Bean
    public BestPayServiceImpl bestPayService(){

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());

        return bestPayService;

    }

    @Bean
    public WxPayH5Config wxPayH5Config(){

        WxPayH5Config wxPayH5Config = new WxPayH5Config();

        wxPayH5Config.setAppId(wechatAccountConfig.getMpAppId());//公众号的appId
        wxPayH5Config.setAppSecret(wechatAccountConfig.getMpAppSecret());//公众号的秘钥appSecrete
        wxPayH5Config.setMchId(wechatAccountConfig.getMchId());//商户的mch_id
        wxPayH5Config.setMchKey(wechatAccountConfig.getMchKey());//商户的mch_secrete
        wxPayH5Config.setKeyPath(wechatAccountConfig.getKeyPath());//商户证书的地址
        wxPayH5Config.setNotifyUrl(wechatAccountConfig.getNotifyUrl());//支付的回调地址
        return wxPayH5Config;
    }
}
