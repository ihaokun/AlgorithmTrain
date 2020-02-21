package leetcode;

import java.util.LinkedList;

/**
 * 21 合并两个有序链表
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * TAGS: LinkedList, Merge Sorting ?
 * LEVel: EASY
 *
 * @author ihaokun
 * @date 2020/1/27 20:58
 */
public class MergeTwoOrderedLinkedLists {
  public static void main(String[] args) {
    MergeTwoOrderedLinkedLists instance = new MergeTwoOrderedLinkedLists();
    // sample
    // 输入：1->2->4, 1->3->4
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(2);
    l1.next.next = new ListNode(4);
    ListNode l2 = new ListNode(1);
    l2.next = new ListNode(3);
    l2.next.next = new ListNode(4);
    // 输出：1->1->2->3->4->4
    ListNode head = instance.solution1(l1, l2);
    while (head != null){
      System.out.print(head.val + "->");
      head = head.next;
    }
  }

  private static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  /**
   * 利用了归并排序的归并方法
   *
   * @param l1
   * @param l2
   * @return
   * @see algorithms.chapter2sorting.MergeSorting#merge(Comparable[], int, int, int)
   * @see algorithms.chapter2sorting.MergeSortingTopDown#merge(int[], int, int, int)
   * @see algorithms.chapter2sorting.MergeSortingBottomUp#merge(int[], int, int, int)
   */
  private ListNode solution(ListNode l1, ListNode l2) {
    // sort two ordered linked list, get a ordered <queue(impl by linked list)>
    LinkedList<ListNode> linkedList = new LinkedList<>();
    while (l1 != null || l2 != null){
      if (l1 == null){
        linkedList.add(new ListNode(l2.val));
        l2 = l2.next;
      } else if (l2 == null){
        linkedList.add(new ListNode(l1.val));
        l1 = l1.next;
      } else if (l1.val > l2.val){
        linkedList.add(new ListNode(l2.val));
        l2 = l2.next;
      } else {
        linkedList.add(new ListNode(l1.val));
        l1 = l1.next;
      }
    }
    // structure convert: queue(linkedList) to <ListNode>
    ListNode head = linkedList.poll();
    ListNode node = head;

    for (ListNode listNode : linkedList) {
      node.next = listNode;
      node = node.next;
    }

    return head;
  }

  // 看了下其他人的解法，似乎也可以和上一道题一样，使用一个哑节点，减少一次转换
  private ListNode solution1(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);//CRUX clever dummy node
    ListNode node = dummy;
    while (l1 != null || l2 != null){
      if (l1 == null){
        node.next = new ListNode(l2.val);
        l2 = l2.next;
      } else if (l2 == null){
        node.next = new ListNode(l1.val);
        l1 = l1.next;
      } else if (l1.val > l2.val){
        node.next = new ListNode(l2.val);
        l2 = l2.next;
      } else {
        node.next = new ListNode(l1.val);
        l1 = l1.next;
      }
      node = node.next;
    }
    return dummy.next;
  }

}