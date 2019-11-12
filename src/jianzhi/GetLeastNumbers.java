package jianzhi;

import algorithms.chapter2_sorting.InsertionSorting;
import algorithms.chapter2_sorting.SelectionSorting;

import java.util.ArrayList;

/**
 * <pre>
 * 剑指offer - 最小的K个数
 *
 * 题目描述：
 *      输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 * 知识点：
 *      数组、高级算法
 * 考点：
 *      时间效率
 *
 * 解法：
 *      这次手写一下排序算法吧，快速排序|冒泡排序，并进行算法分析
 *      补充：这题可能适合使用 <em>选择排序{@link SelectionSorting#sort(Comparable[])}</em> 来解题
 *            原因：题目<em>找出其中<strong>最小的K个</strong>数</em>，那么使用选择排序，可以节省外层循环的次数，即交换次数
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/29 17:57
 */
public class GetLeastNumbers {
    public static void main(String[] args) {
        // init
        // int[] arr = {4, 5, 1, 6, 2, 7, 3, 8};
        int[] arr = {8, 7, 6, 5, 4, 3, 2, 1};
        int k = 4;
        // test
        System.out.println(solution(arr, k));
    }

    private static ArrayList<Integer> solution(int[] input, int k) {
        ArrayList<Integer> integers = new ArrayList<>(k);
        if (input.length >= k){
            bubbleSort(input);
            for (int i = 0; i < k; i++)
                integers.add(input[i]);
        }
        return integers;
    }

    /**
     * <pre>
     * 冒泡排序，使用双重循环
     *      外层循环，每次将最大的值沉到最底下
     *      内层循环，除已沉外的元素，相邻两两比较，交换；循环结束则最大的值被沉到最底下
     *
     * 算法分析：
     *      N - 1       ~   N
     *      N - 1 - i   ~   N
     *      O（n²）
     *
     * wiki：
     *      Bubble Sort是一种最简单了解和实现的算法，简洁，但对于包含大量元素的数列排序很没效率
     *      逐渐被 <em>插入排序{@link InsertionSorting}</em> 替代
     * 杂闻：
     *      名字由来：越小的元素会经由交换慢慢“浮”到数列的顶端；
     *                有时称为<i>鸡尾酒排序</i>，因为算法会从数列的一端到另一端之间穿梭往返
     *
     * 示例（用|分隔）：
     *      8 9 4 5 7 1 3
     *
     *      8 4 5 7 1 3 | 9
     *      4 5 7 1 3 | 8 9
     *      4 5 1 3 | 7 8 9
     *      4 1 3 | 5 7 8 9
     *      1 3 | 4 5 7 8 9
     *      1 | 3 4 5 7 8 9
     *
     * </pre>
     *
     * @param arr 需排序数组
     * @see <a href="https://zh.wikipedia.org/wiki/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F">wiki</a>
     */
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
