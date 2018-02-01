package com.Controller;

import com.Exception.SellException;
import com.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;


/**
 * @author Wudi
 * @Description: 微信公众平台和微信开放平台
 * @date 20:19  2018/1/4
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;//公众平台，自定义获取appid和appsecret



    @Autowired
    private WxMpService wxOpenService;//开放平台，自定义获取appid和appsecret

    /***
     * 公众平台， 授权路径获取 code
     */
    @GetMapping("/authorize")
    public String  authorize(@RequestParam("returnUrl") String returnUrl ){
        log.info("【公众平台授权路径】,returnUrl{}"+returnUrl);

                String url = "http://wudi.nat100.top/sell/wechat/userInfo";

                String redirectUrl;
                redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, "snsapi_base", URLEncoder.encode(returnUrl));
                log.info("【公众平台授权路径跳转路径】"+redirectUrl);

        //转发
        return "redirect:"+redirectUrl;
    }

    /***
     * 公众平台，回调地址，通过code 获取openid和access_token
     */
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        log.info("【公众平台回调获取openid，returnUrl】openid{},returnUrl{}",code,returnUrl);

                WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
                    try {
                        wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);

                        log.info("【公众平台微信授权回调数据】"+wxMpOAuth2AccessToken);
                    }catch (WxErrorException e){
                        log.error("【公众平台微信获取access_token异常】"+e);

                        throw new SellException(ResultEnum.ACCESSTOKEN_FAIL.getCode(),e.getError().getErrorMsg());
                    }
                //TODO 获取openid
                String openid = wxMpOAuth2AccessToken.getOpenId();
                String accessToken = wxMpOAuth2AccessToken.getAccessToken();
        log.info("【公众平台openid】"+openid,"【公众平台accessToken】"+accessToken);

        return "redirect:"+returnUrl+"?openid="+openid;
    }

    /***
     * 开放平台， 授权路径获取 code
     */
    @GetMapping("/qrAuthorize")
    public String  qrAuthorize(@RequestParam("returnUrl") String returnUrl ){
        log.info("【开放平台，授权路径】,returnUrl{}"+returnUrl);

        String url = "http://wudi.nat100.top/sell/wechat/qrUserInfo";

        String redirectUrl;
        redirectUrl = wxOpenService.buildQrConnectUrl(url, "snsapi_login", URLEncoder.encode(returnUrl));
        log.info("【开放平台，授权路径跳转路径】"+redirectUrl);

        //转发
        return "redirect:"+redirectUrl;
    }

    /***
     * 公众平台，回调地址，通过code 获取openid和access_token
     */
    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl){
        log.info("【开放平台，回调获取openid，returnUrl】openid{},returnUrl{}",code,returnUrl);

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);

            log.info("【开放平台，微信授权回调数据】"+wxMpOAuth2AccessToken);
        }catch (WxErrorException e){
            log.error("【开放平台,微信获取access_token异常】"+e);

            throw new SellException(ResultEnum.ACCESSTOKEN_FAIL.getCode(),e.getError().getErrorMsg());
        }
        //TODO 通过回调获取openid
        String openid = wxMpOAuth2AccessToken.getOpenId();
        String accessToken = wxMpOAuth2AccessToken.getAccessToken();
        log.info("【开放平台,openid】"+openid,"【开放平台,accessToken】"+accessToken);

        return "redirect:"+returnUrl+"?openid="+openid;
    }
}
