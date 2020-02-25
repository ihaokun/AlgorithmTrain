package nowcoder.programming2019;

import java.util.Scanner;

/**
 * 非递减序列——快手
 *
 * 题目描述：
 * 对于一个长度为n的整数序列，你需要检查这个序列是否可以是非递减序列，假如你最多可以改变其中的一个数。
 * 非递减序列的定义是：array[i]<=array[i+1], for 1<=i<n;
 *
 * 输入描述：
 * 输入是一个长度为n的整数序列。
 *
 * 输出描述：
 * 输出为； 是为1； 否为0
 *
 * 知识点：排序、数组、穷举
 *
 * @author ihaokun
 * @date 2020/2/24 17:40
 */
public class NonDecrementSequence {
  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();
    sc.close();
    //
    int count = 0;
    String[] split = s.split(" ");
    for (int i = 0; i < split.length - 1; i++) {
      if (count > 1) break;
      if (Integer.parseInt(split[i]) > Integer.parseInt(split[i + 1])) count++;
    }
    // output
    System.out.println(count > 1 ? 0 : 1);
  }
}