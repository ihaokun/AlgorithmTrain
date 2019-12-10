package leetcode;

/**
 * 8 字符串转换整数（atoi）
 *
 * 说明：
 * <p>假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN
 * (−231) 。
 *
 *
 * TAGS: Math、String
 * LEVEL: Medium
 *
 * @author ihaokun
 * @date 2019/12/5 22:14
 */
public class Atoi {
  private static String[] strs;
  static {
    strs = new String[11];
    strs[0] = "42"; //42
    strs[1] = "   -42"; //-42
    strs[2] = "4193 with words"; //4193
    strs[3] = "words and 987";  //0
    strs[4] = "-91283472332"; //-2147483648
    // special cases
    // ""、"+"
    strs[5] = "";
    strs[6] = "+";
    strs[7] = "+123";
    strs[8] = "-";
    strs[9] = "-123";
    // un allow cases
    strs[10] = "  -0012a42";
  }
  public static void main(String[] args) {
    Atoi instance = new Atoi();
    for (String str : strs) {
      System.out.println(instance.myAtoi(str));
    }
  }

  // perhaps should use recursive
  private int myAtoi(String str) {
    // trim leading & trailing spaces
    str = str.trim();
    if (str.isEmpty()) return 0;
    // remain
    char c = str.charAt(0);
    int i;
    int max;
    boolean flag = false;
    if (c == '-'){
      max = (int) Math.pow(2, 31);
      i = 1;
      flag = true;
    } else if (c == '+'){
      max = (int) (Math.pow(2, 31) - 1);
      i = 1;
    } else if (c >= '0' && c <= '9'){
      max = (int) (Math.pow(2, 31) - 1);
      i = 0;
    } else {
      return 0;
    }
    // computer
    long l = 0;
    char[] chars = str.toCharArray();
    for (; i < chars.length; i++) {
      if (chars[i] >= '0' && chars[i] <= '9'){
        //CRUX 这里应该要使用上一题的思路，计算而不是使用语法糖
        l = l*10 + chars[i] - '0';
        if (l > max){
          if (flag) return Integer.MIN_VALUE;
          else return Integer.MAX_VALUE;
        }
      } else {
        break;
      }
    }
    if (flag) return (int) -l;
    return (int) l;
  }
}