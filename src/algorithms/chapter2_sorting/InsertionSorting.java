package algorithms.chapter2_sorting;

import java.util.Arrays;

/**
 * <pre>
 * 插入排序（Insertion Sort）
 *
 * 自然语言描述：
 *      不断 原一个有序的数组扩容+1，将一个元素插入已有序的数组中，并将该数组重新排序
 *
 * 示例：
 *      9 6 7 5 2 3
 *
 *      9
 *      6 9
 *      6 7 9
 *      5 6 7 9
 *      2 5 6 7 9
 *      2 3 5 6 7 9
 *
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/31 23:22
 */
public class InsertionSorting extends Sorting {
    public static void main(String[] args) {
        // Comparable[] comparables = {7,9,8,2};
        Comparable[] comparables = {7,6,5,4,3,2,1};
        new InsertionSorting().sort(comparables);
        System.out.println(isSort(comparables));
        System.out.println(Arrays.toString(comparables));
    }

    /**
     * 实现：
     *      对外层索引前的元素，不断加一个元素（当前索引所在元素），两两比较，以重新排序
     *
     * 算法分析：
     *      N
     *      N
     *      O(N²)
     *
     *      类似冒泡，内循环结束，将最小的元素浮到数组顶部
     *      比较次数：worst: (1 + 2 + ... + N-1) = N² / 2
     *      交换次数：worst: N² / 2
     *      读数组一次
     *
     *
     * @param arr 需排序数组
     */
    @Override
    public void sort(Comparable[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr[j], arr[j - 1]))
                    exchange(arr, j, j - 1);
            }
        }
    }
}
