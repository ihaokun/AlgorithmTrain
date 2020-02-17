package nowcoder.jianzhi.list;

import analysis.chapter2_analysis.MaxSubSeqSumSolution4;

/**
 * <pre>
 * 剑指offer - 连续子数组的最大和
 * 题目描述：
 *      HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:
 *      在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
 *      但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
 *      例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 *
 * 简要理解：
 *      序列中找到最大和的子序列
 *
 * 考点：
 *      时间效率
 * 知识点：
 *      数组
 *
 * 小结：
 *      是和 《Data Structures and Algorithm Analysis in Java》third edition 上一题相似的题目，书中有4种解法
 *      后续有时间可以看看第三种解法{@link MaxSubSeqSumSolution4#maxSubSum3(int[])}
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/1 22:25
 */
public class GreatestSumOfSubArray {
    public static void main(String[] args) {
        // init
        // int[] array = {6, -3, -2, 7, -15, 1, 2, 2};
        int[] array = {-2, -8, -1, -5, -9};
        // test
        System.out.println(solutionByExhaustive(array));
        System.out.println(solutionByOnLine(array));
    }

    private static int sum(int[] array) {

        return -1;
    }

    /**
     * 这题做过，在书上看过这题，见{@link MaxSubSeqSumSolution4}，书中给了4种解法<br/>
     * 这里我先使用第二种穷举（exhaustive）的方式来解这道题
     *
     * @param array 序列
     * @return 最大子序列和
     */
    private static int solutionByExhaustive(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                if (sum > max)
                    max = sum;
            }
        }
        return max;
    }

    /**
     * 这是解法4{@link MaxSubSeqSumSolution4#maxSubSum4(int[])}
     * 但之前 书上给的条件是：（为方便起见，如果所有整数均为负数，则最大子序列和为0）
     *      故，该解法不适用这道（包含全为负数情况）的题
     *
     * @param array 序列
     * @return 最大子序列和（全为负数则返回0）
     */
    private static int solutionByOnLine(int[] array) {
        int maxSum = 0, thisSum = 0;
        for (int value : array) {
            thisSum += value;
            if (thisSum > maxSum)
                maxSum = thisSum;
            else if (thisSum < 0)
                thisSum = 0;
        }
        return maxSum;
    }
}