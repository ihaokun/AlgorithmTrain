package jianzhi.string;

/**
 * <pre>
 *     剑指offer - 正则表达式匹配
 *
 *     题目描述：
 *          请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 *          模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 *          在本题中，匹配是指字符串的所有字符匹配整个模式。
 *          例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 *
 *     注意题目描述：在本题中，匹配是指字符串的所有字符匹配整个模式。
 *
 *     考点：
 *          字符串
 *     知识点：
 *          字符串、正则表达式Regex
 *
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/22 0:32
 * @see <a href="https://www.runoob.com/regexp/regexp-syntax.html">regex refer link</a>
 */
public class RegexMatch {
    public static void main(String[] args) {
        System.out.println("match(\"aaa\".toCharArray(), \"a.a\".toCharArray()) = " + solution("aaa".toCharArray(), "a.a".toCharArray()));
    }

    /**
     * <pre>
     *     解法一：
     *          使用Java自带类库，lang.String，{@link String#valueOf(char[])} + {@link String#matches(String)}
     * </pre>
     *
     * @param str     字符串
     * @param pattern 正则表达式
     * @return regex是否匹配该string
     * @see String#matches(String) "JDK的正则表达式匹配"
     */
    private static boolean solution(char[] str, char[] pattern) {
        return String.valueOf(str).matches(String.valueOf(pattern));
    }

    // 解法二，需要充分考虑第二位是*的情况
    private static boolean solution1(char[] str, char[] pattern) {
        if (str == null && pattern == null) return false;
        return matches(str, 0, pattern, 0);
    }

    /**
     * <pre>
     *   解法二：
     *        共 4 种情况
     *
     *        先分 2 大块
     *        1. pattern中的第二个字符不是'*'
     *            字符串str 和 模式pattern 一一匹配，不符合则 return false
     *        2. pattern中的第二个字符是'*'
     *            '*'代表0个字符，即前面的字符不匹配任何，相当于不存在；模式pattern后移两个字符，字符串str不移动
     *            '*'代表1个字符，即前面的字符仅匹配一个，仅有一个；模式pattern向后移动两个字符，字符串str移动一位
     *            '*'代表多个字符，即前面的字符匹配多个；模式pattern向后移动两个字符，字符串str移动多位
     *
     * </pre>
     *
     * @param str     字符串
     * @param pattern 正则表达式
     * @return regex是否匹配该string
     */
    private static boolean matches(char[] str, int strIndex, char[] pattern, int patternIndex) {
        //有效性检验：str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) return true;
        //pattern先到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) return false;
        //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if (strIndex < str.length && (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.'))
                return matches(str, strIndex, pattern, patternIndex + 2)//模式后移2，视为x*匹配0个字符
                        || matches(str, strIndex + 1, pattern, patternIndex + 2)//视为模式匹配1个字符
                        || matches(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
            else
                return matches(str, strIndex, pattern, patternIndex + 2);
        }
        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if (strIndex < str.length && (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.'))
            return matches(str, strIndex + 1, pattern, patternIndex + 1);
        return false;
    }
}