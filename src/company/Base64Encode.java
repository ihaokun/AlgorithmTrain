package company;

import java.util.ArrayList;
import java.util.Base64;

/**
 * <pre>
 * 公司：汇顶科技 - 提前批
 * 题目描述：
 *      输入文本，并将其转化为 Base64编码
 *      text(ASCII编码) - > binary -> index - > Base64
 *      binary to index
 *      6 bit 对应一个 index，index string应该是4个index组成，如Q对应16
 *      Base64：ABC...XYZabc...xyz123...789+/
 *              不足用 = 补
 *
 * 解法：
 *      提供了2个解法
 *      1是JDK自带类库，util包，详见{@link #encode(String)}
 *      2是按照题目提示给的思路，自行编码转换，详见{@link #encode1(String)}
 *
 * 小结：
 *      要学会<em>快速</em><strong>总结规律</strong>
 *
 * 归纳规律草图：
 *      Base64编码，2^6 = 64，4个码一组
 *      64个字符的组成：ABC...XYZabc...XYZ+/   ；不为4的倍数尾部补"="
 *
 *      A   ASCII：65
 *      binary：0100 0001
 *              010000，010000
 *      indexs：16 16
 *      base64：QQ==
 *
 *       B 66
 *       0100 0010
 *       010000，100000
 *       16 32
 *       Qg==
 *
 *      ABC 65 66 67
 *      0100 0001，0100 0010，0100 0011
 *      010000，010100，001001，000011
 *      16 20 9 3
 *      QUJD
 *
 *      ABCD
 *      0100 0001，0100 0010，0100 0011，0010 0100
 *      010000，010100，001001，000011，001001，000000
 *      QUJDRA==
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/26 19:32
 */
public class Base64Encode {
    public static void main(String[] args) {
        String text = "ABCD";
        System.out.println(encode(text));
        System.out.println(encode1(text));
    }

    private static String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    /**
     * 解法二：
     *      先将 text 转换为 binary字符串，再将binary string 转换为 索引，最后转换为 base64编码
     *
     *      注意：
     *          String 2 int，Integer.parseInt(String)，中的String参数，必须是<em>数字形式</em>的字符串才能被解析
     *          Base64的编码过程中，ASCII码转化为 binary string，每个字节都需要是完整的8 bit，不足补0；如A，65，100001，0100 0001
     *          Base64码的长度必须是4的倍数
     *
     * @param text ASCII字符集构成文本
     * @return Base64编码
     */
    private static String encode1(String text){
        StringBuilder binaryString = new StringBuilder();
        // 1. text - > binary string
        for (char c : text.toCharArray()) {
            String bin = "";
            // Integer.toBinaryString(String)方法，trim了前面的0（可能少于8 bit）
            String str = Integer.toBinaryString(c);
            // 但是在Base64的解析中，是不可以省略的，这里补上前端的0
            int diff = Byte.SIZE - str.length();
            for (int i = 0; i < diff; i++) {
                bin += 0;
            }
            bin += str;
            binaryString.append(bin);
        }
        // 2. binary string - > index seq
        // binary string的长度应为6的倍数，若不足则在尾部补0
        while(binaryString.length() % 6 != 0){
            binaryString.append(0);
        }
        ArrayList<Integer> indexSeq = new ArrayList<>(binaryString.length() / 6);
        while(binaryString.length() > 0){
            indexSeq.add(Integer.parseInt(binaryString.substring(0, 6), 2));
            binaryString.delete(0, 6);
        }
        // 3. index seq - > base64 string，0-63的索引值对应Base64编码的64个字符
        String index2Base64 = "ABCDEDFHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+/";
        StringBuilder base64Encode = new StringBuilder();
        for (int index : indexSeq) {
            base64Encode.append(index2Base64, index, index + 1);
        }
        // base64的长度应为4的倍数，不足在尾部补0
        while (base64Encode.length() % 4 != 0)
            base64Encode.append("=");

        return base64Encode.toString();
    }
}
