package jianzhi.tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * <pre>
 * 剑指offer - 二叉树中 和 为 某一值的 <em>路径</em>
 * 题目描述：
 *      输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 *      <strong>路径</strong>定义为<em>从树的根结点开始往下一直到叶结点所经过的结点形成一条路径</em>。
 *      (注意: 在返回值的list中，数组长度大的数组靠前)
 * 考点：
 *      树的预备知识（路径path）、二叉树
 * 解题思路：
 *      注意 结合使用 树的先序遍历（先序遍历中得到路径的规律）、Map、LinkedList、树的depth
 *      分析规律，在先序遍历过程中
 *      当前栈顶元素为非叶子节点，要加入的元素也为非叶子节点，则出栈后入栈
 *      遇到叶子节点，先入栈后出栈，入栈的结果和比较是否为target
 * 实例：
 *      		0
 * 	    1			2
 * 3		5	4		6
 * path：0 1 3 = 4
 * 	     0 1 5 = 6
 * 	     0 2 4 = 6
 * 	     0 2 6 = 8
 *
 * 		    10
 * 	    5			12
 * 4		7
 *
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/21 11:32
 */
public class BinaryTreePath {
    private static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.valueOf(this.val);
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
        /*TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);*/
        // test
        // System.out.println(findPath(root, 6));
        System.out.println(printPath(new Stack<>(), root));
        System.out.println("-------------------------------------------");
        System.out.println(printPath(new LinkedHashMap<>(), root, 0));
        System.out.println("-------------------------------------------");
        System.out.println(findPath(root, 6));
        // System.out.println(getArrOfPreOrderTraversal(root));
    }

    private static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        LinkedHashMap<Integer, TreeNode> map = new LinkedHashMap<>();

        return findPath(matrix, list, map, root, target, 0);
    }

    /**
     * 正确解题方法
     * 相较于{@link #printPath(LinkedHashMap, TreeNode, int)}，增加了一些参数
     * 为的是题目需要，其核心思路没有变
     * 注意：
     * matrix的add的list，不能是引用，因为引用会改变，需要新建一个局部变量存储list，将临时变量加入matrix中
     * 该解题法的缺陷：
     * 方法参数过多，让代码显得有些复杂；核心思路不变的精简版本见{@link #printPath(LinkedHashMap, TreeNode, int)}
     *
     * @param matrix 矩阵
     * @param list   先序遍历路径序列
     * @param map    对应list的LinkedMap，辅助作用
     * @param root   根节点
     * @param target 目标路径和
     * @param depth  节点深度
     * @return 矩阵（满足条件的路径序列）
     */
    private static ArrayList<ArrayList<Integer>> findPath(ArrayList<ArrayList<Integer>> matrix, ArrayList<Integer> list, LinkedHashMap<Integer, TreeNode> map, TreeNode root, int target, int depth) {
        if (root != null && map != null) {
            // 要加入的节点是和 map的栈顶节点相同深度，则先将栈顶节点出栈(list、map均出栈)
            if (map.containsKey(depth)) {
                map.remove(depth);
                list.remove(list.size() - 1);
            }
            // 节点入栈(list、map均入栈)
            map.put(depth, root);
            list.add(root.val);
            // 当前节点为叶子节点，则得到一条路径序列，并在之后将其栈顶节点移除
            if (isLeafNode(root)) {
                System.out.println(list);
                if (list.stream().mapToInt(Integer::intValue).sum() == target) {
                    ArrayList<Integer> foo = new ArrayList<>(list);
                    matrix.add(foo);
                }
                map.remove(depth);
                list.remove(list.size() - 1);
            }
            findPath(matrix, list, map, root.left, target, depth + 1);
            findPath(matrix, list, map, root.right, target, depth + 1);
        }
        return matrix;
    }

    /**
     * 正确的打印 路径序列 的方法
     * 相较于 {@link #printPath(Stack, TreeNode)}这个之前不完善的思路
     * 改进点在于：
     * 使用LinkedMap而不是Stack，并同时引入depth
     * 进而避免二叉树的先序遍历过程中重复的同一深度节点 -> 正确打印二叉树的所有路径
     *
     * @param map   LinkedHashMap
     * @param root  根节点
     * @param depth 当前节点深度
     * @return 每次判断的序列
     */
    private static LinkedHashMap<Integer, TreeNode> printPath(LinkedHashMap<Integer, TreeNode> map, TreeNode root, int depth) {
        if (root != null && map != null) {
            // 要加入的节点是和 map的栈顶节点相同深度，则先将栈顶节点出栈
            if (map.containsKey(depth)) {
                map.remove(depth);
            }
            // 节点入栈i
            map.put(depth, root);
            // 当前节点为叶子节点，则得到一条路径序列，并在之后将其移除
            if (isLeafNode(root)) {
                System.out.println(map);
                map.remove(depth);
            }
            printPath(map, root.left, depth + 1);
            printPath(map, root.right, depth + 1);
        }
        return map;
    }

    /**
     * 错误思路
     * 没能解决所有情况，缺陷在于 同深度的节点有可能重复
     * 正确的解决方案见{@link #printPath}
     *
     * @param stack 节点栈
     * @param root  根节点
     * @return 错误
     */
    @Deprecated
    private static Stack<TreeNode> printPath(Stack<TreeNode> stack, TreeNode root) {
        if (root != null) {
            if (isLeafNode(root)) {
                stack.push(root);
                System.out.println(stack);
                stack.pop();
            } else {
                if (stack.size() > 1 && !isLeafNode(stack.peek()))
                    stack.pop();
                stack.push(root);
            }
            printPath(stack, root.left);
            printPath(stack, root.right);
        }
        return stack;
    }

    /**
     * 判断该节点是否为 叶子节点
     *
     * @param node 节点
     * @return 叶子节点 true；非叶子节点 false
     */
    private static boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

}
