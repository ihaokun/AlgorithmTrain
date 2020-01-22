package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 19 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * TAGS:
 * LEVEL:
 *
 * @author ihaokun
 * @date 2020/1/22 16:19
 */
public class RemoveNthFromEnd {
  public static void main(String[] args) {
    RemoveNthFromEnd instance = new RemoveNthFromEnd();
    // sample
    // 1->2->3->4->5; n = 2
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    ListNode newHead = instance.solution(head, 2);

    // unAccept cases

    // ListNode head = new ListNode(1);
    // ListNode newHead = instance.solution(head, 1);

    // ListNode head = new ListNode(1);
    // head.next = new ListNode(2);
    // ListNode newHead = instance.solution(head, 2);

    while (newHead != null){
      System.out.println(newHead.val);
      newHead = newHead.next;
    }
  }

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

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
      list.get(list.size() - n - 1).next = list.get(list.size() - n).next;
    else
      head = head.next;
    return head;
  }
  // 解法分析：时间复杂度：遍历一次，O(N)；空间复杂度，常数空间O(1)
}