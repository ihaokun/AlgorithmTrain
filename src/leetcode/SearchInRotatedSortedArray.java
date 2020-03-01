package leetcode;

/**
 * 33. 搜索旋转排序数组
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 *
 * NOTICE 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * TAGS: Array, Binary Search
 * LEVEL: MEDIUM
 *
 * @author ihaokun
 * @date 2020/3/1 21:46
 */
public class SearchInRotatedSortedArray {
  public static void main(String[] args) {
    // 这题和以往不一样，是先用C语言写的一遍
    // 今后刷题开始用多语言，不局限于Java了，golang也可以开始
    SearchInRotatedSortedArray instance = new SearchInRotatedSortedArray();
    System.out.println(instance.solution(new int[]{1, 3}, 0));
    System.out.println(instance.solution(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    System.out.println(instance.solution(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
  }

  private int solution(int[] nums, int target) {
    // First, find the rotation index
    int i = 0;
    while (i < nums.length - 1 && nums[i] < nums[++i]);
    // Second, use binary search
    int result = binarySearch(nums, 0, i - 1, target);
    return result == -1 ? binarySearch(nums, i, nums.length - 1, target) : result;
  }

  /**
   * 手写二分查找算法
   * 根据题目要求的O(log N)时间复杂度，可以知道应该使用二分查找
   *
   * @param arr    数组
   * @param i      左下标
   * @param j      右下标
   * @param target 查找目标
   * @return 数组索引
   */
  private int binarySearch(int[] arr, int i, int j, int target) {
    int mid;
    while (i <= j) {     //NOTE 注意这里包含等于的可能
      mid = (i + j) / 2;
      if (arr[mid] > target)
        j = mid - 1;
      else if (arr[mid] < target)
        i = mid + 1;
      else
        return mid;
    }
    return -1;
  }
}