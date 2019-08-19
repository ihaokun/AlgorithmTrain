package offer;

/**
 * <pre>
 * 剑指offer题目	- 树的子结构
 * 题目描述：
 * 		输入两颗二叉树A，B，判断B是不是A的子结构。（PS：我们约定空树不是任意一个树的子结构）
 * 考察知识点：
 * 		二叉树
 * 注意：
 * 		树的子结构 ！= 子树
 * 解题思路：
 * 		任一排序法，A、B的排序结果存入String中，判断B是否为A的子串
 * 小结：
 * 		使用了先序遍历，递归实现，是子结构不是子树的话这题不难
 * </pre>
 *
 * @author ihaokun
 * @date 2019年8月14日
 * @see <a href="https://blog.csdn.net/wushuomin/article/details/79943737">reference link，二叉树的子树、子结构区别</a>
 */
public class HasTreeSubStructure {
    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // init
        TreeNode node1 = new TreeNode(1);
        node1.left = new TreeNode(3);
        node1.right = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        node2.left = new TreeNode(3);
        // test
        System.out.println(isSubStructure(node1, null));
    }

    /**
     * 解题思路：
     * 通过先序遍历得到的String，比较是否包含（String的contains()方法是指定次序的）
     *
     * @param node1
     * @param node2
     * @return
     */
    public static boolean isSubStructure(TreeNode node1, TreeNode node2) {
        if (node2 == null) {
            // 空树判断
            return false;
        }
        String foo = preOrder2String(node1);
        String bar = preOrder2String(node2);
        return foo.contains(bar);
    }

    /**
     * 对根节点进行先序遍历，存入String中
     *
     * @param root
     * @return String
     */
    public static String preOrder2String(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        if (root != null) {
            builder.append(root.val);
            builder.append(preOrder2String(root.left));
            builder.append(preOrder2String(root.right));
        }
        return builder.toString();
    }

}
