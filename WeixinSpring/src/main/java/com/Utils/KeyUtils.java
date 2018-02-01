package com.Utils;

import java.util.Random;

/**
 * @author Wudi
 * @Description: 唯一的id
 * @date 14:17  2017/12/28
 */
public class KeyUtils {

    public static synchronized String  getUniqueKey(){

        //随机数和时间戳
        Random random = new Random();

        Integer number =  random.nextInt(999999)+100000;

        return System.currentTimeMillis()+String.valueOf(number);

    }

    public static void main(String[] args) {
        getUniqueKey();
        System.out.println("【随机数的产生：】"+getUniqueKey());
    }
}
