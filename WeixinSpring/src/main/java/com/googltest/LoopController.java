package com.googltest;

/**
 * @author Wudi
 * @Description:  循环数组
 * @date 16:40  2018/1/23
 */
public class LoopController {

    public Node reverseLinkedList(Node head){
        Node newHead=null;
        Node curentHead = head;
        while(curentHead!=null){
            //curentHead==null
            Node next = curentHead.getNext();//next =null
            curentHead.setNext(newHead);
            newHead = curentHead;
            curentHead = next;
        }
        //循环不变式
        return  newHead;
    }

    public static void main(String[] args) {
       /* List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        Collections.reverse(integers);
        System.out.println(integers);*/




    }
}
