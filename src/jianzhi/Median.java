package jianzhi;

import java.util.Arrays;

/**
 * 剑指offer - 数据流中的中位数(Median)
 *
 * <p>题目描述： 如何得到一个数据流中的中位数？ 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 *
 * <p>知识点：进制转化、树
 *
 * <p>个人解法：没用到进制转化、树等，而是用了StringBuffer + 一些相关类库， 思路也比较简单，加都正常加，但每次读都排序，再取中位数（当然，这个思路并不好，仅仅简单而已）
 *
 * @author ihaokun
 * @date 2019/10/9 16:47
 */
public class Median {
  public static void main(String[] args) {
    // Init
    int[] arr = {5, 2, 3, 4, 1, 6, 7, 0, 8};
    // Test
    Median median = new Median();
    for (int i : arr) {
      median.insert(i);
      System.out.println(median.getMedian());
    }
  }

  private StringBuffer buffer = new StringBuffer();

  private void insert(Integer num) {
    buffer.append(num);
    buffer.append(",");
  }

  private Double getMedian() {
    String s = buffer.toString();
    String[] split = s.split(",");
    int length = split.length;
    int remainder = length % 2;
    Arrays.sort(split);
    if (remainder == 1) return Double.valueOf(split[length / 2]);
    // 注意一下，int转double的位置
    return (double) (Integer.parseInt(split[length / 2]) + Integer.parseInt(split[length / 2 - 1]))
        / 2;
  }
}
