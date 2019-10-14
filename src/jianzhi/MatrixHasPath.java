package jianzhi;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指offer - 矩阵中的路径
 *
 * <p>题目描述：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * 例如 a b c e s f c s a d e e
 * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 *
 * <p>考点：回溯法
 *
 * <p>解题思路：使用 递归+Set，注意 路径的下一个点是上一个点的 左右上下 之一，且不能有重复，就可以不断递归</p>
 *
 * @author ihaokun
 * @date 2019/10/12 1:21
 * @see <a href="https://zh.wikipedia.org/wiki/%E5%9B%9E%E6%BA%AF%E6%B3%95">回溯法wiki</a>
 */
public class MatrixHasPath {
  public static void main(String[] args) {
    // Initialization
    /*
    * {
    *   {a, b, c, e},
    *   {s, f, c, s},
    *   {a, d, e, e}
    * }
    * */
    // 此处给的是一个一维数组，非矩阵，需要转化 [idx] = [rowIdx][colIdx] = [idx / cols][idx % cols] = [cols * rowIdx + colIdx]
    // 对应的 left、right、up、down 分别转化为 [idx/cols][idx%cols - 1]、[idx/cols][ids%cols + 1]、[idx/cols - 1][idx%cols]、[idx/cols + 1][idx%cols]
    char[] matrix = {'a', 'b', 'c', 'e', 's', 'f', 'c', 's', 'a', 'd', 'e', 'e'};
    char[] str = {'b', 'c', 'c', 'e', 'd'};
    // char[] matrix = {'A','B','C','E','S','F','C','S','A','D','E','E'};
    // char[] str = {'A', 'B', 'C', 'B'};
    int rows = 3;
    int cols = 4;
    // Test
    MatrixHasPath matrixHasPath = new MatrixHasPath();
    System.out.println(matrixHasPath.hasPath(matrix, rows, cols, str));
  }

  // XXX 回溯法（backtracking） 是什么（是穷举法的一种，经常使用递归）
  private boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
    if (matrix.length == 0) return false;
    // First idx(rowIdx, colIdx)
    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i] == str[0]) {
        // 辅助stack，用于存储 路径的下标
        Set<Integer> set = new HashSet<>();
        // 路径的第一个的下标找到了，下面的可以通过递归寻找路径
        path(set, matrix, i, str, 0, rows, cols);
        if (set.size() == str.length) return true;
      }
    }
    return false;
  }

  private void path(Set<Integer> set, char[] matrix, int matrixIdx, char[] str, int strIdx, int rows, int cols){
    // 该解法一定可以使用recursive
    if (set.size() == str.length) {
      System.out.println(set);
      return;
    }
    if (matrixIdx >= 0 && matrixIdx < matrix.length && strIdx < str.length && matrix[matrixIdx] == str[strIdx] && set.add(matrixIdx)){
      int rowIdx = matrixIdx / cols;
      int colIdx = matrixIdx % cols;
      path(set, matrix, cols * (rowIdx - 1) + colIdx, str, strIdx + 1, rows, cols);
      if (set.size() == str.length) return;
      path(set, matrix, cols * (rowIdx + 1) + colIdx, str, strIdx + 1, rows, cols);
      if (set.size() == str.length) return;
      path(set, matrix, cols * rowIdx + (colIdx - 1), str, strIdx + 1, rows, cols);
      if (set.size() == str.length) return;
      path(set, matrix, cols * rowIdx + (colIdx + 1), str, strIdx + 1, rows, cols);
    }
  }
}
