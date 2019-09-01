package analysis.chapter2;

/**
 * 《Data Structure and Algorithm Analysis》3rd
 *
 * @author ihaokun
 * @date 2019/8/5 16:41
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(binarySearch(arr, 3));
    }

    /**
     * 二分|折半 查找
     *
     * 计算时间复杂度：
     * 分析：循环体内工作花费O(1)，需确定循环次数
     *       初始 right - left = N - 1，退出情形为 right - left < 0，每循环一次 right - left的值将折半
     *       循环次数最多为 (㏒(N - 1)) + 2
     *       因此，运行时间是O(logN)
     *
     * @param arr 数组
     * @param target 被查找元素
     * @return 元素下标
     */
    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (arr[middle] == target)
                return middle;
            else if (arr[middle] > target)
                left = middle + 1;
            else if (arr[middle] < target)
                right = middle - 1;
        }
        return -1;
    }
}
