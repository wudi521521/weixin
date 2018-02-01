package com.test;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Wudi
 * @Description: 创建一个bean工厂
 * @date 14:36  2018/1/29
 */
@Component
public class RedisConfig {

    /**
     * 创建一个bean
     *
     * @return
     */
    @Bean
    public RedisConnectionFactory getFactory(){
        //设置redis的ip地址，端口号，如果有密码需要添加密码
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setHostName("192.168.40.246");

        return jedisConnectionFactory;
    }

    /**
     * 创建一个redisTemaplate模板
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate redisTemaplate(RedisConnectionFactory factory){
        //创建一个模板
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        //将redis连接放置在模板中
        template.setConnectionFactory(factory);
        return template;
    }
}
