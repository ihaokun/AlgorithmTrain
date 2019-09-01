package analysis.chapter2;

/**
 * 最大子序列和问题<br/>
 * 问题描述：<br/>
 * 给定（可能有负的）整数 A₁、A₂，...，An<br/>
 * 求<br/>
 * <p>
 *       i<br/>
 *      ∑ Ak<br/>
 *      k=i
 * </p>
 * 的最大值（为方便起见，如果所有整数均为负数，则最大子序列和为0）<br/>
 * 4种解法:
 * <p>
 * 解法1.2均为穷举，不过2在1的方法上进行了优化，减少了循环的次数；
 * 3采用分治；
 * 4联机算法，最优解，是2的改进
 * </p>
 *
 * @author ihaokun
 * @date 2019/7/31 21:15
 */
public class MaxSubSeqSumSolution {

    public static void main(String[] args) {
        int[] sequence = {-2, 11, -4, 13, -5, -2};
        // int[] sequence = {11, -4};
        System.out.println("maxSubSum1(sequence) = " + maxSubSum1(sequence));
        System.out.println("maxSubSum(sequence) = " + maxSubSum(sequence));
        System.out.println("maxSubSum2(sequence) = " + maxSubSum2(sequence));
        System.out.println("maxSubSum3(sequence) = " + maxSubSum3(sequence));
        System.out.println("maxSubSum4(sequence) = " + maxSubSum4(sequence));
    }

    /**
     * 解法1：穷举
     *
     * 思路草略如下：
     * {-2, 11, -4, 13, -5, -2}
     *
     * 穷举：6 + 5 + 4 + 3 + 2 + 1
     *
     * -2
     * -2 + 11
     * -2 + 11 + (-4)
     * ...
     * 11
     * 11 + (-4)
     * ...
     *
     * 计算该方法的时间单元数：
     * 忽略 前导常数 和 低阶项（常数项）
     *
     * 嵌套结构（3重循环中）执行 N³
     * if结构（2重循环中）执行 N²
     * 最终为：O(N³ + N²) = max(O(N³),O(N²)) = O(N³)
     *
     * @param sequence 序列
     * @return 最大子序列之和
     */
    private static int maxSubSum1(int[] sequence) {
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
     *
     * 思考代码的实现，
     * 实际是3重循环结构：最外层是数组元素的遍历；次外层是对应元素的子序列组合遍历；内层是子序列和的计算
     *
     * @param sequence 数组
     * @return 最大子序列和
     * @see #maxSubSum1     观察可知，和上面的方法是同一原理，不过这个是通过思考手写的
     */
    static int maxSubSum(int[] sequence) {
        int maxSum = 0;
        for (int i = 0; i < sequence.length; i++) {
            for (int j = 0; j < sequence.length - i; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += sequence[k];
                }
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    /**
     * 解法2：是解法1的改进版，同样是穷举
     * 与解法1对比，仅减少了一层循环结构
     * 减少的第三层循环结构，是不必要的；可以在第二层循环中就进行判断，同样可以得到正确结果，并减少了执行时间
     *
     * 时间复杂度为：O(N²)
     *
     * @param sequence 数组
     * @return 最大子序列和
     */
    private static int maxSubSum2(int[] sequence) {
        int maxSum = 0;
        for (int i = 0; i < sequence.length; i++) {
            int thisSum = 0;
            for (int j = i; j < sequence.length; j++) {
                thisSum += sequence[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * 解法3：<br/>
     * 相较之前的解法1.2有不同的地方，需要添加数组的左右端下标作为参数<br/>
     * 采用分治策略，使用递归实现<br/>
     * 时间复杂度：O(N logN)
     *
     * @param sequence 序列
     * @return 最大子序列和
     * @see #maxSubSumRec
     */
    private static int maxSubSum3(int[] sequence) {
        return maxSubSumRec(sequence, 0, sequence.length - 1);
    }

    /**
     * 该方法采用一种“分治(divide-and-conquer)”策略<br/>
     * 思路：<br/>
     * divide：<br/>
     * &nbsp&nbsp 将问题分成 两个 大致相等的 子问题，然后递归地对它们求解<br/>
     * conquer：<br/>
     * &nbsp&nbsp 将两个问题的解 修补到一起并可能再做些少量的附加工作，最后得到整个问题的解<br/><br/>
     * 计算该方法的时间复杂度：<br/>
     * 首先，用T(N)表示该算法所需时间单元。<br/>
     * 考虑基准情形，则T(1) = 1 <br/>
     * 两个递归部分，所需均为T(N/2)，故递归需要 2T(N/2)<br/>
     * 循环求得的 中间的最大子序列和 过程，需要O(N)<br/>
     * 故 T(N) = 2T(N/2) + O(N)，T(1) = 1；且O(N)可以用N替换，则T(N) = 2T(N/2) + N
     * 当 N = 2的k幂，则 k = ㏒N
     * T(1) = 1，T(2) = 2*1 + 2 = 4，T(4) = 2*4 + 4 = 12，T(8) = 2*12 + 8 = 32
     * T(1 = 2⁰) = 1 * 1 = 1，T(2 = 2¹) = 2 * 2 = 4，T(4 = 2²) = 4 * 3 = 12，T(8 = 2³) = 8 * 4 = 32，
     *     推得：T(N) = N * (k + 1)
     *               = N * （㏒N + 1）
     *               = N ㏒N + N
     *      故 O(N ㏒N)
     *
     * @param sequence 数组
     * @param left     数组最左端下标
     * @param right    数组最右端下标
     * @return 最大子序列和
     */
    private static int maxSubSumRec(int[] sequence, int left, int right) {
        // base case
        if (left == right) {
            return Math.max(sequence[left], 0);
        }
        // 递归求得 分开的两半各自的最大子序列和
        int center = (left + right) >>> 1;
        int maxLeftSum = maxSubSumRec(sequence, left, center);
        int maxRightSum = maxSubSumRec(sequence, center + 1, right);
        // 横跨两部分且通过中间的最大子序列和
        int maxLeftBoardSum = 0, leftBoardSum = 0;
        for (int i = center; i >= left; i--) {
            leftBoardSum += sequence[i];
            if (leftBoardSum > maxLeftBoardSum) {
                maxLeftBoardSum = leftBoardSum;
            }
        }
        int maxRightBoardSum = 0, rightBoardSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBoardSum += sequence[i];
            if (rightBoardSum > maxRightBoardSum) {
                maxRightBoardSum = rightBoardSum;
            }
        }
        // return Max（左半边最大子序列和，右半边最大子序列和，横跨两部分且通过中间的最大子序列和）
        return Math.max(Math.max(maxLeftSum, maxRightSum), (maxLeftBoardSum + maxRightBoardSum));
    }

    /**
     * 解法4：<br/>
     * 最优解，算法分析也很简单，O(N)，线性<br/>
     * 但正确性不是那么容易看出来，重点在于else if的判断
     *
     * 模拟流程：
     * {-2, 11, -4, 13, -5, -2}
     *
     * 0 + (-2) 0
     * 0 + 11 11
     * 11 + (-4) 7
     * 7 + 13 20
     * 20 + (-5) 15
     * 15 + (-2) 13
     *
     * @param arr 序列
     * @return 最大子序列和
     */
    static int maxSubSum4(int[] arr) {
        int maxSum = 0, thisSum = 0;
        for (int value : arr) {
            thisSum += value;
            if (thisSum > maxSum)
                maxSum = thisSum;
            else if (thisSum < 0)
                thisSum = 0;
        }
        return maxSum;
    }

}
