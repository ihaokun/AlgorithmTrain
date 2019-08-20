package offer.stack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 剑指offer - 栈的压入、弹出序列
 * 题目描述：
 *      输入两个整数序列，
 *      第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 *      假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 *      （注意：这两个序列的长度是相等的）
 * 考察知识点：
 *      栈（栈的压入、弹出规则）
 * case率：
 *      16 - 66 - 100
 * 解题思路：
 *      人脑的思路还是比较简单的，但关键点在于 将其 转化为 代码实现
 *      要注意：
 *          1. 弹出序列的下一个 一定是 压入序列的最后一个 | 压入序列上一个的左右元素之一
 *          2. 当仅剩1个时，只要两两比较就可以了
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/16 18:40
 */
public class StackSeq {
    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        // 将 数组 转为 List（Java 8 新特性，Stream流的方式，将 基本类型数组 转为 对应包装类对象list）
        List<Integer> pushSeq = Arrays.stream(pushA).boxed().collect(Collectors.toList());
        List<Integer> popSeq = Arrays.stream(popA).boxed().collect(Collectors.toList());
        while (!popSeq.isEmpty()) {
            // 仅剩1个的情况
            if (popSeq.size() == 1) {
                return popSeq.get(0).equals(pushSeq.get(0));
            }
            // 获取对应的下一个元素所在下标
            int index = pushSeq.indexOf(popSeq.get(0));
            popSeq.remove(0);
            int nextIndex = pushSeq.indexOf(popSeq.get(0));
            if (Math.abs(nextIndex - index) == 1 || nextIndex == pushSeq.size() - 1)
                pushSeq.remove(index);
            else
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] pushA = {1, 2, 3, 4, 5};
        int[] popA = {3, 5, 4, 2, 1};
        System.out.println(IsPopOrder(pushA, popA));
    }
}
