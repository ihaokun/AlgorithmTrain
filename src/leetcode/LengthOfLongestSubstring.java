package leetcode;

/**
 * 3. 无重复字符的最长子串
 *
 * <p>题目描述：给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * <p>示例：
 *
 * <pre>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * </pre>
 *
 * <p>涉及知识点：字符串、hashtable、双指针
 *
 * <p>Level：Medium
 *
 * @author ihaokun
 * @date 2019/10/22 0:20
 */
public class LengthOfLongestSubstring {
  public static void main(String[] args) {
    // Init
    String str = "abcabcbb";
    // String str = "bbbbb";
    // String str = "pwwkew";
    // String str = " ";
    // String str = "tmmzuxt";
    // Test
    LengthOfLongestSubstring instance = new LengthOfLongestSubstring();
    System.out.println(instance.lengthOfLongestSubstring1(str));
  }

  // 解法一：使用 穷举 + hashtable(HashSet)
  // 顺带分析该解法 时间复杂度：O(n³)，XXX contains()方法相当于一个N
  private int lengthOfLongestSubstring(String s) {
    /*
    Set<String> set = new HashSet<>();
    StringBuilder builder = new StringBuilder();
    String longest = "";
    for (int i = 0; i < s.length(); i++) {
      set.add(s.substring(i, i + 1));
      builder.append(s, i, i + 1);
      for (int j = i + 1; j < s.length(); j++) {
        if (set.contains(s.substring(j, j + 1))) break;
        builder.append(s, j, j + 1);
        set.add(s.substring(j, j + 1));
      }
      if (builder.length() > longest.length()) longest = builder.toString();
      set.clear();
      builder.delete(0, builder.length());
    }
    System.out.println(longest);
    return longest.length();
    */
    // 使用 字符串模拟 穷举
    int length = 0;
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      builder.append(s, i, i + 1);
      for (int j = i + 1; j < s.length(); j++) {
        if (builder.toString().contains(s.substring(j, j + 1))) {
          break;
        }
        builder.append(s, j, j + 1);
      }
      if (builder.length() > length) length = builder.length();
      builder.delete(0, builder.length());
    }
    return length;
  }

  /**
   * 使用 队列Queue 的解法， 在 剑指offer 也有做到相似解法的题目，{@link jianzhi.MaxInWindows}
   *
   * <p>该解法时间复杂度分析：O(N²)
   *
   * @param s
   * @return
   */
  private int lengthOfLongestSubstring1(String s) {
    /*
    int length = 0;
    Queue<String> queue = new ArrayDeque<>();
    for (int i = 0; i < s.length(); ) {
      if (queue.contains(s.substring(i, i + 1))) {
        queue.remove();
        continue;
      }
      queue.offer(s.substring(i, i + 1));
      if (queue.size() > length) length = queue.size();
      i++;
    }
    System.out.println(queue);
    return length;
    */
    // 改进一下，使用 纯字符串 模拟 队列 试试
    /*int length = 0;
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < s.length(); ) {
      if (builder.toString().contains(s.substring(i, i + 1))) {
        builder.delete(0, 1);
        // continue是关键
        continue;
      }
      builder.append(s, i, i + 1);
      if (builder.length() > length) length = builder.length();
      // i++不要放在for中
      i++;
    }
    return length;*/
    // 对上面解法的小小优化
    int length = 0;
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < s.length(); ) {
      String current = s.substring(i, i + 1);
      if (builder.toString().contains(current)) {
        // 优化
        builder.delete(0, builder.indexOf(current) + 1);
        continue;
      }
      builder.append(s, i, i + 1);
      if (builder.length() > length) length = builder.length();
      i++;
    }
    return length;
  }

}