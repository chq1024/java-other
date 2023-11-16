package data_struct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author bk
 */
public class TreeSearchDemo {

    public TreeNode sort(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);
        List<Integer> arr = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            arr.add(poll.val);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
        arr = arr.stream().sorted().collect(Collectors.toList());
        TreeNode node = new TreeNode(arr.get(0));
        arr = arr.subList(1, arr.size());
        for (int i = 0; i < arr.size(); i++) {
            Integer r = arr.get(i);
            TreeNode temp = new TreeNode(r);
            if (r < node.val) {
                node.left = temp;
            } else {
                if (node.left != null && node.right == null) {
                    node.right = temp;
                } else {
                    temp.left = node;
                    node = temp;
                }
            }
        }
        return node;
    }

    public TreeNode search(TreeNode root,int value) {
        TreeNode curr= root;
        while (curr != null) {
            if (value < curr.val) {
                curr = curr.left;
            } else if (value > curr.val) {
                curr = curr.right;
            } else {
                break;
            }
        }
        return curr;
    }

    public void insert(TreeNode root,int value) {
        if (root == null) {
            root = new TreeNode(value);
        }
        TreeNode curr = root,pre = null;
        while (curr != null) {
            if (curr.val == value){
                break;
            }
            pre = curr;
            if (curr.val > value) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        TreeNode node = new TreeNode(value);
        if (pre.val > value) {
            pre.left = node;
        } else {
            pre.right = node;
        }
    }

    public void remove(TreeNode root,int value) {
        if (root == null) {
            return;
        }
        TreeNode curr = root,pre = null;
        while (curr != null) {
            if (curr.val == value) {
                break;
            }
            pre = curr;
            if (curr.val < value) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        if (curr == null) {
            return;
        }
        if (curr.left == null || curr.right == null) {
            TreeNode child = curr.left != null ? curr.left:curr.right;
            if (curr != root) {
                if (pre.left == curr) {
                    pre.left = child;
                } else {
                    pre.right = child;
                }
            } else {
                root = child;
            }
        } else {
            TreeNode temp = curr.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            // 递归删除节点 tmp
            remove(root,temp.val);
            // 用 tmp 覆盖 cur
            curr.val = temp.val;
        }
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
        TreeSearchDemo demo = new TreeSearchDemo();
        // 变成完全二叉树且是二叉搜索树
        TreeNode sort = demo.sort(root);
        // 查找
        TreeNode search = demo.search(sort, 4);
        System.out.println(search);
    }
}

class TreeNode {
    int val;         // 节点值
    TreeNode left;   // 左子节点引用
    TreeNode right;  // 右子节点引用
    TreeNode(int x) { val = x; }
}
