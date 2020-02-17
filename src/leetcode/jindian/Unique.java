package leetcode.jindian;

import java.util.HashSet;

/**
 * 面试题01 判断字符是否唯一
 *
 * 实现一个算法，确定一个字符串s的所有字符是否全都不同
 *
 * 限制：不使用额外的数据结构
 *
 * TAGS: hashtable
 * LEVEL: EASY
 *
 * @author ihaokun
 * @date 2020/2/13 22:51
 */
public class Unique {
  public static void main(String[] args) {
    Unique instance = new Unique();
    System.out.println(instance.solution1("leetcode"));  // should be false
    System.out.println(instance.solution1("abs")); // should be true
  }

  private boolean solution(String s){
    HashSet<Character> hashSet = new HashSet<>();
    for (char c : s.toCharArray())
      if (!hashSet.add(c)) return false;
    return true;
  }

  // 不使用额外数据结构，那么hashtable的方式就不能用了
  private boolean solution1(String s){
    // 一种笨方法，耗费额外的时间，时间换空间
    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++)
      for (int j = i + 1; j < chars.length; j++)
        if (chars[i] == chars[j]) return false;
    return true;
  }
}