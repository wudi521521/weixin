package com.Utils.redislock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Wudi
 * @Description:
 * @date 14:05  2018/1/31
 */
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key 商品的id
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key,String value){
        //加锁返回的时true
        if (redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }
        //假设获取锁的值当前锁是A 其余2个线程是B 只会是一个线程拿到锁
        String currentTime = redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currentTime) && Long.parseLong(currentTime)<(System.currentTimeMillis())){
            //获取上一个锁的时间，getSet方式就是key,value1.使用value1替换原来过期的value
            //只能有一个线程执行getAndSet，（现在就是有3个线程抢占这一条线程。）
            String orderValue = redisTemplate.opsForValue().getAndSet(key, value);

            //替换后的时间是否与当前时间一致22，只有当线程A执行了getAndset才可以返回true
            if (StringUtils.isEmpty(orderValue) && orderValue.equals(currentTime)){
                return true;
            }
        }
        return false;
    }
    /**
     * 解锁
     * @param key  商品id
     * @param value 当前时间 + 超时时间
     */
    public void unlock(String key,String value){
        try{
            //1:获取redis中的value
            String currentTime = redisTemplate.opsForValue().get(key);
            //2:删除redis中的key
            if (currentTime.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch(Exception e){
            log.error("【redis解锁出现异常】"+e);
        }

    }
}
