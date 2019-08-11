package external.trend;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 耐心，按顺序做，难度应该是递增的
 * 编程实现两个超长非负数之和
 *
 * 要点：
 * 1.《Thinking in Java》说的好，不建议使用char[]；应该更多的使用String和StringBuilder
 * 2. int 转 char 是高向低转换，会出现问题，不要这么使用，把char改为用String替代
 *
 * @author ihaokun
 * @date 2019/8/8 19:04
 */
public class Adder {

    @Deprecated
    private static String add(String num1, String num2) {
        //Please write your code here
        // 这是使用BigDecimal类解决的方式，下面要自己实现
        /*BigDecimal bigDecimal = new BigDecimal(num1);
        BigDecimal bigDecimal1 = new BigDecimal(num2);
        return String.valueOf(bigDecimal.add(bigDecimal1));*/

        /*
            旧的思路或许错误，char不好转int，计算时会得到不正确的值
        * 新思路：或许可以不用char[]，改用StringBuilder
        * */

        // 1. 将字符串根据.号分隔，注意regex正则表达式的.号不能直接表示，需要使用\\.来表示
        String[] split1 = num1.split("\\.");
        String[] split2 = num2.split("\\.");
        // 整数部分计算
        String string = null;
        char[] preChars1 = split1[0].toCharArray();
        char[] preChars2 = split2[0].toCharArray();
        int length = Math.max(preChars1.length, preChars2.length) + 1;
        char[] preChars = new char[length];
        for (int i = length - 1; i >= 0; i--) {
            int sum = (i < preChars1.length ? preChars1[i] : 0) + (i < preChars2.length ? preChars2[i] : 0);
            preChars[i] = (char) (sum % 10);
            if ((sum / 10) > 0) {
                if (i - 1 > 0) {
                    preChars[i - 1]++;
                } else {
                    string = "1";
                }
            }
        }

        return string != null ? string + Arrays.toString(preChars) : Arrays.toString(preChars);
    }

    // please don't modify any code below.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1234567890123456789012345678901234567890.1234567890123456789012345678901234567890
        // 1000000000100000000010000000001000000000.1000000000100000000010000000001000000000
        // 2234567890223456789022345678902234567890.2234567890223456789022345678902234567890

        String num1 = sc.nextLine();
        String num2 = sc.nextLine();

        String sum = add2(num1, num2);
        System.out.println(sum);
    }

    static String add1(String num1, String num2) {
        // 应该有几种组合情况，整数 + 小数，整数 + 整数，小数 + 小数
        // 1.整数 + 整数
        int length = Math.max(num1.length(), num2.length());
        StringBuilder builder = new StringBuilder(length + 1);
        // 翻转
        StringBuilder builder1 = new StringBuilder(num1).reverse();
        StringBuilder builder2 = new StringBuilder(num2).reverse();
        for (int i = 0; i < length; i++) {
            // 空位用0替代
            int i1 = i < builder1.length() ? Integer.parseInt(builder1.substring(i, i + 1)) : 0;
            int i2 = i < builder2.length() ? Integer.parseInt(builder2.substring(i, i + 1)) : 0;
            int sum = i1 + i2;
            if (builder.length() > i) {
                // 存在进位
                builder.replace(i, i + 1, String.valueOf(((sum += 1) % 10)));
            } else {
                // 不存在
                builder.append(sum % 10);
            }
            if (sum / 10 > 0) {
                // 进位
                builder.append(1);
            }
        }
        return builder.reverse().toString();
    }

    static String add2(String num1, String num2) {
        // 泛用型（整数 + 整数，小数 + 小数，整数 + 小数）
        int length = Math.max(num1.length(), num2.length());
        StringBuilder builder = new StringBuilder(length + 1);
        // 细节部分：String的split()方法，参数为正则表达式，当为.时用\\.表示
        String[] split1 = num1.split("\\.");
        String[] split2 = num2.split("\\.");

        String integer = add1(split1[0], split2[0]);
        if (split1.length > 1 || split2.length > 1) {
            // 有小数部分
            String decimal = add3(1 < split1.length ? split1[1] : "0", 1 < split2.length ? split2[1] : "0");
            if (decimal.length() > Math.max(1 < split1.length ? split1[1].length() : 0, 1 < split2.length ? split2[1].length() : 0)){
                decimal = decimal.substring(1);
                integer = String.valueOf(Integer.parseInt(integer) + 1);
            }
            builder.append(integer);
            builder.append(".");
            builder.append(decimal);
        } else {
            // 无小数部分
            builder.append(integer);
        }
        return builder.toString();
    }

    static String add3(String num1, String num2) {
        // 小数部分字符串 相加的 规则 和 整数部分 不同
        int length = Math.max(num1.length(), num2.length());
        StringBuilder builder = new StringBuilder(length + 1);
        for (int i = length - 1; i >= 0; i--) {
            int i1 = num1.length() > i ? Integer.parseInt(num1.substring(i, i + 1)) : 0;
            int i2 = num2.length() > i ? Integer.parseInt(num2.substring(i, i + 1)) : 0;
            int sum = i1 + i2;
            if (builder.length() > (length - 1 - i)) {
                // 存在进位
                builder.replace(length - 1 - i, length - i, String.valueOf(((sum += 1) % 10)));
            } else {
                // 不存在
                builder.append(sum % 10);
            }
            if (sum / 10 > 0) {
                // 进位
                builder.append(1);
            }
        }
        return builder.reverse().toString();
    }

}
