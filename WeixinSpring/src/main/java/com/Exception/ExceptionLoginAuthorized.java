package com.Exception;

import com.config.SellProjectConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Wudi
 * @Description:  捕获异常处理
 * @date 10:31  2018/1/30
 */
@ControllerAdvice
@Slf4j
public class ExceptionLoginAuthorized {

    @Autowired
    private SellProjectConfig config;

    @ExceptionHandler(value = LoginAuthorizedException.class)
    public ModelAndView redirectLoginUrl(Exception e){
        log.info("【授权异常处理的e={}】",e.getMessage());

        return new ModelAndView("redirect:"
                .concat("授权路径")
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(config.getProjectUrl())
                .concat("/sell/seller/login"));
    }
}
