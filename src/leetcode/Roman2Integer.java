package leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 13 罗马数字转整数
 *
 * 题目描述见12，几乎完全一样，只是翻转了上一个问题
 *
 * TAGS:
 * LEVEL: EASY
 *
 * @author ihaokun
 * @date 2019/12/15 18:23
 * @see Integer2Roman
 */
public class Roman2Integer {
  private static Map<String, Integer> mapping = new LinkedHashMap<>();

  private static Map<String, Integer> useCases = new HashMap<>();

  static {
    mapping.put("M", 1000);
    mapping.put( "CM",900);
    mapping.put( "D",500);
    mapping.put( "CD",400);
    mapping.put( "C",100);
    mapping.put("XC",90);
    mapping.put("L", 50);
    mapping.put("XL", 40);
    mapping.put("X", 10);
    mapping.put("IX", 9);
    mapping.put("V", 5);
    mapping.put("IV", 4);
    mapping.put("I", 1);

    useCases.put("III",3);
    useCases.put("IV",4);
    useCases.put("IX",9);
    useCases.put("LVIII", 58);
    useCases.put("MCMXCIV", 1994);
    useCases.put("MM", 2000);
  }

  public static void main(String[] args) {
    Roman2Integer instance = new Roman2Integer();
    useCases.forEach((s, integer) -> System.out.println(instance.solution(s) == integer));
  }
  private int solution(String s){
    int result = 0;
    String[] strings = mapping.keySet().toArray(new String[0]);
    int i = 0;
    while (!s.isEmpty()){
      if (s.startsWith(strings[i])){
        result += mapping.get(strings[i]);
        s = s.substring(strings[i].length());
      } else {
        ++i;
      }
    }
    return result;
  }
}