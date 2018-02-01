package com.googltest;

/**
 * @author Wudi
 * @Description: 测试链接模块
 * @date 9:46  2018/1/22
 */
public class Node {

    private final int value;

    private Node next;

    public int getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(int vaule){

        this.value=vaule;

        this.next=null;
    }

    public static void printLinkedList(Node head){
        while (head !=null){
            System.out.println(head.getValue());
            head=head.getNext();
        }
        System.out.println();
    }
}
