package nowcoder.jianzhi.string;

/**
 * <pre>
 *     剑指offer - 表示数值的字符串
 *
 *     题目描述：
 *          请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *          例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 *          但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 *
 *     <i>这题属实做不来，看不太懂题目，虽然猜测着使用Java的API解了这道题，但并没有真正理解这题，
 *     这题应该是 设计到了 一些计算机的底层知识，如 浮点型在计算机中的存储等，这个E是啥意思也没明白；
 *     和上一题的情况应该也有点类似，上一题我也是用的Java自带类库解的题，实际实现它应该也需要了解一些底层；
 *     如 char型的原理实现</i>
 *
 *     考点：
 *          字符串
 *     知识点：
 *          字符串
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/23 20:06
 */
public class NumericString {
    public static void main(String[] args) {
        System.out.println(isNumeric("-1E-16".toCharArray()));
    }

    /**
     * 此题实际上是 求解 String convert Double<br/>
     * 这里使用了 JDK API，{@link Double#parseDouble(String)}
     *
     * @param str 字符串的char数组
     * @return str是否可被解析为double
     */
    private static boolean isNumeric(char[] str){
        try {
            System.out.println("Double.parseDouble(new String(str)) = " + Double.parseDouble(new String(str)));
            return true;
        } catch (Exception e){
            return false;
        }
    }
}