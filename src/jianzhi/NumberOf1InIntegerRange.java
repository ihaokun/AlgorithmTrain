package jianzhi;

/**
 * <pre>
 * 剑指offer - 整数中1出现的次数（从1到n整数中1出现的次数）
 *
 * 题目描述：
 *      求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 *      为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 *      ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 *
 * 知识点：
 *      查找、数学
 * 考点：
 *      时间效率
 *
 * 关联：
 *      和之前做的一道题有相似之处，也是剑指offer的题，见{@link NumberOf1InBinary}
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/3 0:14
 */
public class NumberOf1InIntegerRange {
    public static void main(String[] args) {
        // init
        int n = 13;
        // test
        System.out.println(solution(n));
        System.out.println(solution1(n));
    }

    /**
     * 思路：
     *      判断一个 正整数 是否包含1
     *                      若包含1，则1的个数是多少
     *
     *      解法一：穷举；使用String；善用Java的API，在String和int之间相互转换
     *              时间复杂度是O(N²)
     *
     * @param n 1~N，n即为范围
     * @return 范围内1出现的次数，详见题意
     */
    private static int solution(int n) {
        // 解法一，使用String和int
        int count = 0;
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            if (s.contains("1")){
                for (int j = 0; j < s.length(); j++)
                    if (s.substring(j, j + 1).equals("1"))
                        count++;
            }
        }
        return count;
    }

    /**
     * 解法二：
     *      思路：使用数学性质（取每一位，原整数mod 10，循环体内 / 10）
     *      同样是穷举，但这个没有使用类型转换，故相较解法一，运行更快
     *                  且使用数学性质，代码更简洁、不依赖某一编程语言
     *
     * @param n 范围终点
     * @return 1出现次数
     */
    private static int solution1(int n){
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int j = i;
            while (j != 0){
                if (j % 10 == 1)
                    count++;
                j /= 10;
            }
        }
        return count;
    }
}
