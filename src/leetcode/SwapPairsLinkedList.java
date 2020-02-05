package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 24 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * TAGS: LinkedList
 * LEVEL: MEDIUM
 *
 * @author ihaokun
 * @date 2020/2/2 23:16
 */
public class SwapPairsLinkedList {
  public static void main(String[] args) {
    SwapPairsLinkedList instance = new SwapPairsLinkedList();
    //sample
    // input: 1->2->3->4
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    // special cases
    // ListNode head = null;  //case1
    // ListNode head = new ListNode(1); //case2
    // ListNode head = new ListNode(1); //case3
    // head.next = new ListNode(2);
    // head.next.next = new ListNode(3);
    // output: 2->1->4->3
    // ListNode newHead = instance.solution(head);
    ListNode newHead = instance.officialSolution(head);
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

  // 链表交换考虑得太多，直接用数组简单点
  private ListNode solution(ListNode head) {
    // first step: convert to array
    ListNode alias = head;
    List<ListNode> nodes = new ArrayList<>();
    while (head != null){
      nodes.add(head);
      head = head.next;
    }
    if (nodes.size() <= 1) return alias;
    // second step: swap
    ListNode dummy = new ListNode(0);
    ListNode node = dummy;
    boolean isEven = nodes.size() % 2 == 0; //note even：甚至；即使；偶数n，odd：奇数n
    int length = isEven ? nodes.size() : nodes.size() - 1;
    for (int i = 0; i < length; i += 2) {
      node.next = nodes.get(i + 1);
      node.next.next = nodes.get(i);
      node = node.next.next;
    }
    node.next = isEven ? null : nodes.get(length);
    return dummy.next;
  }

  // 官方题解-1，使用递归
  private ListNode officialSolution(ListNode head){
    if (head == null || head.next == null) return head; // base case
    ListNode first = head;
    ListNode second = head.next;
    // Swapping Pair
    first.next = officialSolution(second.next);
    second.next = first;
    return second;
  }
  // 这个递归的是很简洁了，比我的转为数组用的代码量少很多，也明了
  // 时间复杂度O(N)，N是链表节点数量；空间复杂度O(N)，使用的栈空间

  // 官方题解-2，使用迭代
  private ListNode officialSolution1(ListNode head){
    ListNode dummy = new ListNode(-1);  // 需要一个哑节点
    ListNode prev = dummy;  //注意这个前驱节点
    while (head != null && head.next != null){
      ListNode first = head;
      ListNode second = head.next;
      // swapping
      first.next = second.next;
      second.next = first;
      prev.next = second;
      //CRUX ReInitializing prev & head
      head = first.next;
      prev = first;
    }
    return dummy.next;
  }

}