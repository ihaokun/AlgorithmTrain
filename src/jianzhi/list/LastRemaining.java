package jianzhi.list;

/**
 * <pre>
 *     剑指offer - 孩子们的游戏（圆圈中最后剩下的数）
 *
 *     题目描述：
 *          每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。
 *          其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
 *          然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 *          每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,
 *          从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
 *          请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 *
 *          如果没有小朋友，请返回-1
 *
 *     考点：
 *          抽象建模能力
 *     知识点：
 *          链表、数学
 *
 *     思考：
 *          和上一题类似，也是长长的 题目描述
 *     关键字：
 *          圆圈（可以想到使用链表了）
 *
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/19 1:39
 */
public class LastRemaining {
    static class DoubleLinkedListNode<T> {
        DoubleLinkedListNode prev;
        DoubleLinkedListNode next;
        T value;
    }

    public static void main(String[] args) {
        System.out.println(solution1(5, 3) == 3);
    }

    static class LinkedListNode {
        LinkedListNode next;
        int value;
    }

    /**
     * <pre>
     *     解法一：
     *          根据圆圈，使用 自定义的单链表解题
     *          1. 节点数组初始化并赋值
     *          2. 进行报数游戏（循环中指针移动位置，将节点移除）
     * </pre>
     *
     * @param n 节点总数
     * @param m 报数
     * @return 剩余最后一个节点
     */
    private static int solution1(int n, int m) {
        if (n == 0)
            return -1;
        // Init Linked List
        LinkedListNode[] nodes = new LinkedListNode[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new LinkedListNode();
            nodes[i].value = i;
        }
        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        nodes[nodes.length - 1].next = nodes[0];
        //
        LinkedListNode node = nodes[0];
        while (node.next != node) {
            for (int i = 0; i < m - 2; i++) {
                node = node.next;
            }
            node.next = node.next.next;
            node = node.next;
        }
        return node.value;
    }

}
