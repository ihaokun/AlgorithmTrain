package jianzhi;

/**
 * 剑指offer - 剪绳子
 *
 * <p>题目描述：给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * <p>考点：动态规划；知识点：贪心算法</p>
 *
 * @author ihaokun
 * @date 2019/10/15 22:50
 */
public class CutRope {
  public static void main(String[] args) {
    // Initialization
    int n = 8; // 长度
    // int m = 3; // 段数
    // int[] arr = new int[m];
    // Test Case（n=8，m=3，maxProduct=3*3*2=18）
    CutRope rope = new CutRope();
    System.out.println("rope.cutRope(n) = " + rope.cutRope(n));
  }

  /**
   * <p>得到了一个思路，<em>5</em>是关键数字
   * 该解法涉及到了数学知识，运用了证明
   * <p>证明过程：
   * 当 N >= 5 时，3 * (N - 3) >= 2 * (N - 2) > 5
   *
   * <p>可得到结论：尽量减 长为3 的绳子
   *
   * @param target target > 1
   * @return 最大乘积
   * @see <a href="https://www.acwing.com/solution/acwing/content/731/">启发思路</a>
   * @see <a href="https://www.cnblogs.com/shiganquan/p/9289984.html">启发思路2</a>
   */
  private int cutRope(int target) {
    // 这题一开始是没思路的，看了看评论，大致是有两种解法：动态规划 和 贪心算法；本解法采用的是 贪心算法
    // 两种都听说过，但都不会写；这里根据数学证明，使用了一种 联机on-line算法O(N)
    if (target == 2) return 1;
    // 乘积
    int product = 1;
    while (target > 1){
      if (target >= 5){
        product *= 3;
        target -= 3;
      } else {
        product *= 2;
        target -= 2;
      }
    }
    return product;
  }
}
