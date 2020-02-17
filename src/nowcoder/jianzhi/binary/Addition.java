package nowcoder.jianzhi.binary;

/**
 * <pre>
 *     剑指offer - 不用 加减乘除 做加法
 *
 *     题目描述：
 *          写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 *
 *     考点：
 *          发散思维能力
 *     知识点：
 *          进制转化
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/20 21:25
 */
public class Addition {
    public static void main(String[] args) {
        System.out.println(add(1, 3));
        System.out.println(add1(1, 3));
    }
    private static int add(int num1, int num2){
        // 不正确的解法，虽然用了java的lang库，但实际方法中还是用了+法
        return Math.addExact(num1, num2);
    }

    /**
     * <pre>
     * 解题思路：
     *      <em>模仿 十进制加法，用 二进制 及 位运算符 来实现</em>
     *      十进制过程：
     *          x + y：3 + 18
     *          非进位：11
     *          进位：1
     *          和：21
     *      使用 二进制 + 位运算符 模仿：
     *          x：0000 0010
     *          y：0000 0011
     *          x ^ y：0000 0001 得到非进位
     *          (x & y) << 1：0000 0100 得到进位
     *          递归如上操作，直至(x & y)的值为0，无进位，则得到 x + y 的和 = x ^ y
     *
     * </pre>
     *
     * @param num1 the first value
     * @param num2 the second value
     * @return sum
     */
    private static int add1(int num1, int num2){
        int foo = num1, bar = num2;
        num1 = foo ^ bar;
        num2 = (foo & bar) << 1;
        return num2 != 0 ? add1(num1, num2) : num1;
    }
}
