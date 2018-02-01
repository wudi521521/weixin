package com.constant;

/**
 * @author Wudi
 * @Description: 定义redis常量
 * @date 10:24  2018/1/29
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";

    Integer EXPIRE = 7200; //2小时
}
