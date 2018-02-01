package com.Controller;

import com.Exception.SellException;
import com.alibaba.fastjson.JSONObject;
import com.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author Wudi
 * @Description: 微信
 * @date 9:10  2018/1/4
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeiXinController {


    /***
     * 授权获取 code 通过code获取页面授权的access_token和openid
     * @param code
     */
    @RequestMapping("/auth")
    public void author(@RequestParam("code") String code){
        log.info("【获取code】,code{}",code);
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx7ae4de6a618cbe0d&secret=7344b63ea02f5308d660caab53c10653&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTample = new RestTemplate();

        String forObject = restTample.getForObject(url, String.class);
        log.info("【数据打印】"+forObject);

        JSONObject jsonObject = JSONObject.parseObject(forObject);

        try{

            if (!Objects.isNull(jsonObject) && jsonObject.containsKey("openid")){
                log.info("【openid,access_token打印】openid："+jsonObject.get("openid")+"【access_token】"+jsonObject.get("access_token"));
            }

        }catch(Exception e){
            log.error("【数据异常】"+e);

            throw new SellException(ResultEnum.OPENID_FAIL);
        }
        //通过code，获取网页授权的access_token和openid

    }
}
