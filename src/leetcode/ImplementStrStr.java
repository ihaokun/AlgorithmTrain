package leetcode;

/**
 * 28. 实现<a href="https://baike.baidu.com/item/strstr/811469">strStr()</a>函数
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * TAGS:
 * LEVEL: EASY
 *
 * @author ihaokun
 * @date 2020/2/7 21:02
 * @see String#indexOf(char[], int, int, char[], int, int, int)
 */
public class ImplementStrStr {
  public static void main(String[] args) {
    // input:
    // haystack = "hello", needle = "ll"
    // haystack = "aaaaa", needle = "bba"
    // output:
    // 2
    // -1
    ImplementStrStr instance = new ImplementStrStr();
    // System.out.println(instance.solution("hello", "ll"));
    // System.out.println(instance.solution("aaaaa", "bba"));
    System.out.println(instance.solution1("hello", "ll"));
    System.out.println(instance.solution1("aaaaa", "bba"));
    // unAccept cases
    System.out.println(instance.solution1("", "")); // should be 0, but result is -1
    System.out.println(instance.solution1("a", "")); // should be 0, but result is -1
    System.out.println(instance.solution1("a", "a")); // should be 0, but result is -1
  }
  private int solution(String haystack, String needle){
    // JDK自带的API，String.indexOf()方法
    return haystack.indexOf(needle);
  }

  // 模仿JDK的API（String.indexOf()方法），自己尝试写一下
  private int solution1(String haystack, String needle){
    if (needle.equals("")) return 0;
    char[] sourceChars = haystack.toCharArray();
    char[] targetChars = needle.toCharArray();
    int max = sourceChars.length - targetChars.length;
    for (int i = 0; i <= max; i++) {  //note 应该是<=，因为存在等长两String的情况
      // JDK里还有无循环体的while和for的写法，有点意外
      // First, Look for first character
      while (i <= max && sourceChars[i++] != targetChars[0]);
      // Second, compare with needle
      if (--i <= max){
        int j = 0;
        for (; j < targetChars.length && sourceChars[i + j] == targetChars[j]; j++);
        if (j == targetChars.length) return i;
      }
    }
    return -1;
  }
}