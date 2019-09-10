package jianzhi;

import java.util.ArrayList;

/**
 * <pre>
 * 剑指offer - 丑数
 *
 * 知识点：
 *      穷举
 * 考点：
 *      时间、空间效率的平衡
 *
 * 题目描述：
 *      把只包含<i>质因子</i>2、3和5的数称作<em>丑数（Ugly Number）</em>。例如6、8都是丑数，但14不是，因为它包含质因子7。
 *      习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 *
 * math refer：
 *      <a href="https://zh.wikipedia.org/wiki/%E8%B3%AA%E5%9B%A0%E6%95%B8">质因子（素因数）</a>：能整除给定正整数的素数
 *      <a href="https://zh.wikipedia.org/wiki/%E7%B4%A0%E6%95%B0">素数（Prime Number）wiki</a>：也称质数；大于1的自然数中，除了1和其本身，不能被其他自然数整除的数
 *      <a href="https://zh.wikipedia.org/wiki/%E8%87%AA%E7%84%B6%E6%95%B0">自然数wiki</a>：一般指正整数，用N表示
 *
 * 小结：
 *      /和%mod的使用，%常用于判断是否能整除，/常用于获得尾位
 *
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/4 15:41
 */
public class UglyNumber {
    public static void main(String[] args) {
        System.out.println("Nth Ugly Number");
        System.out.println("Solution 1：");
        System.out.println(solution(7));
        System.out.println("Solution 2：");
        System.out.println(solution1(7));
    }

    /**
     * <pre>
     *     解法一 - 穷举法
     *
     *      解题关窍：第N个丑数
     *           分解质因数
     *      Ugly Number = 2^n1 * 3^n2 * 5^n3
     *
     *      该解法存在问题：
     *          虽然能得到正确答案，但时间复杂度过大，在题目中运行不通过
     *          因为该方法逐一增加，判断每个是否为丑数；下面试试另一种方式，直接得到下一个丑数，因为下一个丑数必然是之前某个丑数的 2 | 3 | 5倍
     * </pre>
     *
     * @param index 第N个
     * @return 第N个丑数
     */
    private static int solution(int index) {
        int n = 1, count = 0;
        while (count < index){
            int foo = n;
            while (foo % 2 == 0)
                foo /= 2;
            while (foo % 3 == 0)
                foo /= 3;
            while (foo % 5 == 0)
                foo /= 5;
            if (foo == 1){
                System.out.println(n);
                count++;
            }
            n++;
        }
        return --n;
    }

    /**
     * <pre>
     *
     *     解法二 - 联机算法
     *
     * 旧思路：
     *      将所有之前的丑数的2、3、5乘积存入一个数组
     *      下一个丑数必将是数组中最小的大于当前丑数的元素
     *
     *      这种解法也能得出来，以空间换时间
     *      但写起来很麻烦
     *
     * 新思路：
     *      根据 下一个丑数必然是之前某个丑数的 2 | 3 | 5倍，结合之前的思路，其实可以将下一个丑数的值限定在数组的某<em>三个值之中</em>
     *      这个解法用 三个指针（下标）来分别指向应该被 2、3、5乘的元素
     *      归纳：
     *          第一个丑数为1，则index2 = 0，index3 = 0，index5 = 0
     *          1 * 2、1 * 3、1 * 5                                       min：2
     *          1 * 2、1 * 3、1 * 5、2 * 2、2 * 3、2 * 5                 min：3，其中1*2不用比较
     *          1 * 2、1 * 3、1 * 5、2 * 2、2 * 3、2 * 5、3 * 2、3 * 3、3 * 5       min：4，1*2、1*3不用比较
     *      观察以上三个，可得到规律：
     *          进一步推论，下一个丑数的表达式可用<i>min（array[index2] * 2, array[index3] * 3, array[index5] * 5）</i> 来表示
     *          array[0] = 1，先设置index2 = 0，index3 = 0，index5 = 0
     *          array[1] = min(array[0] * 2, array[0] * 3, array[0] * 5) = min(1 * 2, 1 * 3, 1 * 5) = 2；index2 += 1，注意这个下标的移动
     *          array[2] = min(array[1] * 2, array[0] * 3, array[0] * 5) = min(2 * 2, 1 * 3, 1 * 5) = 3；index3 += 1
     *          array[3] = min(array[1] * 2, array[1] * 3. array[0] * 5) = min(2 * 2, 2 * 3, 1 * 5) = 4；index2 += 1
     *          array[4] = min(array[2] * 2, array[1] * 3. array[0] * 5) = min(3 * 2, 2 * 3, 1 * 5) = 5；index5 += 1
     *          当某元素乘2、3、5是下一个丑数，则对应下标移到下一个元素
     *
     * 这个解法有点online algorithm的感觉
     * </pre>
     *
     * @param index N
     * @return      Nth丑数
     * @see <a href="https://www.jianshu.com/p/be262f304745">refer link</a>
     */
    private static int solution1(int index){
        int index2 = 0, index3 = 0, index5 = 0;
        int[] ints = new int[index];
        ints[0] = 1;
        for (int i = 1; i < index; i++) {
            ints[i] = Math.min(ints[index2] * 2, Math.min(ints[index3] * 3, ints[index5] * 5));
            if (ints[i] == ints[index2] * 2)
                index2++;
            if (ints[i] == ints[index3] * 3)
                index3++;
            if (ints[i] == ints[index5] * 5)
                index5++;
        }
        return ints[index - 1];
    }

    private static boolean isPrime(int number){
        for (int i = 2; i < number; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}