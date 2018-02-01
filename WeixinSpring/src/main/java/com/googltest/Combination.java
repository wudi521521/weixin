package com.googltest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wudi
 * @Description: 遍历所有的组合
 * @date 15:17  2018/1/22
 */
public class Combination {

    public void combination(List<Integer> data,List<Integer> list,int n){
        if (n==0){
            for (Integer i:data){
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
            return ;
        }
        if (list.isEmpty()){
            return ;
        }
         data.add(list.get(0));//添加上第一个参数

        //遍历所有组合选择了第0号元
        combination(data,list.subList(1,list.size()),n-1);

        data.remove(data.size()-1);//移除最后一个元素
        //遍历所有组合没有选择第0号元素
        combination(data,list.subList(1,list.size()),n);
    }

    public static void main(String[] args) {
        /*Combination combination = new Combination();
        combination.combination(new ArrayList<>(),Arrays.asList(1,2,3),2);*/

        List<Integer> objects = new ArrayList<Integer>();
        if (objects.isEmpty()){
            System.out.println("数组为空:"+objects.isEmpty());
        }
        objects.add(1);
        if (objects.get(0)>0){
            System.out.println("添加完数据后判断是否为数据"+objects.isEmpty());

        }

    }
}
