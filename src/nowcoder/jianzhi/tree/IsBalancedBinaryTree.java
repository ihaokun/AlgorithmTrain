package nowcoder.jianzhi.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     剑指offer - 平衡二叉树
 *
 *     题目描述：
 *          输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 *
 *      考点：
 *          知识迁移能力
 *      知识点：
 *          树（平衡二叉树）
 *
 *      摘自wiki的平衡二叉树定义：
 *          是一种特殊的（结构平衡的） <strong><em>二叉搜索树</em></strong>
 *      百度百科的定义：
 *          空树 或 它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 *      本题使用的是 <strong>百科的定义</strong>
 *
 *      小结：
 *          本题主要考察的是：平衡二叉树的定义
 *          我的解法：
 *              根据定义：<em>仅有 空树 或 根节点的左右子树的深度之差<=1，则该二叉搜索树为平衡二叉树</em>
 *              {@link BinaryTreeDepth "结合上一题计算二叉树的深度"}，得到根节点的左右子节点的深度，并比较他们的差的绝对值是否<=1，即解题
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/13 1:02
 * @see <a href="https://zh.wikipedia.org/wiki/%E5%B9%B3%E8%A1%A1%E4%BA%8C%E5%85%83%E6%90%9C%E5%B0%8B%E6%A8%B9">平衡二叉树（平衡二叉搜索树） wiki</a>
 * @see <a href="https://baike.baidu.com/item/%E5%B9%B3%E8%A1%A1%E6%A0%91/7641279?fr=kg_qa">平衡二叉树 baike</a>
 */
public class IsBalancedBinaryTree {
    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

    }
    private static boolean solution(TreeNode root){
        if (root == null)
            return true;
        return Math.abs(treeDepth(root.left) - treeDepth(root.right)) <= 1;
    }
    /** 以下 两个方法 均为上一题写的方法，见{@link BinaryTreeDepth} */
    private static int treeDepth(TreeNode root) {
        if (root == null)
            return 0;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        //XXX 注意：深度从0开始计数
        return depthRecursively(list, 0);
    }
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
        return depthRecursively(nextList, ++depth);
    }
}
