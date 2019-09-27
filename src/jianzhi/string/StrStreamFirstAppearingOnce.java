package jianzhi.string;

/**
 * <pre>
 * 剑指offer - 字符流中第一个不重复的字符
 *
 * 题目描述：
 *      请实现一个函数用来找出字符流中第一个只出现一次的字符。
 *      例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 输出描述：
 *      如果当前字符流没有存在出现一次的字符，返回#字符。
 *
 * 小结：
 *      此题的解法是使用hashing，之前有一题和这道题一样，见{@link jianzhi.FirstNotRepeatingChar "剑指offer - 第一个只出现一次的字符"}
 *      之前那道题是使用Set解题，但用在这道题上，IDE测试通过，提交不过
 *      于是改用了牛客上的一个解答作参考，对其做了一点改进：char是16 bit，故应该是2^16
 *
 * 注意：
 *      <em>hashing</em>的相关知识
 *      顺带分析该解法，时空间复杂度均为 O（N）
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/27 1:03
 */
public class StrStreamFirstAppearingOnce {
  public static void main(String[] args) {
    // String str = "google";
    System.out.println(Character.MIN_VALUE);
    System.out.println(Character.MAX_VALUE);
    System.out.println(Character.SIZE);
    System.out.println(Character.BYTES);
    String str = "helloworld";
    StrStreamFirstAppearingOnce first = new StrStreamFirstAppearingOnce();
    for (char c : str.toCharArray()) {
      first.insert(c);
      System.out.println(first.firstAppearingOnce());
    }
  }

  private StringBuffer buffer = new StringBuffer();
  int[] ints = new int[(int) Math.pow(2, Character.SIZE)];

  // Insert one char from stringstream
  private void insert(char ch) {
    buffer.append(ch);
    if (ints[ch] == 0) ints[ch] = 1;
    else ints[ch]++;
  }

  // return the first appearence once char in current stringstream
  private char firstAppearingOnce() {
    for (char c : buffer.toString().toCharArray()) {
      if (ints[c] == 1) return c;
    }
    return '#';
  }
}
