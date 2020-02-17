package nowcoder.jianzhi;

/**
 * 剑指offer - 机器人的运动范围
 *
 * <p>题目描述：地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 *
 * <p>考点：回溯法；知识点：数组
 *
 * <p>解题思路：使用 递归 + hashing，和上一题很相似，上下左右移动可以用递归来模拟，基准情况可用hashing来判断
 *
 * @author ihaokun
 * @date 2019/10/14 8:50
 */
public class MovingCount {
  public static void main(String[] args) {
    // 看看题目描述，和上一题很类似
    // Initialization
    MovingCount movingCount = new MovingCount();
    // Test
    System.out.println(movingCount.movingCount(18, 35, 40));
  }

  private int movingCount(int threshold, int rows, int cols) {
    int[][] matrix = new int[rows][cols];
    moving(threshold, rows, cols, 0, 0, matrix);
    int count = 0;
    for (int[] ints : matrix) {
      for (int i : ints) {
        if (i == 1) count++;
      }
    }
    return count;
  }

  private void moving(int threshold, int rows, int cols, int rowIdx, int colIdx, int[][] matrix) {
    // 递归穷举方法
    if (rowIdx >= 0 && rowIdx < rows && colIdx >= 0 && colIdx < cols && matrix[rowIdx][colIdx] == 0 && (digitalSum(rowIdx) + digitalSum(colIdx)) <= threshold) {
        System.out.println(rowIdx + "," + colIdx);
        matrix[rowIdx][colIdx] = 1;
        moving(threshold, rows, cols, rowIdx - 1, colIdx, matrix);
        moving(threshold, rows, cols, rowIdx + 1, colIdx, matrix);
        moving(threshold, rows, cols, rowIdx, colIdx - 1, matrix);
        moving(threshold, rows, cols, rowIdx, colIdx + 1, matrix);
    }
  }

  private int digitalSum(int num){
    // 计算 数位和
    int sum = 0;
    while (num > 0){
      sum += (num % 10);
      num /= 10;
    }
    return sum;
  }
}
