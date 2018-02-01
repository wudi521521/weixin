package com.repository;

import java.util.Arrays;

/**
 * @author Wudi
 * @Description:
 * @date 22:18  2017/12/20
 */
public class MainTest {
    public static void main(String[] args) {

        //deepToString

        //二维数组
        int[][] erwei = {{1,2,3},{4,5,6},{7,8,9}};

        int [] yiwei = {1,2,3,4,5};

        //toString()
        System.out.println(Arrays.toString(erwei));//TODO 结果：[[I@6e8cf4c6, [I@12edcd21, [I@34c45dca]

        //deepToString()
        System.out.println(Arrays.deepToString(erwei));//TODO 结果:[[1, 2, 3], [4, 5, 6], [7, 8, 9]]




    }
}
