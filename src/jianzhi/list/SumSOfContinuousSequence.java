package jianzhi.list;

import java.util.ArrayList;

/**
 * <pre>
 *     剑指offer - 和为S的连续子序列
 *
 *     题目描述：
 *          小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 *          但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 *          没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *     输出描述：
 *          输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 *
 *     考点：
 *          知识迁移能力
 *     知识点：
 *          穷举
 *
 *     关联：
 *          从题名看，就和之前做过的有关联，如{@link analysis.chapter2_analysis.MaxSubSeqSumSolution4 "算法分析 一书中的四种解法"}
 *          还有{@link GreatestSumOfSubArray "剑指offer - 连续子数组的最大和"}
 *
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/16 0:48
 */
public class SumSOfContinuousSequence {
    public static void main(String[] args) {
        System.out.println(solution(3));
    }
    private static ArrayList<ArrayList<Integer>> solution(int sum) {
        // 采用第二种穷举法解题
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        int summation = 0;
        for (int i = 1; i < sum; i++) {
            arrayList.add(i);
            summation += i;
            for (int j = i + 1; j < sum; j++) {
                arrayList.add(j);
                summation += j;
                if (summation == sum)
                    arrayLists.add(new ArrayList<>(arrayList));
            }
            summation = 0;
            arrayList.clear();
        }
        return arrayLists;
    }
}
