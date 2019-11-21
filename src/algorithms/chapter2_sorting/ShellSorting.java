package algorithms.chapter2_sorting;

import java.util.Arrays;

/**
 * <pre>
 * 希尔排序（Shell Sort）
 *      又称为 <em>递减增量排序（Diminishing Increment Sort）</em>
 *      是基于 <em>插入排序(Insertion Sort){@link InsertionSorting#sort(Comparable[])}</em> 改进的算法
 *
 *      关键：
 *          增量Increment（即为 元素之间的间距interval）
 *
 *      杂闻：
 *          该方法因D.L.Shell于1959年提出而得名
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/1 2:12
 * @see <a href="https://zh.wikipedia.org/wiki/%E5%B8%8C%E5%B0%94%E6%8E%92%E5%BA%8F">wiki<a/>
 */
public class ShellSorting extends Sorting {
    public static void main(String[] args) {
        Comparable[] arr = {6, 9, 5, 7, 1, 3};
        new ShellSorting().sort(arr);
        System.out.println(isSort(arr));
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 基于 插入排序 实现的ShellSort
     * 加入一个interval，间距值
     *
     * 分析该算法：
     *
     *
     * @param arr 需排序数组
     */
    @Override
    public void sort(Comparable[] arr) {
        int interval = 1;
        while (interval < arr.length)
            interval = interval * 3 + 1;
        while (interval >= 1) {
            for (int i = interval; i < arr.length; i++) {
                for (int j = i; j >= interval; j -= interval) {
                    if (less(arr[j], arr[j - interval]))
                        exchange(arr, j, j - interval);
                }
            }
            interval /= 3;
        }
    }
}
