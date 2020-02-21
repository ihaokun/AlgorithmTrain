package nowcoder.jianzhi.list;

import algorithms.chapter2sorting.MergeSortingTopDown;
import algorithms.chapter2sorting.MergeSorting;

/**
 * <pre>
 *     剑指offer - 数组中的<em>逆序对</em>
 *     对：即为双、二
 *
 *      考点：
 *          时间、空间效率的平衡
 *      知识点：
 *          数组、排序算法（Merge Sort（分治策略divide-and-conquer））
 *
 *     题目描述：
 *          在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 *          输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 *          （数组中元素无重复）
 *
 *      三种解法：
 *          解法一穷举；解法二不太好，是自己想的，思路比较歪；第三种解法好一点
 *          这题和 Merge Sort 关联很大，可以参考一下{@link MergeSorting}
 *                                                  {@link MergeSortingTopDown}
 *
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/5 22:44
 * @see <a href="https://baike.baidu.com/item/%E9%80%86%E5%BA%8F%E5%AF%B9/11035554">逆序对baike</a>
 * @see <a href="https://zh.wikipedia.org/wiki/%E9%80%86%E5%BA%8F%E5%AF%B9">逆序对wiki</a>
 */
public class InversePairs {
    public static void main(String[] args) {
        // int[] array = {7, 6, 5, 4};
        // int[] array = {1,2,3,4};
        // int[] array = {1, 2, 3, 4, 5, 6, 7, 0};
        // int[] array = {7, 5, 6, 4};
        int[] array = {364,637,341,406,747,995,234,971,571,219,993,407,416,366,315,301,601,650,418,355,460,505,360,965,516,648,727,667,465,849,455,181,486,149,588,233,144,174,557,67,746,550,474,162,268,142,463,221,882,576,604,739,288,569,256,936,275,401,497,82,935,983,583,523,697,478,147,795,380,973,958,115,773,870,259,655,446,863,735,784,3,671,433,630,425,930,64,266,235,187,284,665,874,80,45,848,38,811,267,575};
        System.out.println(solution(array));
        System.out.println(solutionByMerge(array));
    }

    private static int divisor = 1000000007;

    /**
     * 解法一 穷举法
     * 使用 双重循环
     *
     * 答案正确，但时间复杂度过大，导致该题运行超时
     *
     * 分析该解法：
     * 双重for循环，时间复杂度 O(N²)
     *
     * @param array 数组
     * @return 数组中逆序对的个数 % 1000000007 的值
     */
    private static int solution(int[] array) {
        long count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j])
                    count++;
            }
        }
        return (int) (count % divisor);
    }

    /**
     * <pre>
     * 穷举法 时间复杂度O(N²)过大；那么bubble、selection、insertion三方法都不能用
     * 尝试参考 <em>归并排序（Merge Sort）；</em>
     * <i>使用额外的内存开销，以空间换时间</i>
     * </pre>
     *
     * @param array 数组
     * @return 数组中逆序对的个数 % 1000000007 的值
     */
    //辅助数组
    private static int[] aux;
    private static int solutionByMerge(int[] array) {
        aux = new int[array.length];
        division(array, 0, array.length - 1);
        /*for (int i : array) {
            System.out.println(i);
        }*/
        // 这题有点坑的地方，因为输入范围过大，必须使用long类型，截尾得到int值返回（以后做题还要注意下题目的 XXX 输入情况）
        return (int) (count % divisor);
    }

  // Top approach Down 的归并过程
  // 注意：low和high均为数组下标
    private static void division(int[] array, int low, int high) {
        if (low < high){
          // int mid = (low + high) >>> 1;
          int mid = (high + low) / 2;
          division(array, low, mid);
          division(array, mid + 1, high);
          merge(array, low, mid, high);
        }
    }

    private static long count = 0;
    private static void merge(int[] array, int left, int mid, int right) {
        // 归并
      System.arraycopy(array, left, aux, left, right + 1 - left);
        //XXX 解法二：在排序之前计数，（当左下标所在元素大于右下标所在元素时，右下标右移，直至不再大于它；左下标右移一位；右下标之前元素均小于该左下标元素）
        /*int indexL = left, indexR = mid + 1;
        while (indexL <= mid && indexR <= right) {
            if (aux[indexL] > aux[indexR]) {
                indexR++;
                while (indexR <= right && aux[indexL] > aux[indexR])
                    indexR++;
                indexR--;
                count += (indexR - mid);
            }
            indexL++;
        }*/
        // 排序
        int leftPos = left, rightPos = mid + 1;
        for (int i = left; i <= right; i++) {
            // 左数组用尽
            if (leftPos > mid) {
                array[i] = aux[rightPos++];
            }// 右数组用尽
            else if (rightPos > right) {
                array[i] = aux[leftPos++];
            }// 左数组元素 < 右数组元素
            else if (aux[leftPos] < aux[rightPos]) {
                array[i] = aux[leftPos++];
            }//XXX 解法三 左数组元素 > 右数组元素；排序过程中，顺带统计 逆序对（左下标之后的元素，均大于该右下标所在元素）
            else if (aux[rightPos] < aux[leftPos]) {
                array[i] = aux[rightPos++];
                count+=(mid - leftPos + 1);
            }
        }
    }
}
