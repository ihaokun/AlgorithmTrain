package jianzhi.string;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     剑指offer - 把字符串转换成整数
 *
 *     题目描述：
 *          将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
 *          要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
 *
 *     考点：
 *          综合
 *     知识点：
 *          进制转化、字符串
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/20 22:34
 */
public class String2Integer {
    public static void main(String[] args) {
        System.out.println(convert("10"));
        System.out.println(convert("+2147483647"));
        System.out.println(convert("-2147483647"));
        System.out.println(convert("1a33"));
    }

    /**
     * <pre>
     *     个人解法：
     *          1. 初始化一个Map，字符 和 整数 的 0 ~ 9，一一对应
     *          2. String转为Char数组，按位求和，得到对应整数
     *      其中一些不符合的情况，视情况return 0
     *      补充：可以优化一下，不用初始化Map，直接'9' - '0'，这样也可以
     * </pre>
     *
     * @param str 字符串
     * @return 转化后整数
     * @see Integer#parseInt(String) "JDK实现，可以参考"
     */
    private static int convert(String str) {
        // if (str == null || str.equals(""))
        if (str.isEmpty())
            return 0;
        // 7个位运算符，&、|、^、~、<<、>>、>>>
        // Init Character correspond Integer in Range（0 ~ 9）
        Map<Character, Integer> map = new HashMap<>(10);
        map.put('0', 0);
        map.put('1', 1);
        map.put('2', 2);
        map.put('3', 3);
        map.put('4', 4);
        map.put('5', 5);
        map.put('6', 6);
        map.put('7', 7);
        map.put('8', 8);
        map.put('9', 9);
        // String convert Integer
        long sum = 0;
        int i = 0;
        boolean negative = false;
        int length = str.length();
        char firstChar = str.charAt(0);
        // compute|calculate
        if (firstChar == '+' || firstChar == '-') {
            i = 1;
            if (firstChar == '-')
                negative = true;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                sum += map.get(c) * Math.pow(10, length - 1 - i);
            } else {
                return 0;
            }
        }
        if (negative)
            sum = 0 - sum;

        if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE)
            return 0;
        return (int) sum;
    }
}
