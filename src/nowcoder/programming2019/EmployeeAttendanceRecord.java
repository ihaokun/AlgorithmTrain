package nowcoder.programming2019;

import java.util.Scanner;

/**
 * 员工考勤记录——招商银行
 *
 * 题目描述：
 * 给定一个字符串来代表一个员工的考勤纪录，这个纪录仅包含以下两个字符：
 * 'A' : Absent，缺勤
 * 'P' : Present，到场
 * 如果一个员工的考勤纪录中不超过两个'A'(缺勤),那么这个员工会被奖赏。
 *
 * 如果你作为一个员工，想在连续N天的考勤周期中获得奖赏，请问有多少种考勤的组合能够满足要求
 *
 * 输入描述:
 * 考勤周期的天数N（正整数）
 *
 * 输出描述:
 * 这N天里能获得奖赏的考勤组合数
 *
 * @author ihaokun
 * @date 2020/2/22 16:34
 */
public class EmployeeAttendanceRecord {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.close();
    // algorithm implement
    // 和上题一样，是排列组合的问题（或者说插入问题更加合适，用到的是排列组合的知识点）
    int count = 1 + n;
    System.out.println(count + combination(n, 2));
    System.out.println(1 + n + n * (n - 1) / 2);  // 因为抽取的必然是2的原因，可以直接写成这样，即为C(n, 0) + C(n, 1) + C(n, 2)
  }

  // P(n, m) = n * (n - 1) * ... * (n - m + 1) = n! / (n - m)!
  private static int permutation(int n, int m){
    return factorial(n) / factorial(n - m);
    // 也可以不使用这个阶乘除法，直接手写循环乘法
  }
  // P(n, m) = C(n, m) * P(m, m) => C(n, m) = P(n, m) / P(m, m)
  // n! / (n - m)! / m! = n! / ((n - m)! * m!)，分子分母消去公因数(n - m)!
  private static int combination(int n, int m){
    return permutation(n, m) / permutation(m, m);
    // 公式最后推导的结果应该是：C(n, r) = n * ... * (n - r + 1) / r!
  }

  // 阶乘方法，数学符号是'!'
  private static int factorial(int n){
    if (n == 0) return 1;   //CRUX 注意。0的阶乘是1
    int result = n;
    while (--n > 1)
      result *= n;
    return result;
  }
}