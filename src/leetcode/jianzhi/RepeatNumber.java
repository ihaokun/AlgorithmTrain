package leetcode.jianzhi;

import java.util.Arrays;

/**
 * 在LeetCode上二刷剑指offer
 *
 * 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * TAGS: Array
 * LEVEL: EASY
 *
 * @author ihaokun
 * @date 2020/2/17 20:50
 * @see nowcoder.jianzhi.list.array.ArrayDuplicate "第一次做"
 */
public class RepeatNumber {
  public static void main(String[] args) {
    RepeatNumber instance = new RepeatNumber();
    System.out.println(instance.solution(new int[]{2, 3, 1, 0, 2, 5, 3}));
  }
  private int solution(int[] nums) {
    // 第一个思路，排序，排序后再顺序查询寻找重复项
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; i++)
      if (nums[i] == nums[i + 1]) return nums[i];
    return -1;
  }
  // 看了看LeetCode的题解，想想还是我以前的hash解法比较简单易懂，这个排序反而不太好
}