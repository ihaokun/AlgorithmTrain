package company;

/**
 * 计算二叉树最大路径
 *
 * @author ihaokun
 * @date 2019/10/17 10:13
 */
public class BinaryTreeMaxPath {
  public static void main(String[] args) {
    // 先序数组
    int[] preArr = {1,2,1};
    // 和之前做的有些不同，之前的树都是链表的形式，用数组的话没做过
    // 题目中，路径是指一条从树中任意节点出发，到达任意节点的序列。该路径至少包含一个节点，且不一定经过根节点
    System.out.println();
    // 题目随便sout了一个6还OC了。。。
    System.out.println("Byte.MAX_VALUE = " + Byte.MAX_VALUE);
    System.out.println("Short.MAX_VALUE = " + Short.MAX_VALUE);
    System.out.println("Character.BYTES = " + Character.BYTES);
    System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
    System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
    System.out.println(1000000007 < 2147483647);
  }
}
