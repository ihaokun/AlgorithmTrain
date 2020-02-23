package nowcoder.programming2019;

import java.util.Scanner;

/**
 * 访友——网易
 *
 * 题目描述：
 * 小易准备去拜访他的朋友，他的家在0点，但是他的朋友的家在x点(x > 0)，均在一条坐标轴上。小易每一次可以向前走1，2，3，4或者5步。
 * 问小易最少走多少次可以到达他的朋友的家。
 *
 * 输入描述：
 * 一行包含一个数字x(1 <= x <= 1000000)，代表朋友家的位置。
 *
 * 输出描述：
 * 一个整数，最少的步数。
 *
 * @author ihaokun
 * @date 2020/2/23 22:03
 */
public class VisitFriend {
  // one time pass
  public static void main(String[] args) {
    // input handle
    Scanner sc = new Scanner(System.in);
    int x = sc.nextInt();
    sc.close();
    //
    int result = 0;
    int[] strides = {1, 2, 3, 4, 5};  // stride：步长
    int idx = 4;
    while (x > 0){
      while (x < strides[idx] && idx >= 0) {
        idx--;
      }
      x -= strides[idx];
      result++;
    }
    System.out.println(result);
  }
}