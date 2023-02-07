package 刷题;

/**
 * @author bk
 */
public class hot02 {

    // 数组长度长处long 限制
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder num1 =  new StringBuilder();
        while (l1 != null) {
            num1.append(l1.val);
            l1 = l1.next;
        }
        StringBuilder num2 =  new StringBuilder();
        while (l2 != null) {
            num2.append(l2.val);
            l2 = l2.next;
        }
        long n1 = Long.parseLong(num1.reverse().toString());
        long n2 = Long.parseLong(num2.reverse().toString());
        long num = n1 + n2;
        String[] split = String.valueOf(num).split("");
        ListNode node = new ListNode(Integer.parseInt(split[0]));
        for (int i = 1; i < split.length; i++) {
            ListNode tempNode = new ListNode(Integer.parseInt(split[i]));
            tempNode.next = node;
            node = tempNode;
        }
        return node;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int i = 0;
        ListNode head = null,node = null;
        while (l1 != null || l2 != null) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2 + i;
            i = sum / 10 ;
            int current = sum % 10;
            if (node == null) {
                head = node = new ListNode(current);
            } else {
                node.next = new ListNode(current);
                node = node.next;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (i > 0) {
            node.next = new ListNode(i);
        }
        return head;
    }

    public static void main(String[] args) {
        hot02 hot02 = new hot02();
        ListNode node1 = new ListNode(2);
        node1.next = new ListNode(4,new ListNode(3));
        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(6,new ListNode(4));
        ListNode node = hot02.addTwoNumbers2(node1, node2);
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
        }
    }

}
