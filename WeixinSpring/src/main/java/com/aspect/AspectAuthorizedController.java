package com.aspect;

import com.Exception.LoginAuthorizedException;
import com.Utils.CookieUtils;
import com.constant.CookieConstant;
import com.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Wudi
 * @Description: 后台登录切面
 * @date 9:47  2018/1/30
 */
@Aspect//确定切面
@Component
@Slf4j
public class AspectAuthorizedController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    //切口,进行切口的方法主要在com.Controller.Seller**中的方法中共，但是登录的不执行
    @Pointcut("execution(public * com.Controller.Seller*.*(..))"+
              "&& !execution(public * com.Controller.SellerUserController.*(..))")
    public void verify(){}
    //前置环绕
    @Before("verify()")//verify()命名切入点
    public void doVerify(){
        //requestContextHolder,reqeust上下文的持有者
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //服务器中属性
        ServletRequestAttributes requests = (ServletRequestAttributes)requestAttributes;
        //从服务器属性中获取request
        HttpServletRequest request = requests.getRequest();
        Cookie cookie  = CookieUtils.getCookie(request, CookieConstant.TOKEN);
        //cookie中查询
        if (cookie == null){
            log.info("【登录cookie中没有数据】");
            throw new LoginAuthorizedException(132,"登录cookie中没有数据");
        }
        //redis中查询
        String redisToken = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (redisToken==null){
            log.info("【登录redis中是没有参数】");
            throw new LoginAuthorizedException(133,"登录redis中没有参数");
        }
    }
}
