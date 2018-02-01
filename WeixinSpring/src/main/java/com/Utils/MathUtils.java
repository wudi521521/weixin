package com.Utils;

/**
 * @author Wudi
 * @Description:
 * @date 15:25  2018/1/15
 */
public class MathUtils {

    private static final double math_range=0.1;

    public static Boolean equals(Double d1,Double d2){

        if (Math.abs(d1-d2)<math_range){
            return true;
        }else {
            return false;
        }
    }
}
