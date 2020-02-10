package leetcode;

/**
 * 29. 两数相除
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * TAGS:
 * LEVEL: MEDIUM
 *
 * @author ihaokun
 * @date 2020/2/9 19:47
 */
public class DivideTwoIntegers {
  public static void main(String[] args) {
    DivideTwoIntegers instance = new DivideTwoIntegers();
    // sample
    System.out.println(instance.solution1(10, 3));
    System.out.println(instance.solution1(7, -3));
    // unAccept cases
    System.out.println(instance.solution1(1, 1));
    System.out.println(instance.solution1(-2147483648, -1));
    System.out.println(instance.solution1(-2147483648, -2));
    System.out.println(instance.solution1(0, 1));
    System.out.println(instance.solution1(1, 2));
    System.out.println(instance.solution1(-1060849722, 99958928));
    System.out.println(instance.solution1(-2147483648, -1017100424));
  }

  // 简要描述思路：使用减法替代除法
  private int solution(int dividend, int divisor){
    double count = 0;
    boolean isLess = dividend < 0 ^ divisor < 0;
    double dividendAbs = dividend;
    dividendAbs = Math.abs(dividendAbs);
    double divisorAbs = divisor;
    divisorAbs = Math.abs(divisorAbs);
    if (divisorAbs == 1) count = dividendAbs;
    else
      while ((dividendAbs -= divisorAbs) >= 0) { //NOTE =0的情况要注意
        ++count;
      }
    double result = isLess ? -count : count;
    if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return Integer.MAX_VALUE;
    return (int) result;
  }
  // 这个思路可行，但时间不通过

  // 新思路，模拟除式（列竖式计算除法）
  private int solution1(int dividend, int divisor){
    if (dividend == 0) return 0;
    if (divisor == 0) throw new RuntimeException("divisor cannot is 0");
    StringBuilder result = new StringBuilder();
    boolean isNegative = dividend < 0 ^ divisor < 0;
    // convert to String(Absolute Value)
    long dividendAbs = dividend == Integer.MIN_VALUE ? (long) Integer.MAX_VALUE + 1 : Math.abs(dividend);
    long divisorAbs = divisor == Integer.MIN_VALUE ? (long) Integer.MAX_VALUE + 1 : Math.abs(divisor);
    String dividendStr = String.valueOf(dividendAbs);
    String divisorStr = String.valueOf(divisorAbs);
    // calculate
    int len = dividendStr.length();
    StringBuilder remainder = new StringBuilder();
    for (int i = 0; i < len; i++, dividendStr = dividendStr.substring(1)) {
      remainder.append(dividendStr, 0, 1);
      long subDividend = Long.parseLong(remainder.toString());
      if (subDividend >= divisorAbs){
        int count = 0;
        while (subDividend >= divisorAbs){
          ++count;
          subDividend -= divisorAbs;
        }
        result.append(count);
        remainder = new StringBuilder(String.valueOf(subDividend));
      } else {
        result.append(0);
      }
    }
    // result handle
    long resultLong = result.toString().equals("") ? 0 : Long.parseLong(result.toString());
    resultLong = isNegative ? -resultLong : resultLong;
    return resultLong > Integer.MAX_VALUE || resultLong < Integer.MIN_VALUE ? Integer.MAX_VALUE : (int) resultLong;
  }
  // 这个解法通过了，但代码行数较多，效果也不是很理想

}