package nowcoder.programming2019;

import java.util.Scanner;

/**
 * 数字序列第n位的值——快手
 *
 * 题目描述：
 * 有一个无限长的数字序列1，2，2，3，3，3，4，4，4，4，5，5，5，5，5。。。（数字序列从1开始递增，且数字k在该序列中正好出现k次），求第n项是多少
 *
 * 输入描述：
 * 输入为一个整数n
 *
 * 输出描述：
 * 输出一个整数，即第n项的值
 *
 * 知识点：查找(searching)、数学
 *
 * @author ihaokun
 * @date 2020/2/27 17:43
 */
public class NumberSequence {
  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.close();
    // 1 + 2 + 3 + ... + x = n
    // (x + 1) * x / 2 = n
    // x =
    double result = 1;
    double count = result;
    while (count < n){
      count = (++result + 1) * result / 2;
    }
    System.out.println((int)result);
  }
}