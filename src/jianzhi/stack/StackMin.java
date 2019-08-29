package jianzhi.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * <pre>
 * 剑指offer - 包含min函数的栈
 * 题目描述：
 * 		定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））
 * 考察知识点：
 * 		栈
 * 解题思路：
 * 		旧思路：要使得时间复杂度为O(1)，则需每次压栈的操作都将栈排序一遍，让最小的元素在栈顶（错误，改变了原栈，出栈操作会导致获得的min错误）
 * 		新思路：新增一个stack，每次push操作 压入 原stack中的最小元素，每次pop操作 出 栈顶元素
 * 关键点：
 * 		在于	O(1) 和	原栈不能改变次序，需有一个存放最小元素的栈
 * 		- 对栈进行一系列操作，每次操作后要得到min，那么原栈必是不可变的
 * 		- 需要一个新栈minStack：通过在每次push操作中得到rawStack的最小值元素压入minStack；pop操作rawStack和minStack均正常出栈
 * 			这样，无论是push还是pop操作，minStack的 栈顶 都是最小值元素
 * 示例：
 * 		push 3
 * 		push 4
 * 		push 2
 * 		push 3
 * 		pop
 * 		pop
 * 		pop
 * 		push 0
 *
 * 		rawStack	minStack		top(min)
 * 		3			3				3
 * 		3,4			3,3				3
 * 		3,4,2		3,3,2			2
 * 		3,4,2,3		3,3,2,2			2
 * 		3,4,2		3,3,2			2
 * 		3,4			3,3				3
 * 		3			3				3
 * 		3,0			3,0				0
 * </pre>
 *
 * @author ihaokun
 * @date 2019年8月16日
 * @see <a href="https://blog.csdn.net/u013132035/article/details/80603691">reference link</a>
 */
public class StackMin {

    Stack<Integer> rawStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push1(int node) {
        rawStack.push(node);
        int min = rawStack.peek();
        for (Integer integer : rawStack) {
            if (min > integer) {
                min = integer;
            }
        }
        minStack.push(min);
    }

    /**
     * 错误，若是有了pop操作，那么得到的min是不正确的，不能采用原栈排序的方式
     *
     * @param node
     */
    @Deprecated
    public void push(int node) {
        rawStack.push(node);
        // 排序（降序）
        int capacity = rawStack.size();
        int[] arr = new int[capacity];
        int index = 0;
        // 出栈
        while (!rawStack.isEmpty()) {
            arr[index++] = rawStack.pop();
        }
        // 排序
        Arrays.sort(arr);
        // 入栈
        for (int i = arr.length - 1; i >= 0; i--) {
            rawStack.push(arr[i]);
        }
    }

    public void pop() {
        rawStack.pop();
        minStack.pop();
    }

    public int top() {
        return minStack.peek();
    }

    public int min() {
        /*
         * // 获得该栈 中最小的元素（非O(1)的方法），错误，没有考虑后续操作
         * int minElement = stack.pop();
         * while (!stack.isEmpty()) {
         * Integer top = stack.pop();
         * if (top < minElement) {
         * minElement = top;
         * }
         * }
         * return minElement;
         */
        return top();
    }

    public static void main(String[] args) {
        StackMin stackMin = new StackMin();

        // test
        stackMin.push1(3);
        System.out.println(stackMin.min());
        stackMin.push1(4);
        System.out.println(stackMin.min());
        stackMin.push1(2);
        System.out.println(stackMin.min());
        stackMin.push1(3);
        System.out.println(stackMin.min());
        stackMin.pop();
        System.out.println(stackMin.min());
        stackMin.pop();
        System.out.println(stackMin.min());
        stackMin.pop();
        System.out.println(stackMin.min());
        stackMin.push1(0);
        System.out.println(stackMin.min());
    }
}
