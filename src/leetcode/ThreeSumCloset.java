package leetcode;

import java.util.Arrays;

/**
 * 16 最接近的三数之和
 *
 * TAGS: Double Points、Sorting
 * LEVEL: MEDIUM
 *
 * @author ihaokun
 * @date 2020/1/3 22:18
 */
public class ThreeSumCloset {
  public static void main(String[] args) {
    ThreeSumCloset instance = new ThreeSumCloset();
    System.out.println(instance.solution(new int[] {-1, 2, 1, -4}, 1));
    // unAccept case
    System.out.println(instance.solution(new int[] {0, 0, 0}, 1));
    System.out.println(instance.solution(new int[] {1, 1, 1, 0}, 1));
  }
  private int solution(int[] nums, int target){
    if (nums == null || nums.length < 3) return target;
    Arrays.sort(nums);
    int i = 0, j = i + 1, k = nums.length - 1;
    int result = nums[i] + nums[j] + nums[k];
    int diff = Math.abs(result - target);
    for (; i < nums.length - 2; i++) {
      j = i + 1;
      k = nums.length - 1;
      while (j < k){
        int subSum = nums[i] + nums[j] + nums[k];
        int subDiff = Math.abs(subSum - target);
        if (diff > subDiff) {
          result = subSum;
          diff = subDiff;
        }
        if (subSum < target){
          j++;
        } else if (subSum > target){
          k--;
        } else {
          return result;
        }
      }
    }
    return result;
  }
}