package nowcoder.jianzhi.list;

import java.util.ArrayList;

/**
 * <pre>
 * 剑指offer - 复杂链表的<strong>复制</strong>
 * 题目描述：
 *      输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 *      返回结果为复制后复杂链表的head。
 *     （注意，输出结果中请<em>不要返回参数中的节点引用</em>，否则判题程序会直接返回空）
 * 注意：
 *      这个复制链表，相当于所有节点都是new的，不能有原链表的节点存在
 * 知识点：
 *      链表
 * 考点：
 *      分解让复杂问题简单化
 * </pre>
 *
 * @author ihaokun
 * @date 2019/8/22 14:43
 */
public class CopyRandomLinkedList {
    private static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return String.valueOf(label);
        }
    }

    public static void main(String[] args) {
        // init
        RandomListNode head = new RandomListNode(1);

        head.random = new RandomListNode(4);
        head.next = new RandomListNode(2);
        head.next.random = new RandomListNode(5);
        head.next.next = new RandomListNode(3);
        // test
        RandomListNode clone = clone(head);
        while (clone != null){
            System.out.println(clone.label);
            System.out.println(clone.random != null ? clone.random.label : null);
            clone = clone.next;
        }
        // test1
        RandomListNode clone1 = clone1(head);
        while (clone1 != null){
            System.out.println(clone1.label);
            System.out.println(clone1.random != null ? clone1.random.label : null);
            clone1 = clone1.next;
        }

    }

    @Deprecated
    private static RandomListNode clone(RandomListNode pHead) {
        RandomListNode node = null;
        ArrayList<RandomListNode> arr = new ArrayList<>();
        while(pHead != null){
            node = new RandomListNode(pHead.label);
            node.random = pHead.random;
            arr.add(node);

            pHead = pHead.next;
        }
        for (int i = 0; i < arr.size() - 1; i++) {
            arr.get(i).next = arr.get(i + 1);
        }
        return arr.get(0);
    }

    @Deprecated
    private static RandomListNode clone1(RandomListNode pHead) {
        RandomListNode head = null;
        if (pHead != null){
            head = new RandomListNode(pHead.label);
            head.random = pHead.random;
            head.next = clone1(pHead.next);
        }
        return head;
    }

    /**
     * 这个方法是这题的最终解法
     * 并不是前两个不行，问题在于题目的描述，这题必须要复制的链表的节点都是new的，不能是参数的引用，否则webIDE会输出为空
     * 当然，也只有全为新的节点，才算是复制的链表了
     *
     * @param pHead 首节点
     * @return 复制链表的首节点
     */
    public RandomListNode Clone(RandomListNode pHead){
        RandomListNode head = null;
        if(pHead != null){
            head = new RandomListNode(pHead.label);
            if(pHead.random != null){
                head.random = new RandomListNode(pHead.random.label);
            }
            if(pHead.next != null){
                head.next = new RandomListNode(pHead.next.label);
                head.next = Clone(pHead.next);
            }
        }
        return head;
    }
}
