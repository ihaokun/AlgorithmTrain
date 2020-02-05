package leetcode;

import java.util.*;

/**
 * 22. 括号生成
 * Parenthesis：圆括号；插入语；括弧
 *
 * <p>给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * <p>关键字：有效、组合（排列）
 *
 * TAGS: 回溯算法、字符串
 * LEVEL: MEDIUM
 * 关联题目：{@link ValidBrackets}, {@link LetterCombinations 使用了回溯法解题}
 *
 * @author ihaokun
 * @date 2020/1/30 20:38
 */
public class GenerateParenthesis {
  public static void main(String[] args) {
    GenerateParenthesis instance = new GenerateParenthesis();
    // sample
    // input: n=3
    // output:[
    //   "((()))",
    //   "(()())",
    //   "(())()",
    //   "()(())",
    //   "()()()"
    // ]
    System.out.println(instance.solution(3));
    instance.backtrack(new ArrayList<>(), "", 0, 0, 3);
  }

  private List<String> result = new ArrayList<>();
  private char[] chars;

  private List<String> solution(int n){
    chars = new char[n * 2];
    backtracking(0, n, 0, 0);
    return result;
  }

  // 根据图示给的思路：构建树形结构，剪枝（回溯）
  private void backtracking(int cur, int n, int leftCount, int rightCount){
    if (cur == chars.length) {
      // System.out.println(Arrays.toString(chars));
      result.add(String.valueOf(chars));
      return;
    }
    if (leftCount < n) {  //先生成左括号
      chars[cur] = '(';
      backtracking(cur + 1, n, leftCount + 1, rightCount);
      //NOTE 注意这个递归结束回来，下面还有要执行的代码行，所以不要修改cur和leftCount这两个变量的值
    }
    if (rightCount < leftCount) {
      chars[cur] = ')';
      backtracking(cur + 1, n, leftCount, rightCount + 1);
    }
  }

  // Official Solution
  public void backtrack(List<String> ans, String cur, int open, int close, int max){
    if (cur.length() == max * 2) {
      System.out.println(cur);
      ans.add(cur);
      return;
    }

    if (open < max)
      backtrack(ans, cur+"(", open+1, close, max);
    if (close < open)
      backtrack(ans, cur+")", open, close+1, max);
  }
}