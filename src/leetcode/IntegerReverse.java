package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 7 整数反转
 * <br/>
 * 题目描述：
 * <p>给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * <pre>
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 * 示例 2:
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * </pre>
 *
 * 注意:
 * <p>假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * <p>TAGS: Math
 * <p>LEVEL: EASY
 *
 * @author ihaokun
 * @date 2019/11/26 22:46
 */
public class IntegerReverse {
  public static void main(String[] args) {
    IntegerReverse instance = new IntegerReverse();
    // System.out.println(instance.reverse(123));
    // System.out.println(instance.reverse(-123));
    // System.out.println(instance.reverse(120));
    // System.out.println(instance.reverse(Integer.MAX_VALUE + 1));
    System.out.println(instance.reverse3(123));
    System.out.println(instance.reverse3(-123));
    System.out.println(instance.reverse3(120));
    System.out.println(instance.reverse3(Integer.MAX_VALUE + 1));
  }

  // way 1: use String API
  @Deprecated@SuppressWarnings("not a good way, it's too foolish")
  private int reverse(int x) {
    String s = String.valueOf(x);
    boolean isNegative = false;
    if (s.startsWith("-")){
      isNegative = true;
      s = s.substring(1);
    }
    if (s.startsWith("0")) {
      char[] chars = s.toCharArray();
      int i = 1;
      for (; i < chars.length; i++) if (chars[i] != '0') break;
      s = s.substring(i);
    }
    StringBuilder builder = new StringBuilder(s);
    if (isNegative){
      builder.append("-");
    }
    try{
      return Integer.parseInt(builder.reverse().toString());
    }catch (Exception e){
      return 0;
    }
  }

  // way 2, like 1 use List
  private int reverse1(int x) {
    long result = 0;
    List<Integer> list = new ArrayList<>();
    do{
      list.add(x % 10);
      x /= 10;
    }while (x != 0);
    int size = list.size();
    for (Integer i : list) {
      result += i * Math.pow(10, --size);
    }
    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
    return (int)result;
  }

  // way3, use Stack; almost equivalent to 2
  private int reverse2(int x){
    long result = 0;
    Stack<Integer> stack = new Stack<>();
    do{
      stack.push(x % 10);
    }while ((x /= 10) != 0);
    int size = stack.size();
    for (int i = 0; i < size; i++) {
      result += stack.pop() * Math.pow(10, i);
    }
    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
    return (int)result;
  }

  // optimize 2
  private int reverse3(int x){
    long result = 0;
    do{
      if (result*10 > Integer.MAX_VALUE || result*10 < Integer.MIN_VALUE) return 0;
      //CRUX 运用数学，替代stack的方式
      result = result*10 + x%10;
      if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
    }while ((x /= 10) != 0);
    return (int)result;
  }
}