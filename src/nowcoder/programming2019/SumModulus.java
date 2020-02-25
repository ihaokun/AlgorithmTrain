package nowcoder.programming2019;

import java.util.Scanner;

/**
 * 模数求和——拼多多
 *
 * 题目描述：
 * 现给定n个整数，并定义一个非负整数m，且令f(m) = (m%a1)+(m%a2)+...+(m%an)。
 * 此处的X % Y的结果为X除以Y的余数。
 * 现请你<i>找出一个m</i>，求出f(m)的最大值。
 *
 * 输入描述：
 * 输入包含两行，第一行为一正整数n，(1<n<=3000)
 * 第二行为n个整数a1,a2,...,an ，其中(2<=ai<=10^5)
 *
 * 输出描述：
 * 输出仅包含一行，输出f(m)的最大值
 *
 * 知识点：数学（最小公倍数、最大公约数（辗转相除法）、素因数分解）
 *
 * @author ihaokun
 * @date 2020/2/24 20:11
 * @see company.baidu.GcdAndLcm 以前做的一道笔试题，有关GCD & LCM
 * @see SumModulusAccepted 实际提交代码
 */
public class SumModulus {
  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    long[] longs = new long[n];
    for (int i = 0; i < n; i++) {
      longs[i] = sc.nextLong();
    }
    sc.close();
    // 下面写一下推理过程：
    // 要使得 f(m) 的值最大，那么m mod a1, m mod a2, ... 这些的值应该分别为 a1 - 1, a2 - 1, ...
    // -> 也即 a1 * x + a1 - 1 = a2 * y + a2 - 1 = ...
    // -> a1 * (x + 1) = a2 * (y + 1) = ...
    // 那么就是求 {a1, a2, ...}的最小公倍数(LCM(Least Common Multiple)) NOTE 关键处
    // result = LCM(int[] arr) - 1
    long dividend = lcm(longs) - 1;     //NOTE 我的写法是没有问题的，只是给的用例的值太大，超出long的正数范围，无法给出正确答案
    // output                             NOtE 实际上是可以不求最小公倍数的，直接在输入部分计算就可以了，解题是这么写的；而IDE的代码多了求解多个整数的LCM的过程
    int result = 0;
    for (long i : longs) {
      result += dividend % i;
    }
    System.out.println(result);
  }

  /**
   * <b>递归</b>计算多个整数的最小公倍数
   * LCM({a1, a2, ..., an}) = LCM(LCM{a1, a2, ..., an-1}, an)
   *
   * @param arr
   * @return
   */
  private static long lcm(long[] arr){
    int len = arr.length;
    if (len == 2)
      return lcm(arr[0], arr[1]);
    long[] subArr = new long[len - 2];
    System.arraycopy(arr, 0, subArr, 0, subArr.length);
    return lcm(lcm(subArr), lcm(arr[len - 2], arr[len - 1]));
  }

  /**
   * 根据{@link #gcd(int, int)}来计算LCM
   *
   * @param p
   * @param q
   * @return LCM(Least Common Divisor)
   */
  private static long lcm(long p, long q){
    return p * q / gcd(p, q);
  }

  /**
   * 这个根据推导出来后，就可以参考以前写过的例子了
   *
   * @param p 非负整数1
   * @param q 非负整数2
   * @return GCD(Greatest Common Divisor)
   * @see algorithms.chapter1fundamentals.Euclid#gcdRecursive(int, int) 之前书上的写法
   */
  private static long gcd(long p, long q){
    long remainder = p % q;
    return remainder == 0 ? q : gcd(q, remainder);
  }
}