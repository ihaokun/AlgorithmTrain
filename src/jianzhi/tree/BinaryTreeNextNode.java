package jianzhi.tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * <pre>
 * 剑指offer - 二叉树的下一个节点
 *
 * 题目描述：
 *      给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 *      注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 *
 * 考点：
 *      二叉树、中序遍历
 * 知识点：
 *      二叉树
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/27 22:59
 */
public class BinaryTreeNextNode {
  private static class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    /** 注意：根据题目给的条件，该next变量，实际指的应该是当前节点的父节点 该命名并不合适，使用super更恰当 */
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
      this.val = val;
    }
  }

  public static void main(String[] args) {
    // init
    TreeLinkNode root = new TreeLinkNode(0);
    TreeLinkNode noode1 = new TreeLinkNode(1);
    root.left = noode1;
    root.next = null;
    root.right = null;
    // test
    BinaryTreeNextNode foo = new BinaryTreeNextNode();
    System.out.println(foo.nextNode1(root));
  }

  public TreeLinkNode nextNode(TreeLinkNode pNode) {
    //
    //         super             or            super
    //     N                                           N
    //  Left right                                 left  right
    //     left1 right1
    //
    /*
    如上 示意图，节点的两种情况，分别为父节点在左或右
    若right存在，那么无论左右，下一节点都是右子树的最左端叶子节点
    若right不存在，则分为两种情况
       左半边情况，可得到下一节点为super
       右半边情况，则需要继续向上寻找父节点，直到得到父节点是位于左半边
    */
    // 1. 当前节点的右节点存在
    if (pNode.right != null) {
      TreeLinkNode right = pNode.right;
      while (right.left != null) right = right.left;
      return right;
    }
    // 2. 当前节点的右节点不存在；当前节点 属于 上级节点的左节点
    if (pNode.next != null && pNode == pNode.next.left) {
      return pNode.next;
    }
    // 3. 当前节点的右节点不存在；当前节点 属于 上级节点的右节点
    if (pNode.next != null && pNode == pNode.next.right) {
      TreeLinkNode preNode = pNode.next;
      while (preNode.next != null && preNode == preNode.next.right) {
        preNode = preNode.next;
      }
      return preNode.next;
    }
    return null;
  }

  public TreeLinkNode nextNode1(TreeLinkNode pNode) {
    // 解法二，采用根据当前节点，得到根节点的方式（从而推得当前节点按照中序遍历InOrder Traversal，得到的下一个节点）
    TreeLinkNode bar = pNode;
    while (pNode.next != null) pNode = pNode.next;
    inOrderTraversal(pNode);
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) == bar) return i + 1 < list.size() ? list.get(i + 1) : null;
    }
    return null;
  }

  static List<TreeLinkNode> list = new ArrayList<>();

  public void inOrderTraversal(TreeLinkNode root) {
    if (root != null) {
      inOrderTraversal(root.left);
      list.add(root);
      System.out.println(root.val);
      inOrderTraversal(root.right);
    }
  }
}
