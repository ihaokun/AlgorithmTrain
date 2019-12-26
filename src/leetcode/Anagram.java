package leetcode;

import java.util.Arrays;

/**
 * 242 有效的字母异位词
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * TAGS: Sorting, hashtable
 * LEVEL: EASY
 *
 * @author ihaokun
 * @date 2019/12/24 0:13
 */
public class Anagram {
  public static void main(String[] args) {
    Anagram instance = new Anagram();
    System.out.println(instance.solution("anagram", "nagaram"));
    System.out.println(instance.solution("rat", "car"));
    System.out.println(instance.solution("a", "ab"));
    System.out.println(instance.solution1("anagram", "nagaram"));
    System.out.println(instance.solution1("rat", "car"));
    System.out.println(instance.solution1("a", "ab"));
  }
  // first sorting, then compare one-to-one
  // use Java API
  private boolean solution(String s, String t){
    if (s.length() != t.length()) return false;
    char[] array1 = s.toCharArray();
    char[] array2 = t.toCharArray();
    Arrays.sort(array1);
    Arrays.sort(array2);
    /*for (int i = 0; i < array1.length; i++) {
      if (array1[i] != array2[i]) return false;
    }
    return true;
    // can replace by Java Arrays API, like next line*/
    return Arrays.equals(array1, array2);
  }
  // fenix, iaho
  //分析该解法的复杂度：时间复杂度O(nlogn)；空间复杂度O(1)

  // feature vector(bitSet) method
  private boolean solution1(String s, String t){
    if (s.length() != t.length()) return false;
    int[] counter = new int[26];// create a counter list
    for (int i = 0; i < s.length(); i++) {
      counter[s.charAt(i) - 'a']++;
      counter[t.charAt(i) - 'a']--;
    }
    for (int i : counter) {
      if (i != 0) return false;
    }
    return true;
  }
  //分析该解法的复杂度，时间复杂度O(N)，空间复杂度O(1)
}