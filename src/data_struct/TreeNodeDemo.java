package data_struct;


import java.util.*;

/**
 * @author bk
 */
public class TreeNodeDemo {

    public List<TreeNode> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<TreeNode> nodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            nodes.add(node);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return nodes;
    }


    public void preOrder(TreeNode node,List<Integer> arr) {
        if (arr == null) {
            arr = new ArrayList<>();
        }
        if (node == null) {
            return;
        }
        arr.add(node.val);
        preOrder(node.left,arr);
        preOrder(node.right,arr);
    }

    public void inOrder(TreeNode node,List<Integer> arr) {
        if (arr == null) {
            arr = new ArrayList<>();
        }
        if (node == null) {
            return;
        }
        inOrder(node.left,arr);
        arr.add(node.val);
        inOrder(node.right,arr);
    }

    public void postOrder(TreeNode node,List<Integer> arr) {
        if (arr == null) {
            arr = new ArrayList<>();
        }
        if (node == null) {
            return;
        }
        postOrder(node.left,arr);
        postOrder(node.right,arr);
        arr.add(node.val);
    }



    public static class TreeNode {
        int val;         // 节点值
        TreeNode left;   // 左子节点引用
        TreeNode right;  // 右子节点引用
        TreeNode(int x) { val = x; }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(6);
        TreeNode n6 = new TreeNode(7);
        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;

        TreeNodeDemo demo = new TreeNodeDemo();
        List<Integer> arr = new ArrayList<>();
        demo.preOrder(root,arr);
//        demo.inOrder(root,arr);
        System.out.println(arr);
    }
}
