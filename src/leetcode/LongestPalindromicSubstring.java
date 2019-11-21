package leetcode;

/**
 * 5. 最长回文子串
 *
 * <p>描述：给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * <pre>
 * 示例1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例2：
 * 输入: "cbbd"
 * 输出: "bb"
 * </pre>
 *
 * @author ihaokun
 * @date 2019/10/30 9:32
 * @see company.TractsNum 回文数
 * @see LengthOfLongestSubstring 最长子串
 * @see analysis.chapter2_analysis.MaxSubSeqSumSolution4 书上经典的 子串4种解法
 * @see jianzhi.MaxInWindows 剑指offer 子串
 */
public class LongestPalindromicSubstring {
  static private String[] cases;
  static {
    // Init Test Cases
    String case1 = "a";
    String case2 = "babad";
    String case3 = "cbbd";
    String case4 = "ac";
    String case5 = "abb";
    String case6 = "";
    String case7 = "ccc";
    cases = new String[]{case1, case2, case3, case4, case5, case6, case7};
  }
  // Test
  public static void main(String[] args) {
    LongestPalindromicSubstring palindrome = new LongestPalindromicSubstring();
    // Test
    for (String s : cases) {
      // System.out.println(palindrome.solution(s));
      System.out.println(palindrome.solution1(s));
      System.out.println(palindrome.acceptSolution(s));
    }
    System.out.println(
        palindrome.acceptSolution(
            "azwdzwmwcqzgcobeeiphemqbjtxzwkhiqpbrprocbppbxrnsxnwgikiaqutwpftbiinlnpyqstkiqzbggcsdzzjbrkfmhgtnbujzszxsycmvipjtktpebaafycngqasbbhxaeawwmkjcziybxowkaibqnndcjbsoehtamhspnidjylyisiaewmypfyiqtwlmejkpzlieolfdjnxntonnzfgcqlcfpoxcwqctalwrgwhvqvtrpwemxhirpgizjffqgntsmvzldpjfijdncexbwtxnmbnoykxshkqbounzrewkpqjxocvaufnhunsmsazgibxedtopnccriwcfzeomsrrangufkjfzipkmwfbmkarnyyrgdsooosgqlkzvorrrsaveuoxjeajvbdpgxlcrtqomliphnlehgrzgwujogxteyulphhuhwyoyvcxqatfkboahfqhjgujcaapoyqtsdqfwnijlkknuralezqmcryvkankszmzpgqutojoyzsnyfwsyeqqzrlhzbc"));
  }
  @Deprecated@SuppressWarnings("穷举法，时间复杂度分析：O(N³)，验证是否位回文串也花费了N；超时无法通过")
  private String solution(String s) {
    // solution 1: use exhaustive
    String max = "";
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      builder.append(s, i, i + 1);
      if (isPalindrome(builder.toString()) && builder.length() > max.length()) max = builder.toString();
      for (int j = i + 1; j < s.length(); j++) {
        builder.append(s, j, j + 1);
        if (isPalindrome(builder.toString()) && builder.length() > max.length()) max = builder.toString();
      }
      builder.delete(0, builder.length());
    }
    return max;
  }
  @Deprecated@SuppressWarnings("对上一个解法进行了部分优化，但还是超时")
  private String solution1(String s) {
    String max = "";
    for (int i = 0; i < s.length(); i++) {
      StringBuilder builder = new StringBuilder(s);
      String target = s.substring(i, i + 1);
      int lastIndex = builder.lastIndexOf(target);
      while (lastIndex >= i){
        String substring = builder.substring(i, lastIndex + 1);
        if (isPalindrome1(substring) && lastIndex - i + 1 > max.length()){
          max = substring;
          break;
        }
        builder.delete(lastIndex, builder.length());
        lastIndex = builder.lastIndexOf(target);
      }
    }
    return max;
  }
  @Deprecated@SuppressWarnings("验证回文串")
  private boolean isPalindrome(String s){
    return s.equals(new StringBuilder(s).reverse().toString());
  }
  @Deprecated@SuppressWarnings("验证回文串1")
  private boolean isPalindrome1(String s) {
    for (int i = 0; i < s.length() / 2; i++)
      if (!s.substring(i, i + 1).equals(s.substring(s.length() - 1 - i, s.length() - i))) return false;
    return true;
  }

  private String acceptSolution(String s) {
    if (s.isEmpty()) return s;
    int maxLen = 0, left = 0, right = 0;
    //
    int length = s.length();
    // Bottom-Up，自底向上
    for (int i = 0; i < length; i++) {
      int l = i, r = i;
      // 注意该特殊情况 例如bab，中间间隔的回文类型
      if (i + 2 < length && s.charAt(i) == s.charAt(i + 2) && s.charAt(i) != s.charAt(i + 1)){
        r += 2;
      } else {
        // 连续相同的回文类型
        while (r + 1 < length && s.charAt(l) == s.charAt(r + 1)){
          r += 1;
        }
      }
      if (l < r){
        do{
          l--;
          r++;
        }
        while (l >= 0 && r < length && s.charAt(l) == s.charAt(r));
        l++;
        r--;
        if (r - l + 1 > maxLen){
          maxLen = r - l + 1;
          left = l;
          right = r;
        }
      }
    }
    return s.substring(left, right + 1);
  }

}
