package com.googltest;

import java.util.TreeMap;

/**
 * @author Wudi
 * @Description: 二分查找
 * @date 16:49  2018/1/25
 */
public class LoopBinaryMain {

    public int getBinaryData(Integer[] list, int number){
        //左边开始的数据
        int a = 0;
        //右边开始的数据
        int b = list.length;

        while(a<b){
            int result=(a+b)/2;
            if (list[result]<number){//将要查找的数据在右边
                a=result+1;
            }else if(list[result]>number){
                b=result;
            }else{
                return result;
            }
        }

        return -1;
    }

    public static void main(String[] args) {


        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("1","1");
        treeMap.put("2","2");
        treeMap.put("3","3");
        treeMap.put("41","4");
        treeMap.put("5","5");
        System.out.println("数据打印:"+treeMap.toString());

    }
}
