package algorithms.chapter2sorting;

import java.util.Arrays;

/**
 * <pre>
 * 选择排序（Selection Sort）
 *
 * 自然语言描述：
 *      不断<em>选择</em>剩余元素中的最小者，并将其和剩余数组的首元素交换位置（相当于 有序数组的 最末位）
 *      相当于分成了两个数组，前面的数组是排序了的，后面的数组是剩余未排序的元素
 *
 * 示例：
 *      9 5 4 6 8
 *
 *      4
 *      4 5
 *      4 5 6
 *      4 5 6 8
 *      4 5 6 8 9
 *
 * 和 Bubble Sort{@link nowcoder.jianzhi.GetLeastNumbers#bubbleSort(int[])} 比较一下
 *      相同：
 *          时间复杂度相同，都是O(N²)
 *          都使用 双重循环
 *      不同：
 *          Bubble一次外层循环结束，最大的元素被沉入数组底层；而Selection则是最小的元素加入前数组
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/31 20:46
 */
public class SelectionSorting extends Sorting {
    public static void main(String[] args) {
        Comparable[] comparables = {7, 9, 8, 2};
        new SelectionSorting().sort(comparables);
        System.out.println(isSort(comparables));
        System.out.println(Arrays.toString(comparables));
    }

    /**
     * 实现：
     *      外层索引后的元素，两两比较，得到最小元素，和首元素交换位置
     *      则索引前的元素，不停末尾加1，最后得到有序数组
     *
     * 算法分析：
     *      N
     *      N
     *      O(N²)
     *
     *      数组只需读一次
     *      比较次数：N * (N-1 + N -2 + ... + 1) = N² /2
     *      交换次数：N
     *
     * @param arr 需排序数组
     */
    @Override
    public void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (less(arr[j], arr[minIndex]))
                    minIndex = j;
            }
            exchange(arr, i, minIndex);
        }
    }
}
