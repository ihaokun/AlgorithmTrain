package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17 电话号码的字母组合
 *
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合
 * int, char[]
 * 2, abc
 * 3, def
 * 4, ghi
 * 5, jkl
 * 6, mno
 * 7, pqrs
 * 8, tuv
 * 9, wxyz
 *
 * Sample: in: "23"; out: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 * note：注意单个数字的情况，如2，返回结果应该为[a, b, c]，而不是[abc]
 *
 * TAGS: string, 回溯算法
 * LEVEL: MEDIUM
 *
 * @author ihaokun
 * @date 2020/1/6 11:04
 */
public class LetterCombinations {
  final static Map<Integer, char[]> map = new HashMap<>();
  static {
    map.put(2, new char[]{'a', 'b', 'c'});
    map.put(3, new char[]{'d', 'e', 'f'});
    map.put(4, new char[]{'g', 'h', 'i'});
    map.put(5, new char[]{'j', 'k', 'l'});
    map.put(6, new char[]{'m', 'n', 'o'});
    map.put(7, new char[]{'p', 'q', 'r', 's'});
    map.put(8, new char[]{'t', 'u', 'v'});
    map.put(9, new char[]{'w', 'x', 'y', 'z'});
  }
  public static void main(String[] args) {
    LetterCombinations instance = new LetterCombinations();
    System.out.println(instance.solution("23"));
    // unAccept cases
    System.out.println(instance.solution(""));
    System.out.println(instance.solution("2"));
  }

  public List<String> solution(String digits) {
    List<String> result = new ArrayList<>();
    if (digits == null || digits.isEmpty()) {
      return result;
    }
    if (digits.length() == 1) {
      for (char c : map.get(digits.toCharArray()[0] - '0')) {
        result.add(String.valueOf(c));
      }
      return result;
    }

    char[] chars = digits.toCharArray();
    int[] keys = new int[chars.length];
    for (int i = 0; i < chars.length; i++) {
      keys[i] = chars[i] - '0';
    }
    int[] ordinals = new int[keys.length];

    // recursion(keys, ordinals, result);
    loop(keys, ordinals, result); //尾递归可以被循环替代

    return result;
  }

  @Deprecated
  private void recursion(int[] keys, int[] ordinals, List<String> result) {
    // build string into result(ordinals last update)
    int i = 0;
    StringBuilder sb = new StringBuilder();
    for (; i < keys.length; i++) {
      sb.append(map.get(keys[i])[ordinals[i]]);
    }
    --i;//note: the last index
    ++ordinals[i];
    result.add(sb.toString());
    //backtracking
    if (ordinals[i] > map.get(keys[i]).length - 1){
      ordinals[i--] = 0;
      while (i != 0 && ordinals[i] == map.get(keys[i]).length - 1){
        ordinals[i--] = 0;
      }
      ++ordinals[i];
    }
    // base case
    if (ordinals[0] > map.get(keys[0]).length - 1){
      return;
    }
    //tail recursion ? (can replace by loop)
    recursion(keys, ordinals, result);
  }

  private void loop(int[] keys, int[] ordinals, List<String> result){
    // tail recursion can replace by loop
    while(ordinals[0] <= map.get(keys[0]).length - 1){
      // build string into result(ordinals last update)
      int i = 0;
      StringBuilder sb = new StringBuilder();
      for (; i < keys.length; i++) {
        sb.append(map.get(keys[i])[ordinals[i]]);
      }
      --i;//note: the last index
      ++ordinals[i];
      result.add(sb.toString());
      //backtracking
      if (ordinals[i] > map.get(keys[i]).length - 1){
        ordinals[i--] = 0;
        while (i != 0 && ordinals[i] == map.get(keys[i]).length - 1){
          ordinals[i--] = 0;
        }
        ++ordinals[i];
      }
    }
  }
}