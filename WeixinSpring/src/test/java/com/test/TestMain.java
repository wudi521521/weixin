package com.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Wudi
 * @Description: 测试CollectionUtils工具类
 * @date 9:45  2018/1/2
 */
public class TestMain {
    public static void main(String[] args) {
      //TODO 判断使用测试CollectionUtils
      /*  String[] arrayA = new String[] { "1", "2", "3", "3", "4", "5" };
        String[] arrayB = new String[] { "3", "4", "4", "5", "6", "7" };
        List<String> a = Arrays.asList(arrayA);
        List<String> b = Arrays.asList(arrayB);

        //并集
        Collection<String> union = CollectionUtils.union(a, b);
        System.out.println("【并集union】"+union);//[1, 2, 3, 3, 4, 4, 5, 6, 7]
        //交集
        Collection<String> intersection = CollectionUtils.intersection(a, b);//[3, 4, 5]
        System.out.println("【交集intersection】" + intersection);
        //交集的补集
        Collection<String> disjunction = CollectionUtils.disjunction(a, b);//[1, 2, 3, 4, 6, 7]
        System.out.println("【交集的补集】"+disjunction);
        //集合相减
        Collection<String> subtract = CollectionUtils.subtract(a, b);//[1, 2, 3]
        System.out.println("【集合相减】"+subtract);
        System.out.println(CollectionUtils.isEmpty(a));*/
       //判断HashMap，LinkedHashMap,TreeMap
        Map map1 = new HashMap();
        Map map2 = new LinkedHashMap();
        for(int i=0;i<10;i++){
            double s=Math.random()*100;//产生一个随机数，并将其放入Map中
            map1.put(new Integer((int) s),"第 "+i+" 个放入的元素："+s+"\n");
            map2.put(new Integer((int) s),"第 "+i+" 个放入的元素："+s+"\n");
        }

        System.out.println("未排序前HashMap："+map1);
        System.out.println("未排序前LinkedHashMap："+map2);
        //使用TreeMap来对另外的Map进行重构和排序
        Map sortedMap = new TreeMap(map1);
        System.out.println("排序后："+sortedMap);
        System.out.println("排序后："+new TreeMap(map2));
    }

}
