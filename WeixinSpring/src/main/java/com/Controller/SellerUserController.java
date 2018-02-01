package com.Controller;

import com.Utils.CookieUtils;
import com.config.SellProjectConfig;
import com.constant.CookieConstant;
import com.constant.RedisConstant;
import com.dataoObject.SellerInfo;
import com.enums.ResultEnum;
import com.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Wudi 后台登录
 * @date 9:12  2018/1/29
 */
@Controller
@RequestMapping("seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SellProjectConfig sellProjectConfig;

    /**
     * 用户登录
     * @param openid  唯一标识
     * @param response
     * @param map
     * @return
     */
    @GetMapping("login")
    @ResponseBody
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              HttpServletRequest request,
                              Map<String,Object> map){
         log.info("【登录者打印数据openid{}】",openid);
        //1:判断用户是否关注
        SellerInfo sellerInfoByOpenid = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfoByOpenid==null){
            map.put("msg", ResultEnum.USER_NOT_LOGIN.getMsg());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView(sellProjectConfig.getErrorUrl());
        }
        //2:设置token到redis中添加数据
        String token = UUID.randomUUID().toString();
        Integer expire =  RedisConstant.EXPIRE;
        String  token_prefix = RedisConstant.TOKEN_PREFIX;
        //格式：key,value,过期时间，时间格式为s
        redisTemplate.opsForValue().set(String.format(token_prefix,token),openid,expire, TimeUnit.SECONDS);

        //3:设置token添加到cookie中
        Integer maxAge = 2000;//过期时间s
        CookieUtils.setCookie(response, CookieConstant.TOKEN,token,maxAge);
        //跳转的路径需要是绝对路径
        log.info("【登录跳转路径】"+sellProjectConfig.getProjectUrl()+"/sell/seller/order/list");
        return new ModelAndView("redirect:"+sellProjectConfig.getProjectUrl()+"/sell/seller/order/list");
}

    /**
     * 用户的退出登录
     * @param request
     * @param response
     * @param map
     * @return
     */
    @GetMapping("loginOut")
    @ResponseBody
    public ModelAndView loginOut(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Map<String,Object> map){
        log.info("【用户退出登录】");
        //1:从cookie中的查询
        Cookie cookie = CookieUtils.getCookie( request, CookieConstant.TOKEN);

        if (cookie !=null){
            //2:删除redis中的数据
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //3:删除cookie中的数据
            CookieUtils.deleteCookie(response,cookie.getName());

            map.put("msg","退出登录页面成功");
            map.put("url","/sell/seller/order/list");

            return new ModelAndView(sellProjectConfig.getSuccessUrl(),map);
        }
        return new ModelAndView(sellProjectConfig.getErrorUrl());

    }
}
