package jianzhi.tree;

import java.util.Arrays;

/**
 * <pre>
 * 剑指offer - <strong>二叉搜索树</strong>的<em>后序</em>遍历序列
 * 题目描述：
 *      输入一个整数数组，判断该数组是不是某二叉搜索树的<em>后序</em>遍历的结果。
 *      如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 * 考点：
 *      栈、树，二叉搜索树 和 二分查找 有关联
 * 解题关键：
 *      注意 二叉搜索树的性质，其定义为：<em>任一节点的 <tt>左子树</tt>的节点均 小于 该节点，<tt>右子树</tt>的节点均 大于 该节点</em>
 *      根据该性质，后序遍历的尾节点是根节点，对后序遍历序列进行<i>递归</i>拆分，其左右子树的序列作为参数分别进行递归
 * 关联：
 *      《算法分析》中，chapter 3.6.3 栈的应用中，有中缀到后缀的转换，可以适当参考看看
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/19 16:32
 * @see <a href="https://blog.csdn.net/u013132035/article/details/80607000">reference link</a>
 */
public class BSTSequencePost {
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
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        // test
        System.out.println("Print Tree By PreOrderTraversal:");
        printPreOrderTraversal(root, 0);
        System.out.println("Print Tree By PostOrderTraversal:");
        printPostOrderTraversal(root);
        System.out.println("Print Tree By InOrderTraversal:");
        printInOrderTraversal(root);
        // solution
        System.out.println("--------------------------------------------------------------");
        System.out.println(isPostOrderSequenceOfBST(new int[]{4, 8, 6, 12, 16, 14, 10}));
        // System.out.println(isPostOrderSequenceOfBST(new int[]{4, 6, 7, 5}));
    }

    /**
     * 判断 数组 是否为 <strong>二叉搜索树</strong> 的 <em>后序遍历序列</em>
     *
     * @param sequence 数组
     * @return true or false
     */
    private static boolean isPostOrderSequenceOfBST(int[] sequence) {
        if (sequence.length == 0)
            return false;
        int indexEnd = sequence.length - 1;
        int rootVal = sequence[indexEnd];
        // 左右分界下标、移动下标
        int indexOf = 0, i = 0;
        // 左子树
        for (; i < sequence.length; i++) {
            if (sequence[i] > rootVal) {
                indexOf = i;
                break;
            }
        }
        // 右子树
        for (; i < sequence.length; i++) {
            if (sequence[i] < rootVal)
                return false;
        }
        // 递归
        boolean left = true;
        if (indexOf > 0)
            left = isPostOrderSequenceOfBST(Arrays.copyOfRange(sequence, 0, indexOf));
        boolean right = true;
        if (indexOf < indexEnd)
            right = isPostOrderSequenceOfBST(Arrays.copyOfRange(sequence, indexOf, indexEnd));
        return left && right;
    }

    /**
     * <strong>先序</strong>遍历 输出二叉树<br/>
     * 并以 <em>目录树</em> 的形式展示
     *
     * @param root  根节点
     * @param depth 节点深度
     */
    private static void printPreOrderTraversal(TreeNode root, int depth) {
        if (root != null) {
            for (int i = 0; i < depth; i++) {
                System.out.print("\t");
            }
            System.out.println(root.val);
            printPreOrderTraversal(root.left, depth + 1);
            printPreOrderTraversal(root.right, depth + 1);
        }
    }

    /**
     * <strong>后序</strong>遍历 输出 二叉树（二叉查找树）
     *
     * @param root 根节点
     */
    private static void printPostOrderTraversal(TreeNode root) {
        if (root != null) {
            printPostOrderTraversal(root.left);
            printPostOrderTraversal(root.right);
            System.out.println(root.val);
        }
    }

    /**
     * <strong>中序</strong>遍历 输出 二叉树（二叉查找树）
     *
     * @param root 根节点
     */
    private static void printInOrderTraversal(TreeNode root) {
        if (root != null) {
            printInOrderTraversal(root.left);
            System.out.println(root.val);
            printInOrderTraversal(root.right);
        }
    }
}
