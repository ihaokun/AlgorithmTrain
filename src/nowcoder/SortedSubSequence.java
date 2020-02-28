package nowcoder;

import java.util.Scanner;

/**
 * 2020-2 笔试题
 * 排序子序列
 * LRU算法、哈夫曼编码（树）、图（深度、广度优先算法）
 *
 * 题目描述：
 *
 * 输入描述：
 *
 * 输出描述：
 *
 * 知识点：
 *
 * @author ihaokun
 * @date 2020/2/27 19:27
 */
public class SortedSubSequence {
  // case通过率为80，后续看看哪里有点问题
  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] ints = new int[n];
    for (int i = 0; i < n; i++) {
      ints[i] = sc.nextInt();
    }
    sc.close();
    //
    int result = 0;
    for (int i = 0; i < n - 1; ) {
      boolean tmp = ints[i] >= ints[++i];
      while (i < n - 1 && tmp == ints[i] >= ints[++i]);
      result++;
    }
    if (n >= 3 && ints[n - 3] >= ints[n - 2] != ints[n - 2] >= ints[n - 1]) result++;
    System.out.println(result);
  }
}