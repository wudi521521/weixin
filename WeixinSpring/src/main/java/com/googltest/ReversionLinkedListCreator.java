package com.googltest;

import java.util.Arrays;

/**
 * @author Wudi
 * @Description: 链表反转创建
 * @date 11:54  2018/1/22
 */
public class ReversionLinkedListCreator {

    public Node reversionLinkedList(Node head){

        if (head == null || head.getNext()==null){
            return head;
        }


        Node linkedList = reversionLinkedList(head.getNext());
             head.getNext().setNext(head);
             head.setNext(null);

           return linkedList;
    }

    public static void main(String[] args) {
        LinkedListCreator linked = new LinkedListCreator();

        ReversionLinkedListCreator reversion= new ReversionLinkedListCreator();
       // Node.printLinkedList(reversion.reversionLinkedList(linked.createLinkedList(Arrays.asList(1))));
        Node.printLinkedList(reversion.reversionLinkedList(linked.createLinkedList(Arrays.asList(11,22,33))));
       // Node.printLinkedList(reversion.reversionLinkedList(linked.createLinkedList(new ArrayList<>())));


    }
}
