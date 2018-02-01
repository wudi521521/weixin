package com.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Wudi
 * @Description: 获取sdk中的配置文件，直接注入到sdk中
 * @date 20:39  2018/1/4
 */
@Component
public class WeChatMpConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    /**容器中注入配置文件*/
    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());

        return wxMpService;
    }
    /**获取appid和secret*/
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
       // WxMpConfigStorage wxMpConfigStorage = new WxMpConfigStorage();
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();

            wxMpInMemoryConfigStorage.setAppId(wechatAccountConfig.getMpAppId());
            wxMpInMemoryConfigStorage.setSecret(wechatAccountConfig.getMpAppSecret());

        return wxMpInMemoryConfigStorage;
    }

}
