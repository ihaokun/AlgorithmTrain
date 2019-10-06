package jianzhi.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指offer - 把二叉树打印成多行
 *
 * <p>题目描述： 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 *
 * <p>知识点： 队列、树
 *
 * <p>关联： 和 之前的两道 二叉树打印的题目是相关的，很相似，可参见{@link PrintBinaryTreeFromTopToBottom} 和 {@link
 * PrintBinaryTreeLevelOrder}， 都是使用 Level Order Traversal
 *
 * @author ihaokun
 * @date 2019/10/6 17:44
 * @see PrintBinaryTreeLevelOrder 相关题1，第二个做到的
 * @see PrintBinaryTreeFromTopToBottom 相关题2，最早的
 */
public class PrintBinaryTreeMultiLine {
  public static void main(String[] args) {
    // Initialize
    TreeNode root = new TreeNode(0);
    TreeNode left = new TreeNode(1);
    TreeNode right = new TreeNode(2);
    root.left = left;
    root.right = right;
    left.left = new TreeNode(3);
    left.right = new TreeNode(4);
    right.left = new TreeNode(5);
    right.right = new TreeNode(6);
    // test
    System.out.println(new PrintBinaryTreeMultiLine().print(root));
  }

  private ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
    // 同样，使用层序遍历
    ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
    Queue<TreeNode> nodes = new LinkedList<>();
    if (pRoot != null) nodes.add(pRoot);
    while (!nodes.isEmpty()) {
      arrayLists.add(levelOrderTraversal(nodes));
    }
    return arrayLists;
  }

  private static ArrayList<Integer> levelOrderTraversal(Queue<TreeNode> queue) {
    ArrayList<Integer> integerList = new ArrayList<>();
    if (queue.isEmpty()) return null;
    for (TreeNode treeNode : queue.toArray(new TreeNode[0])) {
      queue.remove();
      integerList.add(treeNode.val);
      if (treeNode.left != null) queue.add(treeNode.left);
      if (treeNode.right != null) queue.add(treeNode.right);
    }
    return integerList;
  }
}
