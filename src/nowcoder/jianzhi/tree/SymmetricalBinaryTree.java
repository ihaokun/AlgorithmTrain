package nowcoder.jianzhi.tree;

/**
 *
 *
 * <pre>
 *     剑指offer - 对称的二叉树
 *
 *     题目描述：
 *          请实现一个函数，用来判断一颗二叉树是不是对称的。
 *          注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 *
 *     知识点：
 *          二叉树
 *     考点：
 *          二叉树
 *
 *     关联（Association）：
 *          看到题目的描述，就有一种熟悉的感觉，确实是做过相关的题目，之前 剑指offer有一道 二叉树的镜像 一题
 *          但当时写的时候没有在该 git repository 中记录，写完这个再补上之前做过的
 *     解法：
 *        这边自己提供两种解法
 *        1. 按照题意，构建一个原二叉树的镜像树，然后两个二叉树一一比对
 *        2. 使用 中序遍历，左-根-右，左右子树的顺序是相反的，这样可以通过遍历结果来验证是否对称
 *        还有一种，是网上看到的解法，使用 递归 解题，精简正确，同时它给出了另外两种非递归解法，分别是BFS和DFS
 *
 *        我的两种解法，在本地IDE都通过了，但web端有问题，暂时不考虑这个问题，个人认为应该是web端的问题
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/30 22:14
 */
public class SymmetricalBinaryTree {
  private static class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(5);
    TreeNode left = new TreeNode(3);
    TreeNode right = new TreeNode(3);
    root.left = left;
    root.right = right;
    TreeNode node1 = new TreeNode(4);
    TreeNode node4 = new TreeNode(4);
    left.left = node1;
    right.right = node4;
    TreeNode node2 = new TreeNode(2);
    TreeNode node5 = new TreeNode(2);
    node1.left = node2;
    node4.right = node5;
    TreeNode node3 = new TreeNode(1);
    TreeNode node6 = new TreeNode(1);
    node2.left = node3;
    node5.right = node6;

    // test
    SymmetricalBinaryTree foo = new SymmetricalBinaryTree();
    System.out.println(foo.isSymmetrical(root));
    System.out.println(foo.isSymmetrical1(root));
  }

  private boolean isSymmetrical1(TreeNode pRoot) {
    inOrder(pRoot);
    int length = inOrderSeq.length();
    String left = inOrderSeq.substring(0, length / 2);
    String right = inOrderSeq.reverse().substring(0, length / 2);
    return left.equals(right);
  }

  private static StringBuilder inOrderSeq = new StringBuilder();

  private static void inOrder(TreeNode root) {
    if (root != null) {
      inOrder(root.left);
      inOrderSeq.append(root.val);
      inOrder(root.right);
    }
  }

  private boolean isSymmetrical(TreeNode pRoot) {
    TreeNode mirrorNode = mirrorTree(pRoot);
    preOrderCompare(pRoot, mirrorNode);
    return flag;
  }

  /**
   * Build a New Mirror Tree
   *
   * @param root
   * @return
   */
  private static TreeNode mirrorTree(TreeNode root) {
    // 所谓Mirror，即为左右互换
    TreeNode node = null;
    if (root != null) {
      node = new TreeNode(root.val);
      node.left = mirrorTree(root.right);
      node.right = mirrorTree(root.left);
    }
    return node;
  }

  private static boolean flag = true;

  private static void preOrderCompare(TreeNode root1, TreeNode root2) {
    if (root1 != null && root2 != null) {
      if (root1.val != root2.val) flag = false;
      preOrderCompare(root1.left, root2.left);
      preOrderCompare(root1.right, root2.right);
    }
  }
}
