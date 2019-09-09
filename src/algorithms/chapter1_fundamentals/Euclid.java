package algorithms.chapter1_fundamentals;

/**
 * <pre>
 * 欧几里得算法（辗转相除法）
 *      目的：找到两个数的<em>最大公约数</em>
 *      最大公约数(gcd, greatest common divisor)：数学词汇，也称为最大公因数(hcf, highest common factor)
 *                                                指能够整除多个整数的最大正整数，且多个整数不能都为0
 *      自然语言描述：
 *          计算两个非负整数p和q的最大公约数：若q是0，则最大公约数是p。
 *          否，则将p除以q得到余数r，p和q的最大公约数 即为 q和r的最大公约数
 *      更正式的描述：
 *          gcd(a, 0) = a
 *          gcd(a, b) = gcd(b, a mod b)
 *
 *      mod运算：
 *          若 (A - B) mod N = 0，则 A mod N = B mod N，
 *          记作 A≡B(mod N)
 *          示例：36.26.10,(36-26) mod 10 = 0， 36 mod 10 = 26 mod 10 = 6
 *
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/29 23:29
 * @see <a href="https://zh.wikipedia.org/wiki/%E6%9C%80%E5%A4%A7%E5%85%AC%E5%9B%A0%E6%95%B8">GCD wiki</a>
 */
public class Euclid {
    public static void main(String[] args) {
        System.out.println(gcdLoop(8, 12));
    }

    /**
     * 《Algorithms》fourth edition 的写法
     *
     * @param p 整数1
     * @param q 整数2
     * @return 最大公约数
     */
    private static int gcdRecursive(int p, int q) {
        if (q == 0)
            return p;
        return gcdRecursive(q, p % q);
    }

    /**
     * <pre>
     * 《Data Structures and  Algorithm Analysis in Java》3rd 的写法
     * 详见 chapter 2.4.4
     * remainder：余数rem
     * 分析：
     *      <strong>p > q ? p mod q < p/2</strong>
     *      p < q ? p mod q = p
     *      p = q ? p mod q = 0
     *
     *      证明 第一条：
     *          当 q = p/2，则 p mod q = 0 < p/2
     *          当 q > p/2，则 p mod q < p/2
     *          当 q < p/2，则 p mod q < p/2
     *      那么最坏情况：
     *          q = p/2
     *          2logN
     *          O(logN)
     * </pre>
     *
     * @param m 整数1
     * @param n 整数2
     * @return 最大公约数
     */
    private static int gcdLoop(int m, int n) {
        while (n != 0) {
            int rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }
}
