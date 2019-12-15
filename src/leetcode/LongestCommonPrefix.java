package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 14 最长公共前缀
 *
 * 编写一个函数来查找字符数组的公共前缀，若不存在返回""
 * 说明：所有输入只包含a-z
 *
 * TAGS:
 * LEVEL: EASY
 *
 * @author ihaokun
 * @date 2019/12/15 22:30
 */
public class LongestCommonPrefix {
  private static Map<String[], String> useCases = new HashMap<>();
  static {
    useCases.put(new String[]{"flower", "flow", "flight"}, "fl");
    useCases.put(new String[]{"dog", "racecar", "car"}, "");
    // unAccept case
    useCases.put(new String[]{"aa", "a"}, "a");
  }
  public static void main(String[] args) {
    LongestCommonPrefix prefix = new LongestCommonPrefix();
    useCases.forEach((strings, s) -> System.out.println(prefix.solution(strings).equals(s)));
  }

  private String solution(String[] strs){
    // 穷举法
    if (strs.length == 0) return "";
    if (strs.length == 1) return strs[0];

    String result = "";
    char[] chars = strs[0].toCharArray();
    int index = 0;
    boolean flag = true;

    while (index < chars.length){
      for (int i = 1; i < strs.length; i++)
        //XXX 注意字符串长度
        if (strs[i].length() <= index || chars[index] != strs[i].charAt(index)) {
          flag = false;
          break;
        }
      if (flag) {
        result += chars[index];
        ++index;
      } else break;
    }
    return result;
  }
}