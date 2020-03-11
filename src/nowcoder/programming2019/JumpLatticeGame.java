package nowcoder.programming2019;

import java.util.Scanner;

/**
 * 跳格子游戏——招商银行信用卡中心
 *
 * 题目描述：
 * 假设你正在玩跳格子（所有格子排成一个<b>纵列</b>）游戏。需要 跳完n 个格子你才能抵达终点。
 * 每次你可以跳 1 或 2 个格子。你有<i>多少种不同的方法</i>可以到达终点呢？
 * 注意：给定 n 是一个正整数。
 *
 * 输入描述：
 * 格子数n
 *
 * 输出描述：
 * 跳完n个格子到达终点的方法
 *
 * 知识点：
 * 动态规划
 *
 * @author ihaokun
 * @date 2020/3/7 23:19
 */
public class JumpLatticeGame {
  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.close();
    // solution
    int result = 0;   // sum
    if (n <= 2) System.out.println(n);
    else {
      int[] possibilities = new int[n];  // fibonacci
      possibilities[0] = 1;
      possibilities[1] = 2;
      // 这应该是一个斐波那契数列，因为只可能从前一个或前两个阶梯过来，所以跟动态规划没什么关系
      for (int i = 2; i < n; i++) {
        possibilities[i] = possibilities[i - 1] + possibilities[i - 2];
      }
      System.out.println(possibilities[n - 1]);
    }
  }
}