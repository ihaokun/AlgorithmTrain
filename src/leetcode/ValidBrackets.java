package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * TAG: Stack
 * LEVEL: EASY
 *
 * @author ihaokun
 * @date 2020/1/24 23:01
 */
public class ValidBrackets {
  private static Map<Character, Character> hashMap = new HashMap<>();
  static {
    hashMap.put(')', '(');
    hashMap.put('}', '{');
    hashMap.put(']', '[');
  }
  public static void main(String[] args) {
    ValidBrackets instance = new ValidBrackets();
    // samples
    System.out.println(instance.solution("()"));//true
    System.out.println(instance.solution("()[]{}"));//true
    System.out.println(instance.solution("(]"));//false
    System.out.println(instance.solution("([)]"));//false
    System.out.println(instance.solution("{[]}"));//true
    // unAccept cases
    System.out.println(instance.solution("(("));//false
    System.out.println(instance.solution("([)"));//false
  }

  private boolean solution(String s) {
    if ("".equals(s)) return true;
    if (s.length() % 2 == 1) return false;
    //use stack
    char[] chars = s.toCharArray();
    Stack<Character> stack = new Stack<>();
    for (char c : chars) {
      if (hashMap.containsKey(c)){
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
          Character top = stack.pop();
          if (top == hashMap.get(c)){
            flag = solution(sb.toString());
            break;
          } else {
            sb.append(top);
          }
        }
        if (!flag) return false;
      } else {
        stack.push(c);
      }
    }
    return stack.isEmpty();
  }
  // official answer: stack + recursion, I lack recursion
}