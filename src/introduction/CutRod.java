package introduction;

/**
 * 切割钢条问题
 *
 * @author ihaokun
 * @date 2019/11/6 11:35
 */
public class CutRod {
  public static void main(String[] args) {
    int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
    int n = 4;
    CutRod instance = new CutRod();
    System.out.println(instance.r(prices, n));
    System.out.println(instance.r1(prices, n));
    System.out.println(instance.dynamicProgramming(prices, n));
  }

  // 下面 两个方法 是自顶向下的递归实现（Top-Bottom Approach (Recursion)）
  // 均存在 反复求解相同子问题 的问题
  // 时间复杂度分析：n/2 * n -> O(n²)

  // 公式15.1的代码：rn = max(pn, r1+r(n-1), r2+r(n-2),...,r(n-1)+r1)
  // 实际实现的是：rn = max(pn, r1+r(n-1), r2+r(n-2),...,r(n/2)+r(n/2))
  private int r(int[] p, int n){
    if (n == 1) return p[0];
    int max = p[n - 1];
    for (int i = 1; i <= n / 2; i++) {
      int curr = r(p, n- i) + r(p, i);
      if (curr > max) max = curr;
    }
    return max;
  }
  // 公式15.2的代码：rn = max(pi + r(n-i))
  private int r1(int[] p, int n){
    if (n == 1) return p[0];
    int max = p[n - 1];
    for (int i = 1; i <= n / 2; i++) {
      // 是上一个解法的简化版lite，不需要两边都递归求和，只要一边递归+另一边的整值
      int curr = p[i - 1] + r1(p, n - i);
      // int curr = p[n - i - 1] + r1(p, i);
      if (curr > max) max = curr;
    }
    return max;
  }
  // 辅助数组|hash，额外内存
  private int[][] matrix;
  private int dynamicProgramming(int[] p, int n){
    matrix = new int[p.length][1];
    return topDownWithMemorized(p, n);
  }
  private int topDownWithMemorized(int[] p, int n) {
    if (n == 1) return p[0];
    int max = p[n - 1];
    for (int i = 1; i <= n/2; i++) {
      int curr = p[i - 1];
      if (matrix[n - i - 1][0] == 0) {
        matrix[n - i - 1][0] = topDownWithMemorized(p, n - i);
      }
      curr += matrix[n - i - 1][0];
      if (curr > max) max = curr;
    }
    return max;
  }
}
