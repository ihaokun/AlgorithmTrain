/**
 * 最大子序列和问题
 * 问题描述：
 * 给定（可能有负的）整数 A1、A2，...，An，求
 *      i
 *      ∑ Ak    的最大值（为方便起见，如果所有整数均为负数，则最大子序列和为0）
 *      k=i
 *
 * 4种解法
 *
 * @author ihaokun
 * @date 2019/7/31 21:15
 */
public class MaxSubSumSolution {

    public static void main(String[] args) {
        int[] sequence = {-2, 11, -4, 13, -5, -2};
        System.out.println("maxSubSum1(sequence) = " + maxSubSum1(sequence));
        System.out.println("maxSubSum(sequence) = " + maxSubSum(sequence));
    }

    /**
     * 解法1：穷举
     * 计算该方法的时间单元数：
     * 忽略常数和低阶项
     * 嵌套结构（3重循环中）执行 N³
     * if结构（2重循环中）执行 N²
     * <p>
     * 最终为：O(N³)
     *
     * @param sequence 数组
     * @return 最大子序列之和
     */
    public static int maxSubSum1(int[] sequence) {
        int maxSum = 0;                                         // 1
        for (int i = 0; i < sequence.length; i++) {             // N
            for (int j = i; j < sequence.length; j++) {         // N - i，假设最坏情况为N
                int thisSum = 0;                                // 1
                for (int k = i; k <= j; k++)                    // j - i + 1，同样假设最坏情况为N
                    thisSum += sequence[k];
                if (thisSum > maxSum)
                    maxSum = thisSum;                           // if结构在双重for循环中，最坏情况为N²
            }
        }
        return maxSum;
    }

    /**
     * 自己写的 通过归纳法 得到的 通过穷举 解题
     * <p>
     * 思路草略如下：
     * 最大子序列和问题：
     * <p>
     * {-2, 11, -4, 13, -5, -2}
     * <p>
     * 穷举：6 + 5 + 4 + 3 + 2 + 1
     * -2
     * -2 + 11
     * -2 + 11 + (-4)
     * ...
     * 11
     * 11 + (-4)
     * ...
     * <p>
     * 思考代码的实现，
     * 实际是3重循环结构：最外层是序列的遍历；次外层是对应元素的子序列个数；内层是子序列和的计算
     *
     *
     * @param sequence 数组
     * @return 最大子序列和
     * @see #maxSubSum1     观察可知，和上面的方法是同一原理
     * 是同一个原理，不过这个是通过思考手写的
     */
    static int maxSubSum(int[] sequence) {
        int maxSubSum = 0;
        for (int i = 0; i < sequence.length; i++) {
            for (int j = 0; j < sequence.length - i; j++) {
                int subSum = 0;
                for (int k = i; k <= j; k++) {
                    subSum += sequence[k];
                }
                if (subSum > maxSubSum) {
                    maxSubSum = subSum;
                }
            }
        }
        return maxSubSum;
    }

}
