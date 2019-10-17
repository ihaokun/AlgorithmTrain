package algorithms.chapter2_sorting;

import analysis.chapter2_analysis.MaxSubSeqSumSolution4;

/**
 * <pre>
 *     归并排序（Merge Sort）
 *     定义：
 *          一种<em>递归</em>排序算法
 *          该算法基于<em>归并</em>这个简单的操作，即将两个有序的数组通过递归操作，归并成一个更大的有序的数组
 *
 *     基于抽象方法
 *     运用 分治（递归），可以参见 第三种解法{@link MaxSubSeqSumSolution4#solution3(int[])}
 *                                         新{@link MergeSort2}
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/6 6:19
 */
public class MergeSort extends SortTemplate {
    public static void main(String[] args) {
        Comparable[] ints = {6, 4, 3, 2};
        new MergeSort().sort(ints);
    }

    /** 归并所需辅助数组，即额外内存 */
    private static Comparable[] aux;

    /**
     * 自顶向下 的 归并方法
     * O(N logN)
     *
     * @param arr 需排序数组
     */
    @Override
    public void sort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        sortRecursive(arr, 0, arr.length - 1);
        for (Comparable comparable : arr)
            System.out.println(comparable);
    }

    /**
     * 递归
     * 递归调用的一般形式：传递输入的数组，及其左边界下标和右边界下标
     * 和{@link MaxSubSeqSumSolution4#maxSubSumRec(int[], int, int)}同一个原理
     *
     * @param arr 需排序数组
     * @param low 数组左边界下标 left border index
     * @param high 数组右边界下标 right border index
     */
    private static void sortRecursive(Comparable[] arr, int low, int high){
        if (high <= low)
            return;
        int mid = low + (high - low) / 2;
        // 分（divide）：两个数组分别通过递归，得到两个有序的数组
        sortRecursive(arr, low, mid);
        sortRecursive(arr, mid + 1, high);
        // 治（conquer）：将两个有序数组 归并（merge） 为一个有序数组
        merge(arr, low, mid, high);
    }

    private static void merge(Comparable[] arr, int low, int mid, int high) {
        // 原地归并的抽象方法
        // 原地排序算法：除了 函数调用所需的栈 和 固定数目的实例变量 外，无需额外内存
        int i = low, j = mid + 1;
        // for循环的一个替代API
        if (high + 1 - low >= 0)
            System.arraycopy(arr, low, aux, low, high + 1 - low);
        // 归并回arr
        for (int k = low; k <= high; k++) {
            // 左半边用尽
            if (i > mid)
                arr[k] = aux[j++];
            // 右半边用尽
            else if (j > high)
                arr[k] = aux[i++];
            // 右半边当前元素小于左半边当前元素
            else if (less(aux[j], aux[i]))
                arr[k] = aux[j++];
            // 右半边当前元素大于左半边当前元素
            else
                arr[k] = aux[i++];
        }
    }

}
