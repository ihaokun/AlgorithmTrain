package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 组队竞赛
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
 * @date 2020/2/27 19:46
 * @see <a href="https://www.nowcoder.com/discuss/371985?type=all&order=time&pos=&page=1">reference site</a>
 */
public class TeamCompetition {
  // case通过率60
  // 这题应该是中位数+组合
  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] ints = new int[3 * n];
    for (int i = 0; i < 3 * n; i++) {
      ints[i] = sc.nextInt();
    }
    sc.close();
    //
    // 5 2 8 5 1 5 -> 8 5 5 5 2 1
    // comb: 851 552
    int result = 0;     //FIXME 看了看参考答案的C++代码，基本步骤都一样，就是这个结果类型是long long，应该是这个的原因
    Arrays.sort(ints);
    for (int i = 3 * n - 2, j = 0; j < n; i -= 2, j++) {
      result += ints[i];
    }
    System.out.println(result);
  }
}