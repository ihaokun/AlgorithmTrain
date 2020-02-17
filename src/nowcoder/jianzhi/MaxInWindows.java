package nowcoder.jianzhi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指offer - 滑动窗口的最大值
 *
 * <p>题目描述：给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
 * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 *
 * <p>知识点：数组
 *
 * <p>考点：栈和队列
 *
 * <p>解题思路：使用Queue + Collections工具类的max()方法；这个解法并不好，过于依赖Java的类库了，而且有冗余，应该可以在获取每个窗体的最大值那里优化一下
 *
 * <p>顺带翻译一下题目：大致就是求 等长的连续子序列 的最大值
 *
 * @author ihaokun
 * @date 2019/10/11 0:53
 */
public class MaxInWindows {
  public static void main(String[] args) {
    // 根据题意，可预估使用 队列Queue 更加合适
    int[] arr = {2, 3, 4, 2, 6, 2, 5, 1};
    MaxInWindows maxInWindows = new MaxInWindows();
    System.out.println(maxInWindows.maxInWindows(arr, 3));
  }

  private ArrayList<Integer> maxInWindows(int[] num, int size) {
    ArrayList<Integer> arrayList = new ArrayList<>();
    if (num.length < size || size <= 0) return arrayList;
    Queue<Integer> queue = new LinkedList<>();
    int i = 0;
    for (; i < size; i++) {
      queue.offer(num[i]);
    }
    arrayList.add(Collections.max(queue));
    System.out.println(queue);
    for (; i < num.length; i++) {
      queue.poll();
      queue.offer(num[i]);
      System.out.println(queue);
      arrayList.add(Collections.max(queue));
    }
    return arrayList;
  }
}
