package nowcoder.jianzhi.tree;

import java.util.ArrayList;

/**
 * <pre>
 * 剑指offer - 从上往下打印二叉树
 * 题目描述：
 *      从上往下 打印出二叉树的每个节点，同层节点 从左至右 打印
 * 考察知识点：
 *      二叉树、层序遍历、递归
 * 小结：
 *      这题不难，和其他三种遍历类似，注意递归
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/17 23:04
 */
public class PrintBinaryTreeFromTopToBottom {
    /**
     * 层序遍历 二叉树
     * 每层按 从左至右 的顺序
     *
     * @param node 二叉树节点
     */
    private static void printLevelOrderTraversal(TreeNode node) {
        // 根节点先存入
        ArrayList<TreeNode> list = new ArrayList<>(1);
        if (node != null) {
            list.add(node);
        }
        // 方案1：直接打印
        print1(list, 0);
        // 方案2：存入ArrayList再打印
        list.addAll(print2(list, 0));
        for (TreeNode treeNode : list) {
            System.out.println(treeNode.val);
        }
    }

    /**
     * 层序遍历，遍历一层的方法，递归使用
     *
     * @param nodes node列表
     * @param depth 深度
     * @see #print2(ArrayList, int)
     */
    private static void print1(ArrayList<TreeNode> nodes, int depth) {
        if (nodes.size() > 0) {
            System.out.println("depth:" + depth);
            for (TreeNode node : nodes) {
                System.out.println(node.val);
            }
            ArrayList<TreeNode> list = new ArrayList<>();
            for (TreeNode node : nodes) {
                if (node.left != null)
                    list.add(node.left);
                if (node.right != null)
                    list.add(node.right);
            }
            // 递归
            print1(list, depth + 1);
        }
    }

    /**
     * print1()方法的变种，将数据存入到ArrayList中
     *
     * @param nodes 数组列表
     * @param depth 当前节点深度
     * @return 数组列表
     * @see #print1(ArrayList, int)
     */
    private static ArrayList<TreeNode> print2(ArrayList<TreeNode> nodes, int depth) {
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        if (nodes.size() > 0) {
            for (TreeNode node : nodes) {
                if (node.left != null)
                    arrayList.add(node.left);
                if (node.right != null)
                    arrayList.add(node.right);
            }
            // 递归，迭代下一层节点
            arrayList.addAll(print2(arrayList, depth + 1));
        }
        return arrayList;
    }

    /**
     * 先序遍历 二叉树
     *
     * @param node 二叉树节点
     */
    private static void printPreOrderTraversal(TreeNode node, int depth) {
        if (node != null) {
            // System.out.println("depth:" + depth);
            for (int i = 0; i < depth; i++) {
                System.out.print("\t");
            }
            System.out.println(node.val);
            printPreOrderTraversal(node.left, depth + 1);
            printPreOrderTraversal(node.right, depth + 1);
        }
    }

    public static void main(String[] args) {
        // init
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        // test
        printPreOrderTraversal(root, 0);
        printPreOrderTraversal(null, 0);
        System.out.println("**********************************************************************");
        printLevelOrderTraversal(root);
        printLevelOrderTraversal(null);
    }
}
