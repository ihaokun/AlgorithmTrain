package jianzhi.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指offer - 按 之 字形顺序打印二叉树
 *
 * <p>题目描述： 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 *
 * <p>知识点：栈、树
 *
 * <p>相较于{@link PrintBinaryTreeFromTopToBottom}这个之前的方法，本类的方法更好一些，使用了队列而不是多个list，空间更节约了；
 * 而且这也更加符合书中对层序遍历的描述，不需要递归，而是使用 循环+队列，不像其它三种遍历方式是 递归+栈
 *
 * @author ihaokun
 * @date 2019/10/5 16:56
 * @see PrintBinaryTreeFromTopToBottom "关联题"
 */
public class PrintBinaryTreeLevelOrder {

  public static void main(String[] args) {
    // init
    TreeNode root = new TreeNode(0);
    TreeNode left = new TreeNode(1);
    TreeNode right = new TreeNode(2);
    root.left = left;
    root.right = right;
    left.left = new TreeNode(3);
    left.right = new TreeNode(4);
    right.left = new TreeNode(5);
    right.right = new TreeNode(6);

    PrintBinaryTreeLevelOrder instance = new PrintBinaryTreeLevelOrder();
    System.out.println(instance.print(root));
  }

  private ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
    ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    if (pRoot == null) return null;
    // 解题思路，可以使用 层序遍历(Level Order Traversal)，
    // 和其它三种遍历不同，层序遍历使用的不是 Stack，而是 Queue
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(pRoot);
    int level = 1;
    while (!queue.isEmpty()) {
      ArrayList<Integer> integers = levelOrderTraversal(queue);
      if (level % 2 == 0) {
        // for (int i = integers.size() - 1; i >= 0; i--) {
        //   System.out.println(integers.get(i));
        // }
        //XXX 注意Collections这个工具类的使用，其和Arrays对于数组的作用差不多，是对于集合的工具类
        Collections.reverse(integers);
        arr.add(integers);
      } else {
        // for (Integer integer : integers) {
        //   System.out.println(integer);
        // }
        arr.add(integers);
      }
      level++;
    }
    return arr;
  }

  private static ArrayList<Integer> levelOrderTraversal(Queue<TreeNode> queue) {
    // 当前层 所有节点 值表
    ArrayList<Integer> arr = new ArrayList<>();
    if (!queue.isEmpty()) {
      //XXX 注意：需copy一个数组用于遍历，避免因 循环过程中对队列进行修改，导致抛出异常(xxx 对应Arrays.asList()方法，Collection也有实例方法toArray())
      TreeNode[] treeNodes = queue.toArray(new TreeNode[0]);
      for (TreeNode item : treeNodes) {
        arr.add(item.val);
        // 移除 队列head的元素，向tail加入元素
        queue.remove();
        if (item.left != null) {
          queue.add(item.left);
        }
        if (item.right != null) {
          queue.add(item.right);
        }
      }
    }
    return arr;
  }
}
