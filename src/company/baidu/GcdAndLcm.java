package company.baidu;

import java.util.ArrayList;
import java.util.List;

/**
 * SCAN电梯调度；堆
 * {@link algorithms.chapter1fundamentals.Euclid "之前在书上看到过的欧几里得算法（辗转相除法）求GCD"}
 * <p>
 *     之前做过GCD，或许以前课堂上也知道LCM的概念，但这次确是是没做出来，
 *     感觉很糟糕<br/>
 *     最后，记住公式：<strong>LCM(a,b) = (a * b) / GCD(a,b)</strong>
 * </p>
 *
 * @author ihaokun
 * @date 2019/9/24 19:24
 * @see <a href="https://zh.wikipedia.org/wiki/%E6%9C%80%E5%A4%A7%E5%85%AC%E5%9B%A0%E6%95%B8">GCD（Greatest Common Divisor） 最大公约数 wiki</a>
 * @see <a href="https://zh.wikipedia.org/wiki/%E6%9C%80%E5%B0%8F%E5%85%AC%E5%80%8D%E6%95%B8">LCM（Least Common Multiple） 最小公倍数 wiki</a>
 */
public class GcdAndLcm {
    public static void main(String[] args) {
        solution(5);
        solution(3);
    }
    private static void solution(int n){
        // use Exhaustive
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = i + 1;
        }
        // C(n, 2) = A(2.2) * n
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            list.add(diff(ints[i], ints[i + 1]));
        }
        System.out.println(list);
    }
    private static int diff(int a, int b){
        int gcd = gcdRecursive(a, b);
        return a * b / gcd - gcd;
    }
    private static int gcdRecursive(int p, int q) {
        if (q == 0)
            return p;
        return gcdRecursive(q, p % q);
    }
    private static int lcm(int a, int b){
        // 两个整数的 最小公倍数，运用公式计算，需要先求得 最大公约数GCD
        // 公式：LCM(a, b) = (a * b) / GCD(a, b)
        return (a * b) / gcdRecursive(a, b);
    }
}
