package external;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 饿了么 一道面试题
 * 题目描述：
 * 从n个字符中找到m个字符的组合，比如abc，找出2个字符的组合即为ab,ac,bc
 *
 * TODO 这题目可能出自《组合数学》，有空去实验室看一下这本书
 *
 * @author ihaokun
 * @date 2019/8/8 14:58
 */
public class Eleme {

    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c'};
        int num = 2;
        // combination(chars, num);
        combine(chars.length, num, chars);
    }

    private static void combination(char[] chars, int num) {

        /*
         * 思路：
         * 记录 下标的组合，下标的组合算作数组，总组合为二维数组
         * 最后通过比较数组排除重复（去除顺序颠倒但下标都相同的组合）
         */
        char[] indexs = new char[num];
        List<char[]> list = new ArrayList<>();

        /*
         * 新思路：
         * 使用set存储组合
         * substring不可取，这是组合，不是序列
         * 使用递归获得组合
         * 采用分治，递归求解？
         *
         * 再尝试一下使用 归纳法
         * abc，找出2个字符的组合即为ab,ac,bc
         *
         */

    }

    private static void combine(int n, int m, char[] arr){
        Set<String> set = new LinkedHashSet<>();

    }

}
