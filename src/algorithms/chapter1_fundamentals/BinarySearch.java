package algorithms.chapter1_fundamentals;

import java.util.Arrays;

/**
 * 《Algorithms》fourth edition
 *
 * @author ihaokun
 * @date 2019/8/30 17:53
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] whiteList = {1,2,3,4,5};
        // Arrays的快速排序使之有序
        Arrays.sort(whiteList);
        System.out.println(Arrays.toString(whiteList));
        // 是否位于白名单中
        System.out.println(rank(whiteList, 1));
    }

    /**
     * 简单分析一下该算法
     * N = high - low
     * 每次循环 N >>> 1
     * 故大O标记法应为 O(logN)
     *
     * @param arr 有序数组
     * @param key 需要查找的值
     * @return 查找结果
     */
    private static int rank(int[] arr, int key){
        int low = 0, high = arr.length - 1;
        // 仅当找到key 或 查找范围为空时结束方法
        while (low < high){
            int mid = (low + high) >>> 1;
            if (key > arr[mid])
                low = mid + 1;
            else if (key < arr[mid])
                high = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}
