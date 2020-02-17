package nowcoder.jianzhi.list;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <pre>
 * 剑指offer - 把数组<i>排</i>成最小的数
 *
 * 知识点：
 *      数组
 * 考点：
 *      时间效率
 *
 * 题目描述：
 *      输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *      例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 *
 * 本题使用解法：
 *      与之前 <em>剑指offer - 字符串的排列{@link nowcoder.jianzhi.StringPermutation}</em>，使用了同一个解法
 *      后续若有新的解法，再行补充
 *
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/3 23:16
 */
public class Array2MinNumber {
    public static void main(String[] args) {
        // init
        int[] numbers = {3, 32, 321};
        // test
        System.out.println(printMinNumber(numbers));
    }

    /**
     * <pre>
     * 试分析题意：
     *      根据给出条件，预估本题需要用到String、StringBuilder等
     *
     * 思路一：
     *      使用离散数学（组合数学）中排列的思想，得到所有排列的可能，再获得所有排列可能中最小的可能；
     *      但该方法所需代码量有点多，会比较耗时
     *
     * </pre>
     *
     * @param numbers 正整数数组
     * @return 数组元素排成的最小数字
     */
    private static String printMinNumber(int[] numbers) {
        if (numbers.length == 0)
            return "";

        ArrayList<String> integerStrings = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= numbers.length; i++)
            builder.append(i);
        StringBuilder maximum = new StringBuilder();
        for (int i = numbers.length; i >= 1; i--)
            maximum.append(i);
        // 最小正整数序列 递增
        while (Integer.parseInt(builder.toString()) < Integer.parseInt(maximum.toString())) {
            // System.out.println(builder.toString());
            integerStrings.add(builder.toString());
            // 尾部开始的序列
            ArrayList<String> subSequence = new ArrayList<>();
            int i = builder.length() - 1;
            for (; i > 0; i--) {
                subSequence.add(builder.substring(i, i + 1));
                if (Integer.parseInt(builder.substring(i, i + 1)) > Integer.parseInt(builder.substring(i - 1, i)))
                    break;
            }
            // 交换并替换元素
            boolean flag = true;
            String s = builder.substring(i - 1, i);
            for (int j = 0; j < subSequence.size(); j++) {
                if (flag && Integer.parseInt(subSequence.get(j)) > Integer.parseInt(s)) {
                    // 交换
                    builder.replace(i - 1, i, subSequence.get(j));
                    subSequence.set(j, s);
                    flag = false;
                }
                builder.replace(i + j, i + j + 1, subSequence.get(j));
            }
        }
        integerStrings.add(maximum.toString());
        // 解码，翻译回字符串
        String[] strings = new String[integerStrings.size()];
        for (int i = 0; i < integerStrings.size(); i++)
            strings[i] = decode(numbers, integerStrings.get(i));
        // 通常数据整理的第一步，排序
        Arrays.sort(strings);
        return strings[0];
    }

    /**
     * 解码，翻译
     * 将 最小正整数字符串 转为 原字符串
     *
     * @param numbers 原数组
     * @param integerString     最小正整数字符串
     * @return  原字符串
     */
    private static String decode(int[] numbers, String integerString) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < integerString.length(); i++) {
            builder.append(numbers[Integer.parseInt(integerString.substring(i, i + 1)) - 1]);
        }
        return builder.toString();
    }
}
