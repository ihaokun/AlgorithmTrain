package leetcode;

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
 * <p>LEVEL:MEDIUM
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
    System.out.println(new ZigzagConversion().convert1("LEETCODEISHIRING", 3));
    System.out.println(new ZigzagConversion().convert1("AB", 1));
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
      // build matrix，循环体 = 竖下+斜上
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
  // 分析该解法的时间复杂度 O(N²)

  // Official Solution
  private String convert1(String s, int numRows) {
    if (numRows == 1) return s;
    StringBuilder[] builders = new StringBuilder[Math.min(s.length(), numRows)];      // non-null rows
    for (int i = 0; i < builders.length; i++) {                                         // array elements init, avoid NPE
      builders[i] = new StringBuilder();
    }

    boolean goingDown = false;
    int i = 0;
    for (char c : s.toCharArray()) {
      builders[i].append(c);
      /*if (goingDown){
        if (i == builders.length - 1){
          --i;
          goingDown = false;
        } else {
          ++i;
        }
      } else {
        if (i == 0){
          ++i;
          goingDown = true;
        } else {
          --i;
        }
      }*/
      // simplify above if-else
      if (i == 0 || i == builders.length - 1){
        goingDown = !goingDown;
      }
      i += goingDown ? 1:-1;
    }
    StringBuilder res = new StringBuilder();
    for (StringBuilder builder : builders) {
      res.append(builder.toString());
    }
    return res.toString();
  }
  // 官方解法，直接将按Z字形排列的数组，转化成从左至右、从上至下的数组；时间复杂度O(N)，聪明解法
}
