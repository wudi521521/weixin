package com.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Wudi
 * @Description: redis中的测试
 * @date 14:43  2018/1/29
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private RedisConnectionFactory factory;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void getResult(){
        RedisConnection connection = factory.getConnection();
        connection.set("wudi".getBytes(),"wudi1234567".getBytes());
        System.out.println(new String(connection.get("wudi".getBytes())));

    }

    @Test
    public void getResultTemplate(){
        redisTemplate.opsForValue().set("wd","wudi");

        System.out.println(redisTemplate.opsForValue().get("wd"));
    }

    @Test
    public void testRedisTemplateList(){

    }

}