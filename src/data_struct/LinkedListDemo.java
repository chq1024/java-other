package data_struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author bk
 */
public class LinkedListDemo {

    private static class LinkedList {

        private LinkNode head;

        public LinkedList() {
        }

        public void add(int value) {
            LinkNode temp = head;
            if (temp == null) {
                temp = new LinkNode(value);
                head = temp;
                return;
            }
            while (true) {
                if (temp.next == null) {
                    temp.next = new LinkNode(value);
                    return;
                }
                temp = temp.next;
            }
        }

        public void remove(int value) {
            LinkNode temp = head;
            while (true) {
                if (temp.val == value) {
                    temp = temp.next;
                    head = temp;
                    return;
                }
                temp = temp.next;
            }
        }


        public void insert(LinkNode before,LinkNode node) {
            LinkNode curr = before.next;
            before.next = node;
            node.next = curr;
        }
    }


    private static class LinkNode {
        private int val;
        private LinkNode next;

        public LinkNode(int val) {
            this.val = val;
        }
    }


    public static void main(String[] args) {
        ArrayList<Integer> curr = new ArrayList<>();
        curr.add(1);
        curr.add(2);
        curr.add(3);
        curr.add(4);
        curr.add(5);

        ArrayList<Integer> cur2 = new ArrayList<>();
        cur2.add(3);
        cur2.add(4);

        String a = "hello";
        String b = "word";
        a.equals(b);
        curr.retainAll(cur2);
    }
}
