package nowcoder.jianzhi;

import company.StringCombination;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * <pre>
 * 剑指offer - 字符串的排序
 * 题目描述：
 *      输入一个字符串,按<em>字典序</em>打印出该字符串中字符的所有排列。
 *      例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba
 *
 * 思路：
 *      和之前<tt>饿了么</tt>的那道题考点接近，饿了么的题目是考的<em>生成组合(combination)</em>；而本题考的是<em>生成排列(permutation)</em>
 *      两道题解题策略类似，都是使用另一个序列来替代原序列，通过<strong>替代序列</strong>来解题
 *      生成组合(combination)：使用的是 位串(二进制字符串) 来替代原字符串 表示组合
 *      生成排列(permutation)：则是使用 <em>最小正整数序列</em> 替代原字符串，<i>递增</i>的最小正整数序列表示排列的可能
 *
 * 关联：
 *      和<strong>饿了么</strong> 碰到的一道面试题有关联关系，见{@link StringCombination}
 *      <em>补充</em>：后面做到一道题，可以用跟这道题同样的解法解题，见{@link nowcoder.jianzhi.list.Array2MinNumber}，再写一遍写的更简洁了一些
 *
 * 知识点：
 *      《离散数学》的 chapter6.6 生成排列和组合，这题考的是生成排列，饿了么考的是生成组合
 *      Permutation：排列；置换
 *
 * 解题思路：
 *      使用最小正整数序列
 *
 * 示例草图：
 *       P(n, r) = n * (n - 1) * ... * (n - r + 1)                                      定理
 *               = n! / (n - r)!                                                        推论
 *       abc；P(3, 3) = 3! / (3-3+1)!= 3! = 3 * 2 * 1 = 6；123，321
 *       123    abc
 *       132    acb
 *       213    bac
 *       231    bca
 *       312    cab
 *       321    cba
 *       推导示例：362541 - > 36 2541
 *                               4521
 *                               4125
 *                             364125
 * 注意：
 *      这道题应该有别的解法，我使用的解法是按照《离散数学》一书得来的（书中的是无重复的字符串）；
 *      而本题要求的是可重复的，故最后使用了Stream的API进行去重操作；
 *      实际这题应该还有别的解法：应该是通过动态规划和递归
 *
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/24 20:30
 */
public class StringPermutation {
    public static void main(String[] args) {
        // String str = "abc";
        String str = "aa";
        ArrayList<String> permutation = permutation(str);
        ArrayList<String> collect = (ArrayList<String>) permutation.stream().distinct().collect(Collectors.toList());
        System.out.println(permutation.stream().distinct().collect(Collectors.toList()));
        // System.out.println(permutation);
    }

    private static ArrayList<String> permutation(String str) {
        ArrayList<String> integerStrings = new ArrayList<>();
        int length = str.length();
        if (length > 0) {
            // 1.将字符序列 转换为 对应最小正整数序列
            // 最小值
            StringBuilder minStr = new StringBuilder();
            for (int i = 1; i <= length; i++)
                minStr.append(i);
            integerStrings.add(minStr.toString());
            // 最大值
            StringBuilder maxStr = new StringBuilder();
            for (int i = length; i >= 1; i--)
                maxStr.append(i);
            // 序列递增
            while (Integer.parseInt(minStr.toString()) < Integer.parseInt(maxStr.toString())) {
                // 获得下一个排列
                // 找出递增序列和关键元素下标
                int indexOf = 0;
                StringBuilder increase = new StringBuilder();
                for (int i = length - 1; i > 0; i--) {
                    increase.append(minStr.substring(i, i + 1));
                    if (Integer.parseInt(minStr.substring(i, i + 1)) > Integer.parseInt(minStr.substring(i - 1, i))) {
                        indexOf = i - 1;
                        break;
                    }
                }
                // 递增序列分化
                for (int i = 0; i < increase.length(); i++) {
                    if (Integer.parseInt(increase.substring(i, i + 1)) > Integer.parseInt(minStr.substring(indexOf, indexOf + 1))) {
                        String temp = increase.substring(i, i + 1);
                        increase.replace(i, i + 1, minStr.substring(indexOf, indexOf + 1));
                        minStr.replace(indexOf, indexOf + 1, temp);
                        minStr.replace(indexOf + 1, minStr.length(), increase.toString());
                        integerStrings.add(minStr.toString());
                        break;
                    }
                }
            }
        }
        ArrayList<String> strings = new ArrayList<>();
        for (String s : integerStrings) {
            strings.add(transform(str, s));
        }
        return strings;
    }

    /**
     * 翻译转化；一一对应
     * 如：abc - > 123，则132 - > a c b
     *
     * @param originalStr 原初字符串
     * @param string      最小正整数字符串
     * @return            翻译后的字符串
     */
    private static String transform(String originalStr, String string) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            int index = Integer.parseInt(string.substring(i, i + 1)) - 1;
            builder.append(originalStr, index, index + 1);
        }
        return builder.toString();
    }
}