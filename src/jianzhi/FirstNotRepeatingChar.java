package jianzhi;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <pre>
 *     剑指offer - 第一个只出现一次的字符
 *
 *     题目描述：
 *          在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 *
 *     考点：
 *          时间、空间效率的平衡
 *     知识点：
 *          字符串、链表、Map、<em>以空间换时间</em>
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/5 20:44
 */
public class FirstNotRepeatingChar {
    public static void main(String[] args) {
        String string = "googgle";
        System.out.println(solution(string));
    }

    /**
     * 使用LinkedMap解题
     *
     * @param string 字符串
     * @return 首个不重复的字符
     */
    private static int solution(String string) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        Map<Character, Integer> repeatMap = new LinkedHashMap<>();
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 同上一题一样，以空间换时间
            if (map.containsKey(chars[i]) || repeatMap.containsKey(chars[i])) {
                map.remove(chars[i]);
                repeatMap.put(chars[i], i);
            } else {
                map.put(chars[i], i);
            }
        }
        return map.size() > 0 ? map.values().iterator().next() : -1;
    }
}
