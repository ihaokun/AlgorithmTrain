package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 15 三数之和
 *
 * <p>给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 找出所有满足条件且不重复的三元组。
 *
 * <p>注意：答案中不可以包含重复的三元组。
 *
 * CRUX 从Binary Search中得到灵感，sorting firstly，之后围绕一个有序数组做操作就方便多了；牢记数组是有序的了
 *
 * <p>TAGS: Double Points
 * <p>LEVEL: MEDIUM
 *
 * @author ihaokun
 * @date 2019/12/16 22:48
 */
public class ThreeSum {
  public static void main(String[] args) {
    ThreeSum instance = new ThreeSum();
    System.out.println(instance.solution(new int[] {-1, 0, 1, 2, -1, -4}));
    System.out.println(instance.solution(new int[] {0, 0, 0}));
    System.out.println(instance.solution(null));
    System.out.println(instance.solution(new int[] {3,0,-2,-1,1,2})); // [[-2,-1,3],[-2,0,2],[-1,0,1]]
    System.out.println(
        instance.solution1(new int[] {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6})); // [[-4,-2,6],[-4,0,4],[-4,1,3],[-4,2,2],[-2,-2,4],[-2,0,2]]

  }

  //CRUX 原解法：由2找1；新思路：由1找2
  // 题解的指针方式：分别为i的右半部分的两端
  private List<List<Integer>> solution1(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums == null || nums.length < 3) return result;
    Arrays.sort(nums);  //arranged an ordered array firstly
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) break; // must greater 0
      int j = i + 1, k = nums.length - 1;
      int sum = nums[i] + nums[j] + nums[k];
      while (j < k){
        if (sum > 0){
          k--;
        } else if (sum < 0){
          j++;
        } else {
          List<Integer> list = new ArrayList<>();
          list.add(nums[i]);
          list.add(nums[j]);
          list.add(nums[k]);
          if (!isDuplication(result, list)) result.add(list);
          j++;
          k--;
        }
        sum = nums[i] + nums[j] + nums[k];
      }
    }
    return result;
  }

  // 根据题解提示：中间不应该使用二分查找，而是遍历查找
  @Deprecated
  private List<List<Integer>> solution(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums == null || nums.length < 3) return result;
    Arrays.sort(nums);
    int i = 0, j = nums.length - 1;
    while (j - i > 1){
      int sum = nums[i] + nums[j];
      int mid = (i + j) / 2;
      int index = Arrays.binarySearch(nums, i + 1, j, -sum);
      if (index > 0) {
        List<Integer> list = new ArrayList<>();
        list.add(nums[i]);
        list.add(nums[index]);
        list.add(nums[j]);
        if (!isDuplication(result, list))
          result.add(list);
      } else {
        index = -(index + 1);
      }
      if (index > mid){
        i++;
      } else if (index < mid){
        j--;
      } else {
        //XXX 问题所在，二分查找的话，无法确定当位置位于中位置的时候，指针该往哪边偏移
        i++;
        j--;
      }
    }
    return result;
  }

  private boolean isDuplication(List<List<Integer>> matrix, List<Integer> list){
    for (List<Integer> item : matrix) {
      if (item.toString().equals(list.toString())) return true;
    }
    return false;
  }
}