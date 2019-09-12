package jianzhi.list;

import java.util.Arrays;

/**
 * <pre>
 * 剑指offer - 数组中出现次数超过一半的数字
 *
 * 题目描述：
 *      数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *      例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 * 考点：
 *      时间效率
 * 知识点：
 *      数组
 *
 * 解题思路：
 *      1.原数组排序（使用了{@link Arrays#sort(int[])}，Java优化的快速排序）
 *      2.将原数组按序添加入String中，添加的过程中进行统计，每个要加入的和String尾部字符相同，则count++；否则count归1
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/29 16:45
 */
public class MoreThanHalfNum {
    public static void main(String[] args) {
        // init
        int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        // test
        System.out.println(solution(arr));
    }

    private static int solution(int[] array) {
        int half = array.length >>> 1;
        Arrays.sort(array);
        int count = 1;
        StringBuilder builder = new StringBuilder(array.length);
        for (int i : array) {
            if (builder.length() > 0 && builder.substring(builder.length() - 1).equals(String.valueOf(i)))
                count++;
            else
                count = 1;
            if (count > half)
                return i;
            builder.append(i);
        }

        return 0;
    }
}