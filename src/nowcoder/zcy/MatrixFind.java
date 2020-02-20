package nowcoder.zcy;

import java.util.Scanner;

/**
 * 在行列都拍好序的矩阵中找指定的数
 *
 * 题目描述：
 * 给定一个N×M的整形矩阵matrix和一个整数K, matrix的每一行和每一列都是排好序的。
 * 实现一个函数，判断K是否在matrix中
 * [要求]
 * 时间复杂度为O(N+M)，额外空间复杂度为O(1)。
 *
 * 输入描述:
 * 第一行有三个整数N, M, K
 * 接下来N行，每行M个整数为输入的矩阵
 *
 * 输出描述:
 * 若K存在于矩阵中输出"Yes"，否则输出"No"
 *
 * 考点：数学计算
 *
 * @author ihaokun
 * @date 2020/2/20 16:21
 */
public class MatrixFind {
  public static void main(String[] args) {
    // input & init
    // 没有对不正确的输入进行处理
    Scanner sc = new Scanner(System.in);
    String[] split = sc.nextLine().split(" ");
    int n = split[0].charAt(0) - '0';
    int m = split[1].charAt(0) - '0';
    int k = split[2].charAt(0) - '0';
    int[][] matrix = new int[n][m];
    for (int i = 0; i < n; i++) {
      String[] line = sc.nextLine().split(" ");
      for (int j = 0; j < m; j++)
        matrix[i][j] = line[j].charAt(0) - '0';
    }
    sc.close();
    // searching
    System.out.println(searching(matrix, n, m, k) ? "Yes" : "No");
  }

  // case通过率50%，查找方法没写错，前面的输入初始化写的有问题
  static boolean searching(int[][] sortedMatrix, int rows, int cols, int target) {
    // left point useless, should use right point
    int y = -1; // y轴坐标
    for (int i = 0; i < rows; i++) {
      if (sortedMatrix[i][cols - 1] == target) return true;
      if (sortedMatrix[i][cols - 1] > target) {
        y = i;
        break;
      }
    }
    if (y != -1)
      for (int i = 0; i < cols - 1; i++)
        if (sortedMatrix[y][i] == target) return true;
    return false;
  }
}