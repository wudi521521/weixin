package com.googltest;

import java.util.List;

/**
 * @author Wudi
 * @Description: 创建链表中的数据
 * @date 9:48  2018/1/22
 */
public class LinkedListCreator {

    public Node createLinkedList(List<Integer> data){

        if (data.isEmpty()){
            return null;
        }
        Node firstNode = new Node(data.get(0));
        //TODO list数组中共的sublist的函数,sublist(from,toIndex);返回的视图范围
        Node secondNode = createLinkedList(data.subList(1, data.size()));

        firstNode.setNext(secondNode);
        return firstNode;
        }

    public static void main(String[] args) {

       /* ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        System.out.println(arrayList);
        //部分视图展示,非线性结构修改
        System.out.println("数组部分视图展示:"+arrayList.subList(1,arrayList.size()));//数组部分视图展示:[2, 3]
        System.out.println("原有数据:"+arrayList);//原有数据:[1, 2, 3]
        //除去部分视图
        arrayList.subList(0,2).clear();
        System.out.println("除去部分视图:"+arrayList);//除去部分视图:[3]*/



    }
            }
