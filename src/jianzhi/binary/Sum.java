package jianzhi.binary;

/**
 * <pre>
 *     剑指offer - 求 1 + 2 + 3 + ... + n
 *
 *     题目描述：
 *          求1+2+3+...+n，要求 不能使用 乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 *     考点：
 *          <em>发散思维能力</em>
 *     知识点：
 *          <em>进制转化</em>
 *
 *     解题思路：
 *          两个解法：
 *          解法一：
 *              <em>注意使用 位运算符 + 灵活的转换 + <strong>注意进制中2的重要作用（>>和<<都与2相关）</strong></em>
 *              Math.pow()方法的实现是本地方法，也没有用到乘除运算符
 *          解法二：
 *              比解法一更为巧妙，使用 <em>递归 + 短路&&</em>
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/20 0:28
 */
public class Sum {
    public static void main(String[] args) {
        System.out.println("Solution1:");
        System.out.println(solution(4));
        System.out.println(solution(1));
        System.out.println("Solution2:");
        System.out.println(solution1(4));
        System.out.println(solution1(1));
    }

    private static int solution(int n) {
        // 1 + 2 + 3 + ... + n
        // (1 + n) * (n/2)
        // (n + n^2) / 2
        // (n + (int)Math.pow(n, 2)) >>> 1
        return ((int) Math.pow(n, 2) + n) >>> 1;
        // 注意，Math.pow()方法的实现是本地方法
    }

    private static int solution1(int n) {
        // 递归实现 + &&短路思路
        int sum = n;
        //XXX 关键处：通过 n > 0 这个短路判断，让程序不执行当 n <= 0 时的递归
        boolean ans = (n > 0) && ((sum += solution1(n - 1)) > 0);
        return sum;
    }
}
