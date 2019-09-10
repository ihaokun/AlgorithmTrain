package jianzhi.list;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 *     剑指offer - 两个链表的第一个公共节点
 *
 *     题目描述：
 *          输入两个链表，找出他们的第一个公共节点
 *
 *      知识点：
 *          链表
 *      考点：
 *          时间、空间 效率的平衡
 *
 *      小结：
 *          简单题
 *          注意 算法 与 数据结构 的灵活结合，本题主要使用的Java数据结构框架中的<em>set</em>
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/9 20:40
 */
public class FirstCommonNode {
    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }
    }

    public static void main(String[] args) {
        // init
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode common = new ListNode(3);
        head1.next = new ListNode(4);
        head1.next.next = common;
        head2.next = common;
        // test
        System.out.println(solution(head1, head2));
    }

    /**
     * <pre>
     * 既然题目要求时空间效率平衡，那么普通的穷举法基本是可以排除的
     *
     * 解题思路：
     *      为了 时空间效率平衡
     *      使用 <strong>set</strong> 这个数据结构，时间复杂度为N
     *
     * </pre>
     *
     * @param pHead1 第一个链表首节点
     * @param pHead2 另一个链表的首节点
     * @return 两个链表的第一个公共节点
     */
    private static ListNode solution(ListNode pHead1, ListNode pHead2) {
        Set<ListNode> set = new HashSet<>();
        while (pHead1 != null || pHead2 != null){
            if (pHead1 != null){
                if (!set.add(pHead1))
                    return pHead1;
                pHead1 = pHead1.next;
            }
            if (pHead2 != null){
                if (!set.add(pHead2))
                    return pHead2;
                pHead2 = pHead2.next;
            }
        }
        return null;
    }
}
