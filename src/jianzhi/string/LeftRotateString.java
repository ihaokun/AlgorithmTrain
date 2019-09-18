package jianzhi.string;

/**
 * <pre>
 *     剑指offer - 左旋转字符串
 *     Rotate：旋转
 *
 *     题目描述：
 *          汇编语言中有一种移位指令叫做循环左移（ROL），
 *          现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 *          对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
 *          是不是很简单？OK，搞定它！
 *
 *     考点：
 *          知识迁移能力
 *     知识点：
 *          字符串
 *
 *     简单题
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/18 1:02
 */
public class LeftRotateString {
    public static void main(String[] args) {
        String str = "abcXYZdef";
        System.out.println(solution(str, 3));
    }

    /**
     * <pre>
     *     解法一：
     *          完全使用Java String的API来解题
     * </pre>
     *
     * @param str 待旋转字符串
     * @param n   旋转n位
     * @return 旋转后字符串
     */
    private static String solution(String str, int n) {
        if (str.length() < n)
            return "";
        return str.concat(str.substring(0, n)).substring(n);
    }
}
