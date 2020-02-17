package nowcoder.jianzhi.tree;

/**
 * 一个基本类，与 树 相关的题目基本都用到了这个 模型，把它提出来，不必以后每次都写了
 *
 * @author ihaokun
 * @date 2019/10/6 17:49
 */
public class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;

  public TreeNode(int val) {
    this.val = val;
  }
}
