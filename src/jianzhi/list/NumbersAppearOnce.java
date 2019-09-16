package jianzhi.list;

import java.util.*;

/**
 * <pre>
 *     剑指offer - 数组中只出现一次的数字
 *
 *     详细描述：
 *          一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 *
 *     考点：
 *          知识迁移能力
 *     知识点：
 *          数组
 *
 *     解法：
 *          均是 第一步排序，再对数据进行处理：
 *          解法一是自己写的，解法比较原始，单单找出规律，但用着也不错，比较节约空间
 *          解法二使用了Stack的性质，代码更短，但效率不一定比解法一的效率高
 *
 *          解法三使用了Set的性质，代码也很短，且不用和解法一二一样进行第一步排序
 *
 *     小结：
 *          推荐解法是 解法三，代码简单易懂，时间复杂度也小，O（N）
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/14 22:19
 */
public class NumbersAppearOnce {
    public static void main(String[] args) {
        // solution(nums, new int[1], new int[1]);
        // init
        // int[] nums = {1, 1, 2, 3, 3, 4};
        // int[] nums = {1, 2, 2, 3, 3, 4};
        int[] nums = {1, 1, 2, 3, 3, 4, 5, 5};
        // test
        solution(nums, new int[1], new int[1]);
        solution1(nums, new int[1], new int[1]);
        solution2(nums, new int[1], new int[1]);
    }

    /**
     * <pre>
     * num1,num2分别为长度为1的数组。传出参数
     * 将num1[0],num2[0]设置为返回结果
     *
     * 解法一：
     *      该解法使用的是 <em>找出规律解题</em>，<i>解法比较原始</i>
     *
     * </pre>
     *
     * @param array 原数组
     * @param num1  第一个长度为1的数组
     * @param num2  第二个长度为1的数组
     */
    private static void solution(int[] array, int[] num1, int[] num2) {
        // 先使用快排 使 数组有序
        Arrays.sort(array);
        // 利用Java语言特性，带标签的循环结构
        label:
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i + 1])
                i++;
            else {
                num1[0] = array[i];
                System.out.println(num1[0]);
                for (int j = i + 1; j < array.length; j++) {
                    //XXX 注意存在 最后一个元素是 单个元素（非成对元素） 的情况
                    if (j != array.length - 1 && array[j] == array[j + 1])
                        j++;
                    else {
                        num2[0] = array[j];
                        System.out.println(num2[0]);
                        break label;
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * num1,num2分别为长度为1的数组。传出参数
     * 将num1[0],num2[0]设置为返回结果
     *
     * 解法二：
     *      <em>使用 数据结构<strong>Stack</strong> 的特性 来解题</em>
     *
     *      和解法一对比：
     *          代码比解法一少，但运行时间不一定比解法一少
     *
     * </pre>
     *
     * @param array 原数组
     * @param num1  第一个长度为1的数组
     * @param num2  第二个长度为1的数组
     */
    private static void solution1(int[] array, int[] num1, int[] num2) {
        // 第一步同样是排序
        Arrays.sort(array);
        // 利用 Stack 的特性
        Stack<Integer> stack = new Stack<>();
        for (int i : array) {
            if (!stack.isEmpty() && stack.peek() == i)
                stack.pop();
            else
                stack.push(i);
        }
        num2[0] = stack.pop();
        num1[0] = stack.pop();
        System.out.println(num1[0]);
        System.out.println(num2[0]);
    }

    /**
     * <pre>
     * num1,num2分别为长度为1的数组。传出参数
     * 将num1[0],num2[0]设置为返回结果
     *
     * 解法三：
     *      <em>使用 数据结构<strong>Set</strong> 的特性 来解题</em>
     *
     *      和解法一、二对比：
     *          代码也很少；且不用先一步排序；优于1,2
     *
     *      Set可转为 数组 或 Iterator对象，再赋值给两个容量为1的数组
     *
     * </pre>
     *
     * @param array 原数组
     * @param num1  第一个长度为1的数组
     * @param num2  第二个长度为1的数组
     */
    private static void solution2(int[] array, int[] num1, int[] num2) {
        // 使用Set
        Set<Integer> set = new LinkedHashSet<>();
        for (int i : array) {
            if (set.contains(i))
                set.remove(i);
            else
                set.add(i);
        }
        System.out.println(set);
        // 数组方式
        /*Integer[] integers = set.toArray(new Integer[2]);
        num1[0] = integers[0];
        System.out.println(num1[0]);
        num2[0] = integers[1];
        System.out.println(num2[0]);*/
        // Iterator对象方式
        Iterator<Integer> iterator = set.iterator();
        num1[0] = iterator.next();
        System.out.println(num1[0]);
        num2[0] = iterator.next();
        System.out.println(num2[0]);
    }

    /**
     * <pre>
     * num1,num2分别为长度为1的数组。传出参数
     * 将num1[0],num2[0]设置为返回结果
     *
     * 解法四：
     *      <em>使用 <strong>位运算^异或</strong>  来解题</em>
     *      这个解法讨论区比较推崇，但这边已经写过3个解法了，这个就先不写了，讨论可见<a href="https://www.nowcoder.com/questionTerminal/e02fdb54d7524710a7d664d082bb7811?answerType=1&f=discussion">refer link</a>
     *
     * </pre>
     *
     * @param array 原数组
     * @param num1  第一个长度为1的数组
     * @param num2  第二个长度为1的数组
     * @see <a href="https://www.nowcoder.com/questionTerminal/e02fdb54d7524710a7d664d082bb7811?answerType=1&f=discussion">refernce link</a>
     */
    private static void solution3(int[] array, int[] num1, int[] num2) {
        int orx = array[0];
        for (int i = 1; i < array.length; i++) {
            orx ^= array[i];
        }
    }

}