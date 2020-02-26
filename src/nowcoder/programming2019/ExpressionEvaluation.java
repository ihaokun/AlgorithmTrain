package nowcoder.programming2019;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 表达式求值——网易
 *
 * 题目描述：
 * 今天上课，老师教了小易怎么计算加法和乘法，乘法的优先级大于加法，但是如果一个运算加了括号，那么它的优先级是最高的。
 * 现在小易希望你帮他计算给定3个数a，b，c，在它们中间添加"+"， "*"， "("， ")"符号，能够获得的最大值。
 *
 * 输入描述：
 * 一行三个数a，b，c (1 <= a, b, c <= 10)
 *
 * 输出描述：
 * 能够获得的最大值
 *
 * 知识点：
 *
 * @author ihaokun
 * @date 2020/2/25 22:29
 */
public class ExpressionEvaluation {
  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    // String s = sc.nextLine();
    int[] ints = new int[3];
    for (int i = 0; i < 3; i++) {
      ints[i] = sc.nextInt();
    }
    sc.close();
    // 这应该是一个组合(combination) + 规则 的问题
    // 1. 生成有效的排列
    // 2. 计算表达式
    foo(ints[0], ints[1],ints[2]);
    bar(ints);
  }
  private static void foo(int a, int b, int c){
    // 很难用程序模拟可能的有效排列，给定了数字个数，但没给符号的规定
    // 那么直接写出所有的排列
    // a + b + c parenthesis same same
    // a * b * c parenthesis same same
    // a + b * c parenthesis (a + b) * c same
    // a * b + c parenthesis same a * (b + c)
    int[] possibilities = new int[6];
    possibilities[0] = a + b + c;
    possibilities[1] = a * b * c;
    possibilities[2] = a + b * c;
    possibilities[3] = (a + b) * c;
    possibilities[4] = a * b + c;
    possibilities[5] = a * (b + c);
    Arrays.sort(possibilities);
    System.out.println(possibilities[5]);
  }
  private static void bar(int[] ints){
    // 看评论区一份C代码，考虑+和*计算的大小，以及括号位置的思路
    int result = Math.max(
        // 括号位置只有两种可能
      greater(greater(ints[0], ints[1]), ints[2]),
      greater(ints[0], greater(ints[1], ints[2]))
    );
    System.out.println(result);
  }
  private static int greater(int p, int q){
    // 乘法和加法的值的比较
    return Math.max(p * q, p + q);
  }
}