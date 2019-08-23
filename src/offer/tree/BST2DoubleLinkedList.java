package offer.tree;

import java.util.LinkedList;

/**
 * <pre>
 * 剑指offer - 二叉搜索树 和 双向链表
 * 题目描述：
 *      输入一棵二叉搜索树，将该<em>二叉搜索树</em>转换成一个<em>排序的双向链表</em>。
 *      要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * 知识点：
 *      链表、树
 * 考点：
 *      分解让复杂问题简单化
 * 注意：
 *      关联：和上一道题{@link offer.list.CopyRandomLinkedList}不一样，上一题的复制是要全部新节点，这题不能有新节点
 *      <em>这题有点模糊不清的地方，排序是指的什么排序，这边测试的用了中序得到的升序排序，测试题目的case成功了</em>
 * 解题思路：
 *      由 二叉搜索树 + 中序遍历 的性质可得到 一个排序后的链表，详见{@link #convert(LinkedList, TreeNode)}
 *      然后将链表中每个节点的left和right属性修改，详见{@link #Convert(TreeNode)}
 *      之前有过一个尝试失败的思路：是通过递归，在一次中序遍历过程中，同时将left和right属性正确修改，然而实现不了，只能分为两步完成
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/22 17:40
 */
public class BST2DoubleLinkedList {
    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // init BST
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        // test，题目描述不清，排序的话就先用中序得到的升序来试试
        InOrderTraversal(root);

        Convert(root);
    }
    private static TreeNode Convert(TreeNode pRootOfTree) {
        LinkedList<TreeNode> convert = convert(new LinkedList<>(), pRootOfTree);
        for (int i = 0; i < convert.size(); i++) {
            convert.get(i).left = i - 1 >= 0 ? convert.get(i - 1) : null;
            convert.get(i).right = i + 1 < convert.size() ? convert.get(i + 1) : null;
        }
        return convert.size() > 0 ? convert.get(0) : null;
    }

    private static LinkedList<TreeNode> convert(LinkedList<TreeNode> linkedList, TreeNode root){
        if (root != null){
            convert(linkedList, root.left);
            linkedList.add(root);
            convert(linkedList, root.right);
        }
        return linkedList;
    }

    /**
     * 中序遍历，参考用
     *
     * @param root 根节点
     */
    private static void InOrderTraversal(TreeNode root) {
        if (root != null){
            InOrderTraversal(root.left);
            System.out.println(root.val);
            InOrderTraversal(root.right);
        }
    }
}