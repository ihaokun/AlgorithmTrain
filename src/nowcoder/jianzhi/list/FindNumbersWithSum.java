package nowcoder.jianzhi.list;

import java.util.ArrayList;

/**
 * <pre>
 *     剑指offer - 和为S的两个数组
 *
 *     题目描述：
 *          输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 *     输出描述：
 *          对应每个测试案例，输出两个数，小的先输出。
 *
 *     考点：
 *          知识迁移能力
 *     知识点：
 *          数学
 *
 *     小结：
 *          虽然使用穷举法可以解出此题，见{@link FindNumbersWithSum#solution(int[], int)}
 *          但 最好有时间还是看看题解，看看他们的 <em>优解法</em>，如本题使用的 数学知识 的 双指针夹逼法，
 *          见{@link FindNumbersWithSum#solution2(int[], int)}
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/17 0:34
 */
public class FindNumbersWithSum {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 4, 7, 11, 16}, 10));
        System.out.println(solution1(new int[]{1, 2, 4, 7, 11, 16}, 17));
        System.out.println(solution2(new int[]{1, 2, 4, 7, 11, 16}, 17));
    }

    /**
     * <pre>
     *     采用的 穷举法，{@link SumSOfContinuousSequence "和上一题的穷举有点相似"}
     *     比较耗费空间，应该会有 使用数学知识的更优解
     * </pre>
     *
     * @param array 递增序列
     * @param sum   和
     * @return 和为sum的乘积最小的两个元素，组成的数组
     */
    private static ArrayList<Integer> solution(int[] array, int sum) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>(2);
        for (int i = 0; i < array.length; i++) {
            integers.add(array[i]);
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == sum) {
                    integers.add(array[j]);
                    arrayLists.add(new ArrayList<>(integers));
                    break;
                }
            }
            integers.clear();
        }
        // product 乘积（数学）
        if (arrayLists.size() > 0) {
            integers = arrayLists.get(0);
            for (int i = 1; i < arrayLists.size(); i++) {
                if (integers.get(0) * integers.get(1) > arrayLists.get(i).get(0) * arrayLists.get(i).get(1))
                    integers = arrayLists.get(i);
            }
        }
        return integers;
    }

    /**
     * <pre>
     *     解法二：
     *          实际上还是穷举，虽然通过了题目，但应该并不正确
     * </pre>
     *
     * @param array 递增序列
     * @param sum   和
     * @return 和为sum的乘积最小的两个元素，组成的数组
     */
    @Deprecated
    private static ArrayList<Integer> solution1(int[] array, int sum) {
        ArrayList<Integer> integers = new ArrayList<>(2);
        int index1 = 0, index2 = index1 + 1;
        while (index1 < array.length - 1) {
            if (array[index1] + array[index2] == sum) {
                integers.add(array[index1]);
                integers.add(array[index2]);
                break;
            } else {
                if (++index2 == array.length)
                    index2 = ++index1 + 1;
            }
        }
        return integers;
    }

    /**
     * <pre>
     * 解法三：
     *      采用题解所说的 <strong><em> 双指针 </em>夹逼法</strong>
     *      两个指针分别指向 数组首位和尾位
     *
     *          注意题目所给的 条件 和 要求：
     *              条件：
     *                  递增数组、两个元素
     *              要求：
     *                  如果有多对数字的和等于S，输出两个数的乘积最小的
     *          <pre><strong>
     *          证明过程（<em>使用数学知识</em>）：
     *              若 b > a，且存在 a + b = s
     *                        等同于(a - m) + (b + m) = s；m > 0
     *              则 (a - m)(b + m) = ab - mb + ma - mm
     *                                = ab - m(b - a) - m^2 < ab
     *              可得知
     *                  <em>外层的乘积(product)更小</em>
     *          </strong></pre>
     *
     *          <pre>
     *          <ul>
     *          夹逼过程（类似于二分查找）：
     *              <li>若 array[left] + array[right] > sum，则right应左移</li>
     *              <li>若 array[left] + array[right] < sum，则left应右移</li>
     *              <li>若 array[left] + array[right] = sum，则和正确</li>
     *          </ul>
     *          </pre>
     * </pre>
     *
     * @param array 递增序列
     * @param sum   和
     * @return 和为sum的乘积最小的两个元素，组成的数组
     */
    private static ArrayList<Integer> solution2(int[] array, int sum) {
        ArrayList<Integer> arrayList = new ArrayList<>(2);
        int left = 0, right = array.length - 1;
        while (left < right){
            int summation = array[left] + array[right];
            if (summation < sum)
                left++;
            else if (summation > sum)
                right--;
            else{
                // arrayList.clear();
                arrayList.add(array[left]);
                arrayList.add(array[right]);
                return arrayList;
            }
        }
        return arrayList;
    }

}
