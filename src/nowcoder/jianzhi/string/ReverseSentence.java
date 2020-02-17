package nowcoder.jianzhi.string;

/**
 * <pre>
 *     剑指offer - 反转单词顺序序列
 *
 *     题目描述：
 *          牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 *          例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
 *          Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 *
 *     考点：
 *          知识迁移能力
 *     知识点：
 *          字符串
 *
 *     sentence：句子；命题
 *     和前一道题差不多，简单，考察的String API
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/18 22:08
 */
public class ReverseSentence {
    public static void main(String[] args) {
        System.out.println(solution("student. a am I"));
        System.out.println(solution(" "));
    }

    private static String solution(String str) {
        String[] words = str.split(" ");
        if (words.length == 0)
            return str;
        StringBuilder builder = new StringBuilder(str.length());
        for (int i = words.length - 1; i >= 0; i--) {
            builder.append(words[i]);
            builder.append(" ");
        }
        return builder.substring(0, builder.length() - 1);
    }
}
