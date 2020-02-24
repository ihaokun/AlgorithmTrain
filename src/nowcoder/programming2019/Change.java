package nowcoder.programming2019;

import java.util.Scanner;

/**
 * 找零——字节跳动
 *
 * 题目描述：
 * Z国的货币系统包含面值1元、4元、16元、64元共计4种硬币，以及面值1024元的纸币。
 * 现在小Y使用1024元的纸币购买了一件价值为N(0<N≤1024)的商品，请问最少他会收到多少硬币？
 *
 * 输入描述：
 * 一行，包含一个数N。
 *
 * 输出描述：
 * 一行，包含一个数，表示<u>最少</u>收到的硬币数。
 *
 * @author ihaokun
 * @date 2020/2/23 23:07
 * @see VisitFriend "'访友'，相似题目"
 */
public class Change {
  public static void main(String[] args) {
    // change：改变；零钱（找零）
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.close();
    // 和前一道访友的题目有些相似， 解法完全一样哈
    int result = 0;   // count
    int[] coins = {1, 4, 16, 64};
    int idx = 3;
    int balance = 1024 - n;
    while (balance > 0){
      while (idx >= 0 && balance < coins[idx]){
        idx--;
      }
      balance -= coins[idx];
      result++;
    }
    System.out.println(result);
  }
}