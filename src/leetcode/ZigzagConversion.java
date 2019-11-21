package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 6 Z字行变换（Zigzag Conversion）
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <pre>
 * 示例一：
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 示例二：
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * </pre>
 *
 * <strong>可知，实际不是Z，而是N字形</strong>
 *
 * <p>Tags: string
 *
 * @author ihaokun
 * @date 2019/11/19 22:16
 */
public class ZigzagConversion {
  private static class TestCase{
    String str;
    int numRows;

    TestCase(String str, int numRows) {
      this.str = str;
      this.numRows = numRows;
    }
  }
  public static void main(String[] args) {
    // new TestCase("LEETCODEISHIRING", 3);
    // new TestCase("LEETCODEISHIRING", 4);
    System.out.println(new ZigzagConversion().convert("LEETCODEISHIRING", 3));
    System.out.println(new ZigzagConversion().convert("AB", 1));
    // System.out.println(new ZigzagConversion().convert("LEETCODEISHIRING", 4));
  }

  // way 1：构建矩阵，浪费部分空间
  private String convert(String s, int numRows) {
    if (s.isEmpty() || numRows == 1) return s;
    int length = s.length();
    if (length > numRows){
      StringBuilder builder = new StringBuilder(length);
      char[][] matrix = new char[numRows][length];
      int rowN = 0, colN = 0;
      // build matrix
      for (int i = 0; i < length; ) {
        for (int j = 0; j < numRows && i < length; j++) {
          matrix[rowN++][colN] = s.charAt(i++);
        }
        rowN-=2;colN++;
        for (int j = 0; j < numRows - 2 && i < length; j++) {
          matrix[rowN--][colN++] =  s.charAt(i++);
        }
      }
      // build string
      for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < length; j++) {
          char character = matrix[i][j];
          if (character != '\u0000') builder.append(character);
        }
      }
      return builder.toString();
    } else {
      return s;
    }
  }
  // solution 1：有点类似于缩小增量排序 Shell's Sorting
  private String solution(String s, int numRows) {
    // 转换思路，直接从string取
    int interval = (numRows - 1) * 2;
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < numRows; i++) {
      for (int j = i; j < s.length(); j += interval) {
        builder.append(s.charAt(j));
      }
    }
    System.out.println(s.length() == builder.length());
    return builder.toString();
  }
}
