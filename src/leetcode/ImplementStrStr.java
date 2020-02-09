package leetcode;

/**
 * 28. 实现<a href="https://baike.baidu.com/item/strstr/811469">strStr()</a>函数
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * TAGS: String, Double Points
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
    System.out.println(instance.solution2("hello", "ll"));
    System.out.println(instance.solution2("aaaaa", "bba"));
    // unAccept cases
    System.out.println(instance.solution2("", "")); // should be 0, but result is -1
    System.out.println(instance.solution2("a", "")); // should be 0, but result is -1
    System.out.println(instance.solution2("a", "a")); // should be 0, but result is -1
  }
  private int solution(String haystack, String needle){
    // JDK自带的API，String.indexOf()方法
    return haystack.indexOf(needle);
  }

  /**
   * 这个解法就是按照JDK的思路来的，和我之前一道题的思路也类似，也是双指针，
   *
   * @param haystack
   * @param needle
   * @return
   * @see String#indexOf(char[], int, int, char[], int, int, int) JDK
   * @see RemoveDuplicates#solution2(int[]) 类似
   */
  private int solution1(String haystack, String needle){
    if (needle.equals("")) return 0;
    char[] sourceChars = haystack.toCharArray();
    char[] targetChars = needle.toCharArray();
    int max = sourceChars.length - targetChars.length;
    for (int i = 0; i <= max; i++) {  //NOTE 应该是<=，因为存在等长两String的情况
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

  /**
   * 再写一个无内嵌循环的解法，同双指针，也是那题的官解的思路
   * 这个解法比上一个解法的代码多一些，但更清晰已于理解
   *
   * @param haystack
   * @param needle
   * @return
   * @see RemoveDuplicates#officialSolution(int[]) 类似思路
   */
  private int solution2(String haystack, String needle){
    if (needle.equals("")) return 0;
    char[] sourceChars = haystack.toCharArray();
    char[] targetChars = needle.toCharArray();
    int max = sourceChars.length - targetChars.length;
    for (int i = 0; i <= max; i++) {  //NOTE 应该是<=，因为存在等长两String的情况
      if (sourceChars[i] == targetChars[0]){
        boolean flag = true;
        for (int j = 0; j < targetChars.length; j++) {
          if (sourceChars[i + j] != targetChars[j]) {
            flag = false;
            break;
          }
        }
        if (flag) return i;
      }
    }
    return -1;
  }

}