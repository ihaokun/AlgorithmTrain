package nowcoder.jianzhi.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 剑指offer - 序列化二叉树
 *
 * <p>题目描述：
 *
 * <p>请实现两个函数，分别用来序列化和反序列化二叉树
 *
 * <p>二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。
 * 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！表示一个结点值的结束（value!）。
 *
 * <p>二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 *
 * <p>知识点：树、队列
 *
 * <p>小结：序列化不是问题，反序列化困扰了我一阵，但后来还是有了解决方案（和序列化一样的顺序，还是使用递归，不过要用到队列，每次将队头的元素出队）
 *
 * @author ihaokun
 * @date 2019/10/6 19:07
 */
public class BinaryTreeSerialize {
  public static void main(String[] args) {
    // Initialize
    TreeNode root = new TreeNode(0);
    TreeNode left = new TreeNode(1);
    TreeNode right = new TreeNode(2);
    root.left = left;
    root.right = right;
    left.left = new TreeNode(3);
    left.right = new TreeNode(4);
    left.left.left = new TreeNode(7);
    left.left.right = new TreeNode(8);
    right.left = new TreeNode(5);
    right.right = new TreeNode(6);
    // test
    BinaryTreeSerialize instance = new BinaryTreeSerialize();
    String serializeSeq = instance.serialize(root);
    System.out.println(serializeSeq);
    TreeNode deserializeRoot = instance.deserialize(serializeSeq);
    instance.printTreePreOrder(deserializeRoot);
  }

  private final String NULL_STR = "#";
  private final String END_STR = "!";

  private String serialize(TreeNode root) {
    // 题目没说使用那种遍历方式，就先用 Pre Order试试吧
    StringBuilder builder = new StringBuilder();
    if (root != null) {
      builder.append(root.val);
      builder.append(END_STR);
      builder.append(serialize(root.left));
      builder.append(serialize(root.right));
    } else {
      builder.append(NULL_STR);
      builder.append(END_STR);
    }
    return builder.toString();
  }

  private TreeNode deserialize(String str) {
    String[] split = str.split(END_STR);
    if (str.equals("#!")) return null;
    // XXX should use Queue(convert Array to Collection by Stream JDK 8)
    Queue<String> queue = Arrays.stream(split).collect(Collectors.toCollection(LinkedList::new));
    return deserialize(queue);
  }

  private TreeNode deserialize(Queue<String> queue) {
    if (!queue.isEmpty()) {
      String head = queue.remove();
      if (!head.equals(NULL_STR)) {
        TreeNode root = new TreeNode(Integer.parseInt(head));
        root.left = deserialize(queue);
        root.right = deserialize(queue);
        return root;
      }
    }
    return null;
  }

  private void printTreePreOrder(TreeNode root) {
    if (root != null) {
      System.out.println(root.val);
      printTreePreOrder(root.left);
      printTreePreOrder(root.right);
    }
  }
}
