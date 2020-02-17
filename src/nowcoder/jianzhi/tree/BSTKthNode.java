package nowcoder.jianzhi.tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 剑指offer - 二叉搜索树的第K个节点
 *
 * <p>题目描述：给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8） 中，按结点数值大小顺序第三小结点的值为4。
 *
 * <p>知识点：栈、树
 *
 * @author ihaokun
 * @date 2019/10/8 0:27
 */
public class BSTKthNode {
  public static void main(String[] args) {
    // Initialize
    TreeNode root = new TreeNode(8);
    TreeNode left = new TreeNode(6);
    TreeNode right = new TreeNode(10);
    root.left = left;
    left.left = new TreeNode(5);
    left.right = new TreeNode(7);
    root.right = right;
    right.left = new TreeNode(9);
    right.right = new TreeNode(11);
    // Test
    BSTKthNode obj = new BSTKthNode();
    // Solution 1
    /*TreeNode x = obj.KthNode(root, 8);
    System.out.println(x != null ? x.val : null);*/
    // Solution 2
    /*System.out.println(obj.kThNode(root, 1));
    for (TreeNode treeNode : arr) {
      System.out.println(treeNode.val);
    }*/
    // Solution 3
    // System.out.println(obj.kThNode1(root, 1).val);
    // Test PreOrder
    obj.preOrderTraversal(root);
    obj.mockPreOrderByStack(root);
    for (TreeNode treeNode : arr) {
      System.out.println(treeNode.val);
    }
  }

  // 第一个思路：使用中序遍历得到数组，再取数组的第k个元素
  private TreeNode KthNode(TreeNode pRoot, int k) {
    // 根据 二叉搜索树BST的特性，要找 第K 小的节点，可以使用 中序 In Order 遍历
    inOrderTraversal(pRoot);
    if (k <= 0 || k > arr.size()) return null;
    return arr.get(k - 1);
  }

  private static ArrayList<TreeNode> arr = new ArrayList<>();

  private static void inOrderTraversal(TreeNode root) {
    if (root != null) {
      inOrderTraversal(root.left);
      arr.add(root);
      inOrderTraversal(root.right);
    }
  }

  // TODO 使用InOrder得到数组，再取第K个节点，是一种方法；但应该还可以在递归过程中直接得到第K个节点，后面再写一下
  // TODO 又或者可以 考虑一下使用 题目提示的 栈，用栈模拟递归

  // 思路二：使用 栈 模拟 递归，同样可得到结果
  private TreeNode kThNode(TreeNode pRoot, int k) {
    // 用 stack 模拟 recursive
    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = pRoot;
    while (node != null || !stack.isEmpty()) {
      // push stack
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
      // pop stack
      TreeNode top = stack.pop();
      if (--k == 0) return top;
      node = top.right;
    }
    return null;
  }

  private void preOrderTraversal(TreeNode root) {
    if (root != null) {
      System.out.println(root.val);
      preOrderTraversal(root.left);
      preOrderTraversal(root.right);
    }
  }

  // 拓展：尝试使用 stack 模拟 先序遍历
  private void mockPreOrderByStack(TreeNode root) {
    // Init Stack
    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = root;
    while (node != null || !stack.isEmpty()) {
      if (node != null) {
        stack.push(node);
        arr.add(node);
        node = node.left;
      }
      // push stack
      while (node != null) {
        stack.push(node);
        arr.add(node);
        node = node.left;
      }
      // pop stack
      TreeNode top = stack.pop();
      node = top.right;
    }
  }

  // 解法三，不太理解，两个if的return
  int index = 0;

  private TreeNode kThNode1(TreeNode root, int k) {
    if (root != null) {
      TreeNode node = kThNode1(root.left, k);
      //
      if (node != null) return node;
      if (++index == k) return root;
      node = kThNode1(root.right, k);
      if (node != null) return node;
    }
    return null;
  }
}
