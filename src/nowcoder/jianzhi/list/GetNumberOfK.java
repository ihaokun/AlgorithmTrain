package nowcoder.jianzhi.list;

/**
 * <pre>
 *     剑指offer - 数字在排序数组中出现的次数
 *     和之前做的一道题看起来相似，见{@link MoreThanHalfNum "数组中出现次数超过一半的数字"}
 *
 *     题目描述：
 *          统计一个数字在排序数组中出现的次数。
 *
 *      考点：
 *          知识迁移能力
 *      知识点：
 *          数组
 *
 *      解法：
 *          这题比较简单，给定了条件<em>排序数组</em>，那么某数字在 排序数组 中出现次数就很好统计了
 *          猜测这题应该是考察 <em>折半查找 Binary Search</em>，不难，想到的解法可以有3种
 *          {@link analysis.chapter2_analysis.BinarySearch "Binary Search写过的参考-1"}
 *          {@link algorithms.chapter1_fundamentals.BinarySearch "Binary Search写过的参考-2"}
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/10 22:39
 * @see MoreThanHalfNum "数组中出现次数超过一半的数字"
 */
public class GetNumberOfK {
    public static void main(String[] args) {
        // init
        // int[] array = {2, 3, 4, 4, 5, 6};
        // int[] array = {2, 3, 4, 4, 5, 6, 6, 6};
        // int[] array = {2, 3, 4, 5, 6, 6, 6, 7};
        int[] array = {1, 3, 3, 3, 3, 4, 5};
        // test
        System.out.println(solution(array, 2));
        System.out.println(solution1(array, 2));
    }

    /**
     * <pre>
     * 解法一：
     *      很直观，不花里胡哨，就直接顺序查找
     *      并且编译通过了
     * </pre>
     *
     * @param array 有序数组
     * @param k     需要统计的元素
     * @return 元素个数
     */
    private static int solution(int[] array, int k) {
        int count = 0, length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i] == k) {
                count += 1;
                i += 1;
                while (i < length && array[i] == k) {
                    count++;
                    i++;
                }
                break;
            }
        }
        return count;
    }

    /**
     * 解法2：
     * 使用二分查找（自己手写 或 使用JavaAPI）
     * 先找到元素所在下标，再查看两边的相同值元素 进行统计
     *
     * @param array 有序数组
     * @param k     需要统计的元素
     * @return 元素个数
     */
    private static int solution1(int[] array, int k) {
        // Binary Search 得到元素所在下标
        // 手写方式
        int low = 0, high = array.length - 1, mid,
                index = -1;
        while (low <= high) {
            mid = (high + low) >>> 1;
            if (array[mid] > k)
                high = mid - 1;
            else if (array[mid] < k)
                low = mid + 1;
            else {
                index = mid;
                break;
            }
        }
        // Java API方式
        // int index = Arrays.binarySearch(array, k);
        // 统计
        if (index >= 0) {
            int count = 1;
            for (int i = index + 1; i < array.length; i++) {
                if (array[i] == array[index])
                    count++;
                else
                    break;
            }
            for (int i = index - 1; i >= 0; i--) {
                if (array[i] == array[index])
                    count++;
                else
                    break;
            }
            return count;
        }
        return 0;
    }

    /**
     * 解法2 中的 二分查找 提取出来，程序的层次结构会更好一点
     *
     * @param array  有序数组
     * @param target 目标元素
     * @return 元素下标
     */
    private static int binarySearch(int[] array, int target) {
        int low = 0, high = array.length - 1, mid;
        while (low <= high){
            mid = (low + high) >>> 1;
            if (array[mid] < target)
                low = mid + 1;
            else if (array[mid] > target)
                high = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}
