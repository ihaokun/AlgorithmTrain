package leetcode;

import java.util.*;

/**
 * 12 整数转罗马数字
 *
 * <p>罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * <pre>
 *   I 1
 *   V 5
 *   X 10
 *   L 50
 *   C 100
 *   D 500
 *   M 1000
 * </pre>
 * <pre>
 *   通常情况下，罗马数字中小的数字在大的数字的右边。
 *   但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *   I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 *   X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 *   C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * </pre>
 *
 * <p>TAGS: 数学、字符串
 * <p>LEVEL: MEDIUM
 *
 * @author ihaokun
 * @date 2019/12/11 21:31
 * @see <a href="https://leetcode-cn.com/problems/integer-to-roman/">原题链接</a>
 */
public class Integer2Roman {
  //XXX LinkedHashMap，基于HashMap 和 LinkedList，是按照添加顺序的Map
  // HashMap无序
  // TreeMap有序，升序
  private static Map<Integer, String> mapping = new LinkedHashMap<>();

  private static Map<Integer, String> useCases = new HashMap<>();

  static {
    mapping.put(1000, "M");
    mapping.put(900, "CM");
    mapping.put(500, "D");
    mapping.put(400, "CD");
    mapping.put(100, "C");
    mapping.put(90, "XC");
    mapping.put(50, "L");
    mapping.put(40, "XL");
    mapping.put(10, "X");
    mapping.put(9, "IX");
    mapping.put(5, "V");
    mapping.put(4, "IV");
    mapping.put(1, "I");

    useCases.put(3, "III");
    useCases.put(4, "IV");
    useCases.put(9, "IX");
    useCases.put(58, "LVIII");
    useCases.put(1994, "MCMXCIV");
    useCases.put(2000, "MM");
  }
  public static void main(String[] args) {
    mapping.forEach((integer, s) -> System.out.println(integer));
    Integer2Roman instance = new Integer2Roman();
    useCases.forEach((i, s) -> System.out.println(instance.intToRoman(i).equals(s)));
  }

  private String intToRoman(int num) {
    StringBuilder builder = new StringBuilder();
    Integer[] integers = mapping.keySet().toArray(new Integer[0]);
    int i = 0;
    while (num > 0) {
      if (num < integers[i]) {
        ++i;
        continue;
      }
      num -= integers[i];
      builder.append(mapping.get(integers[i]));
    }
    System.out.println(builder.toString());
    return builder.toString();
  }
}