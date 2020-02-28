package nowcoder.programming2019;

import java.util.Scanner;

/**
 * 二进制中有多少个1——快手
 *
 * 题目描述：
 * 把一个32-bit整型转成二进制，其中包含多少个1，比如5的二进制表达是101，其中包含2个1
 *
 * 输入描述：
 * 输入为整型（十进制），只需兼容32-bit即可，如5、32
 *
 * 输出描述：
 * 输出为字符串，如“2”、“1”
 *
 * 知识点：
 * 进制转化、数学
 *
 * @author ihaokun
 * @date 2020/2/26 20:48
 * @see nowcoder.jianzhi.binary.NumberOf1InBinary 剑指offer中有一样的题目，之前做过
 */
public class BinaryOnes {
  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int i = sc.nextInt();
    sc.close();
    // output
    foo(i);
    // bar(l);
    trimOneFromEnd(i);
    trimOneFromEndOneByOne(i);
  }
  private static void foo(int i){
    int result = 0;   // count
    // use Java API
    String s = Integer.toBinaryString(i);
    for (char c : s.trim().toCharArray()) {
      if (c == '1') result++;
    }
    System.out.println(result);
  }
  @Deprecated// 负数用补码表示，是其正数原码的取反+1，但这样写程序就比较复杂
  private static void bar(long l){
    int result = 0;   // count
    // use binary conversion knowledge
    // decimal 2 binary
    boolean isPositive = l > 0;
    long abs = Math.abs(l);
    while (abs > 0){
      if (abs % 2 == 1) result++;
      abs /= 2;
    }
    System.out.println(result);
  }
  //NOTE 剑指offer中有一道一摸一样的题目，之前做过，花了很长时间
  // 之前的三种解法，第一种是Java类库，第二种手写很罗嗦，有个第三种好像不错，自己思考一下看有没有好的解法

  // 这个解法应该算是最优解，不过不太容易想到
  private static void trimOneFromEnd(int i){
    int result = 0;
    while (i != 0){
      result++;
      i &= (i - 1);     //CRUX 去掉尾端的1
    }
    System.out.println(result);
  }

  // 再写一个和上面类似的，也是从尾端去1，不过这个需要的次数比上一个多一些
  private static void trimOneFromEndOneByOne(int i){
    int result = 0;
    int count = 0;
    while (count < 32){     // ? 负数的移位问题，不等同于正数的移位（正数移位是相当于乘除法2）；负数的右移位操作，最终停在-1的值
      if ((i & 1) == 1) result++;
      i >>= 1;
      count++;
    }
    System.out.println(result);
  }
}