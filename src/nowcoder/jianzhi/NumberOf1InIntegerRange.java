package nowcoder.jianzhi;

import nowcoder.jianzhi.binary.NumberOf1InBinary;

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
 *      XXX 补充关联：在一家笔试题上做到了相同的题目，当时没想到，后面感觉有些相似，题目要求O(N)时间复杂度，5道题的第4题
 *
 * 小结：
 *    解题的话，一般使用解法2和解法3；如果考虑时间复杂度，则使用解法3；解法2仅仅是简单而已
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/3 0:14
 */
public class NumberOf1InIntegerRange {
    public static void main(String[] args) {
        // init
        // int n = 21345;
        int n = 10;
        // int n = 112;
        // test
        System.out.println(solution(n));
        System.out.println(solution1(n));
        System.out.println(solution2(n));
    }

    /**
     * 思路：
     *      判断一个 正整数 是否包含1
     *                      若包含1，则1的个数是多少
     *
     *      解法一：穷举；使用String；善用Java的API，在String和int之间相互转换
     *              时间复杂度是O(N²)
     *
     *      补充：笔试的解法和这个类似，不过没用String，整数的1的个数是用/和%计算的
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
     *      补充：这个解法时间复杂度还是O(N²)
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

  private static int solution2(int n) {
    // 需要新的思路，实现时间复杂度小于O(N)
    // 需要使用 数学知识，数学归纳法
    // 解法思路：统计 位数上1的和，216
    int count = 0;
    for (int i = 1; i <= n; i *= 10) {
      // 位前半部分计算
      int bitLess = n / i > 1 ? i : (n % i + 1);
      // 位后半部分计算
      int bitMore = 0;
      if (n / (i * 10) > 0) {
        if (n % (i * 10) >= (2 * i - 1)) {
          bitMore = n / (i * 10) * i;
        } else {
          bitMore = (n / (i * 10) - 1) * i;
          if (n % (i * 10) > 0) bitMore += n % i + 1;
        }
        // bitMore = (n/(i*10) - 1)*i + n%i;
        // if (n % 10 > 0) bitMore++;
      }
      count += bitLess + bitMore;
    }
    return count;
  }
  }
