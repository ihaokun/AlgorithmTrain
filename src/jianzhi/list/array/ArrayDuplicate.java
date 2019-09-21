package jianzhi.list.array;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 *     剑指offer - 数组中重复的数字
 *
 *     题目描述：
 *          在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 *          数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
 *          请找出数组中任意一个重复的数字。
 *          例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 *
 *     考点：
 *          数组
 *     知识点：
 *          数组
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/21 1:13
 */
public class ArrayDuplicate {
    public static void main(String[] args) {
        int[] array = {2,3,1,0,2,5,3};
        System.out.println(duplicate(new int[]{}, array.length, new int[1]));
        System.out.println(duplicate(array, array.length, new int[1]));
    }

    /**
     * <pre>
     *      解法：
     *          使用了Set的特性
     * </pre>
     *
     * @param numbers     an array of integers
     * @param length      the length of array numbers
     * @param duplication (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
     *                    Here duplication like pointor in C/C++, duplication[0] equal *duplicatio
     *                    这里要特别注意~返回任意重复的一个，赋值duplication[0]
     * @return true if the input is valid, and there are some duplications in the array number
     *          otherwise false
     */
    private static boolean duplicate(int[] numbers, int length, int[] duplication) {
        if (numbers == null || length == 0)
            return false;
        Set<Integer> set = new HashSet<>();
        for (int number : numbers) {
            if (!set.add(number)) {
                duplication[0] = number;
                return true;
            }
        }
        return false;
    }
}
