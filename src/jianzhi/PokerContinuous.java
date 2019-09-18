package jianzhi;

import java.util.Arrays;

/**
 * <pre>
 *     剑指offer - 扑克牌顺子
 *
 *      题目描述：
 *          LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)..
 *          .他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
 *          “红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,
 *          他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
 *          上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
 *          现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
 *
 *      考点：
 *          抽象建模能力
 *      知识点：
 *          字符串
 *
 *      小结：
 *          这题不难，不需要被 题目长的描述 和 字符串 给迷惑了，
 *          这题不需要用到字符串 和 大部分的题意
 *          只需要找到一点规律就可以了，详细题解见注释{@link PokerContinuous#isContinuous(int[])}
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/18 22:37
 */
public class PokerContinuous {
    public static void main(String[] args) {
        // init, a suite of Poker
        System.out.println(isContinuous(new int[]{1, 3, 2, 5, 4}));
    }

    /**
     * <pre>
     *     个人解法：
     *          无需用到字符串，只需找到规律即可
     *     示例：
     *          1 4 8 5 0
     *          1 3 4 0 0
     *          1 5 0 0 0
     *          0 0 0 0 1
     *     解题思路：
     *          1. 将数组排序（通常是处理数据的第一步）
     *          2. 定义两个统计变量，count 和 diff，分别代表 数组中0的个数 和 需要鬼牌的数量
     *          3. 遍历数组
     *              统计 数组中0的个数（0代表大、小王，可以任意表示一张牌）
     *              统计 数组中非0的相邻元素值的差，若差大于1，则两数组之间需要鬼牌，diff += Math.abs(array[i] - array[i + 1]) - 1
     *          4. 比较 count 和 diff 的值。若 diff > count，则鬼牌不够，无法组成顺子，false；否 则 true
     *              例外情况：
     *                  空数组
     *                  当有相同值的元素，则不可能是“顺子”
     * </pre>
     *
     * @param numbers 数组
     * @return 数组是否为“顺子”
     */
    private static boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            return false;
        int count = 0, diff = 0;
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] != 0) {
                int abs = Math.abs(numbers[i] - numbers[i + 1]) - 1;
                if (abs == -1)
                    return false;
                diff += abs;
            } else {
                count++;
            }
        }
        return diff <= count;
    }
}
