package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 19 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * TAGS: Double Point, LinkedList
 * LEVEL: MEDIUM
 *
 * @author ihaokun
 * @date 2020/1/22 16:19
 */
public class RemoveNthFromEnd {
  public static void main(String[] args) {
    RemoveNthFromEnd instance = new RemoveNthFromEnd();
    // sample
    // 1->2->3->4->5; n = 2
    // ListNode head = new ListNode(1);
    // head.next = new ListNode(2);
    // head.next.next = new ListNode(3);
    // head.next.next.next = new ListNode(4);
    // head.next.next.next.next = new ListNode(5);
    // ListNode newHead = instance.solution(head, 2);

    // unAccept cases

    ListNode head = new ListNode(1);
    ListNode newHead = instance.solution1(head, 1);

    // ListNode head = new ListNode(1);
    // head.next = new ListNode(2);
    // ListNode newHead = instance.solution(head, 2);

    while (newHead != null){
      System.out.println(newHead.val);
      newHead = newHead.next;
    }
  }

  private static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  // 解法一：自己的思路：将链表转为数组，再用数组中目标元素的前后元素进行next转换即可
  private ListNode solution(ListNode head, int n) {
    // linked list convert to array
    List<ListNode> list = new ArrayList<>();
    ListNode node = head;
    while (node != null){
      list.add(node);
      node = node.next;
    }
    // process
    if (list.size() - n - 1 >= 0)
      list.get(list.size() - n - 1).next = list.get(list.size() - n).next;//CRUX 前一个节点的next = 目标节点的next节点
    else
      head = head.next;
    return head;
  }
  // 解法分析：时间复杂度：遍历一次，O(N)；空间复杂度，常数空间O(1)

  // 解法二：官方题解思路：使用双指针，两个指针间隔n，当后一个指针移到末尾，则前一个指针就指着目标元素
  private ListNode solution1(ListNode head, int n){
    ListNode dummy = new ListNode(0); //CRUX new一个哑节点，作为首节点；这个哑节点的巧妙之处在于不用判断要移除的是首节点的情况
    dummy.next = head;
    ListNode ahead = dummy;
    ListNode rear = dummy;
    for (int i = 0; i < n + 1; i++)
      rear = rear.next;
    while (rear != null){
      ahead = ahead.next;
      rear = rear.next;
    }
    ahead.next = ahead.next.next;
    return dummy.next;
  }
}