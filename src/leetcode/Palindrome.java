package leetcode;

/**
 * 9 回文数
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * TAGS: MATH
 * LEVEL: EASY
 *
 * @author ihaokun
 * @date 2019/12/6 23:04
 * @see company.TractsNum "笔试遇到时，直接使用String API的解题思路"
 */
public class Palindrome {
  private static int[] ints;
  static {
    ints = new int[4];
    ints[0] = 121;  //true
    ints[1] = -121;  //false
    ints[2] = 10;  //false
    // special cases
    ints[3] = 0;  //true
  }

  public static void main(String[] args) {
    Palindrome palindrome = new Palindrome();
    for (int i : ints) {
      System.out.println(palindrome.isPalindrome(i));
    }
  }

  public boolean isPalindrome(int x) {
    if (x < 0) return false;
    int tmp = x;
    int reverse = 0;
    do{
      //CRUX 和前两题(7.8)一样的数学思路
      reverse = reverse*10 + tmp%10;
    }while ((tmp /= 10) != 0);
    return reverse == x;
  }
}