package leetcode.difficulty;

/**
 * 10 正则表达式匹配
 *
 * <p>给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * <p>'.' 匹配任意单个字符
 * <p>'*' 匹配零个或多个前面的那一个元素
 * <p>所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * <p>说明:
 * <p>s 可能为空，且只包含从 a-z 的小写字母。 p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * <p>TAGS: 字符串、动态规划、回溯算法
 * <p>LEVEL: Difficulty
 *
 * @author ihaokun
 * @date 2019/12/7 20:05
 * @see nowcoder.jianzhi.string.RegexMatch "剑指offer有同样的题，还是按照这个思路解吧，虽然效率不高，但官方题解看不懂"
 * @see <a href="https://leetcode-cn.com/problems/regular-expression-matching/">原题</a>
 */
public class RegexMatch {
  private static String[][] cases;
  static {
    cases = new String[5][2];
    cases[0] = new String[]{"aa", "a"}; //false
    cases[1] = new String[]{"aa", "a*"}; //true
    cases[2] = new String[]{"ab", ".*"}; //true，注意实际上是两个'.'
    cases[3] = new String[]{"aab", "c*a*b"}; //true，注意实际上是"a*b"
    cases[4] = new String[]{"mississippi", "mis*is*p*."}; //false
  }
  public static void main(String[] args) {
    RegexMatch regexMatch = new RegexMatch();
    for (String[] strs : cases) {
      //XXX JDK Regex matches
      System.out.println(strs[0].matches(strs[1]));
      System.out.println(regexMatch.isMatch(strs[0], strs[1]));
    }
  }
  // use recursion, loop process too difficult
  private boolean isMatch(String s, String p) {
    if (s == null && p == null) return false;
    //关键应该是'*'匹配符号的处理
    char[] chars = s.toCharArray();
    char[] patterns = p.toCharArray();
    return matches(chars, 0, patterns, 0);
  }
  private static boolean matches(char[] chars, int i, char[] patterns, int j) {
    //有效性检验：str到尾，pattern到尾，匹配成功
    if (i == chars.length && j == patterns.length) return true;
    //pattern先到尾，匹配失败
    if (i != chars.length && j == patterns.length) return false;
    //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
    if (j + 1 < patterns.length && patterns[j + 1] == '*') {
      if (i < chars.length && (patterns[j] == chars[i] || patterns[j] == '.'))
        return matches(chars, i, patterns, j + 2)//模式后移2，视为x*匹配0个字符
            || matches(chars, i + 1, patterns, j + 2)//视为模式匹配1个字符
            || matches(chars, i + 1, patterns, j);//*匹配1个，再匹配str中的下一个
      else
        return matches(chars, i, patterns, j + 2);
    }
    //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
    if (i < chars.length && (patterns[j] == chars[i] || patterns[j] == '.'))
      return matches(chars, i + 1, patterns, j + 1);
    return false;
  }
}