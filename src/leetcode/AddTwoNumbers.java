package leetcode;

/**
 * 2. 两数相加
 *
 * <p>题目描述：给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的 位数 是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * <p>您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * <p>示例：
 * <pre>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * </pre>
 *
 * <p>涉及知识点：链表
 * <p>Level：Medium
 *
 * @author ihaokun
 * @date 2019/10/20 0:48
 */
public class AddTwoNumbers {
  // Definition for singly-linked list.
  private static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    // Init
    ListNode head1 = new ListNode(2);
    head1.next = new ListNode(4);
    head1.next.next = new ListNode(3);
    ListNode head2 = new ListNode(5);
    head2.next = new ListNode(6);
    head2.next.next = new ListNode(4);
    /*ListNode head1 = new ListNode(9);
    head1.next = new ListNode(9);
    ListNode head2 = new ListNode(9);*/
    // Test
    AddTwoNumbers instance = new AddTwoNumbers();
    // ListNode head = instance.addTwoNumbers(head1, head2);
    ListNode head = instance.addTwoNumbers1(head1, head2);
    while (head != null){
      System.out.println(head.val);
      head = head.next;
    }
  }
  // 该解法没有什么技术含量，正常相加，只是要注意 循环退出的case（个人感觉比官方题解好一点）
  private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) return null;
    ListNode head = new ListNode(0);
    ListNode headRef = head;
    while (headRef != null){
      if (l1 != null){
        headRef.val += l1.val;
        l1 = l1.next;
      }
      if (l2 != null){
        headRef.val += l2.val;
        l2 = l2.next;
      }
      int nextVal = headRef.val / 10;
      headRef.val %= 10;
      headRef.next = new ListNode(nextVal);
      // 循环退出case
      if (l1 == null && l2 == null && nextVal == 0) headRef.next = null;
      headRef = headRef.next;
    }
    return head;
  }

  // 顺带写一下官方题解，官方题解倒是和我的初始思路一样，就是有重复的判断
  private ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) return null;
    ListNode head = new ListNode(0);
    ListNode headRef = head;
    while (l1 != null || l2 != null){
      if (l1 != null) {
        headRef.val += l1.val;
        l1 = l1.next;
      }
      if (l2 != null){
        headRef.val += l2.val;
        l2 = l2.next;
      }
      int nextVal = headRef.val / 10;
      headRef.val %= 10;
      if (l1 == null && l2 == null && nextVal == 0) break;
      headRef.next = new ListNode(nextVal);
      headRef = headRef.next;
    }
    return head;
  }
}
