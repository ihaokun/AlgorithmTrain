package jianzhi.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     剑指offer - 二叉树的深度
 *     关联题：{@link BinaryTreePath "二叉树中 和 为 某一值 的路径"}
 *             {@link PrintBinaryTreeFromTopToBottom "层序遍历打印二叉树节点"}
 *
 *     题目描述：
 *          输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 *     考点：
 *          知识迁移能力
 *      知识点：
 *          树（层序遍历）
 *
 *      解题思路：
 *          使用 层次遍历（level traversal） 得到深度
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/12 21:10
 */
public class BinaryTreeDepth {
    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // init
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(6);
        // test
        System.out.println(solution(root));
    }

    /**
     * 解题思路：
     * 预计使用 层次遍历（Level Traversal），可参考之前写过的{@link PrintBinaryTreeFromTopToBottom}
     *
     * @param root 树的根节点
     * @return 树的深度
     */
    private static int solution(TreeNode root) {
        if (root == null)
            return 0;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        //XXX 注意：深度从0开始计数
        return depthRecursively(list, 0);
    }

    /**
     * <pre>
     *      通过 二叉树的层序遍历（Level Traversal），得到此二叉树的深度
     *      小结一下二叉树的几种遍历
     *          先序、后序、中序、层序
     *              均使用递归实现
     *
     *       TODO 有时间再分析一下该算法的时间复杂度，或考虑使用先序遍历得到深度；这题由于只给了根节点，不然用后序遍历的话，求高度是不错的
     *       递归次数为 该树深度，即当树节点数为N时，深度为logN，递归次数为logN
     *       每次递归的循环执行次数不同，不好计算
     * </pre>
     *
     * @param list  该层节点数组
     * @param depth 该层深度
     * @return 树的深度
     */
    private static int depthRecursively(List<TreeNode> list, int depth) {
        // base case
        if (list.size() == 0)
            return depth;
        // 下层节点数组
        List<TreeNode> nextList = new ArrayList<>();
        for (TreeNode node : list) {
            if (node.left != null)
                nextList.add(node.left);
            if (node.right != null)
                nextList.add(node.right);
        }
        depth = depthRecursively(nextList, ++depth);
        return depth;
    }
}
