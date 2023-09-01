package data_struct;

/**
 * @author bk
 */
public class SingerLinkedListDemo {


    class SingerLinkedList {

        private LinkedNode head = new LinkedNode(0);

        public void add(LinkedNode node) {
            LinkedNode temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = node;
        }

        public void remove(LinkedNode node) {
            LinkedNode temp = head;
            while (temp.next != null) {
                if (temp.next.val == node.val) {
                    temp.next = temp.next.next;
                }
                temp = temp.next;
            }
        }
    }


    class LinkedNode {

        private int val;

        private int age;

        private LinkedNode next;

        public LinkedNode(int val) {
            this.val = val;
        }

        public LinkedNode(int val,LinkedNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
