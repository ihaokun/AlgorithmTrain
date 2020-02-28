package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 训练部队
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
 * @date 2020/2/27 20:08
 */
public class TrainTroops {
  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[][] matrix = new int[n][2];
    for (int i = 0; i < n; i++) {
      matrix[i][0] = sc.nextInt();
      matrix[i][1] = sc.nextInt();
    }
    sc.close();
    //
    Arrays.sort(matrix);

  }
}