package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *
 * <p>题目描述：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * <p>示例：
 * <pre>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 * </pre>
 *
 * <p>涉及知识点：hashing
 * <p>LEVEL：EASY
 *
 * @author ihaokun
 * @date 2019/10/19 22:46
 */
public class TwoSum {
  public static void main(String[] args) {
    // Init
    int[] nums = {2, 7, 11, 15};
    int target = 9;
    // Test
    TwoSum instance = new TwoSum();
    System.out.println(Arrays.toString(instance.twoSum(nums, target)));
    System.out.println(Arrays.toString(instance.twoSum1(nums, target)));
    System.out.println(Arrays.toString(instance.twoSum2(nums, target)));
  }

  // 复杂度分析：
  // 时间复杂度：O(N²)
  // 空间复杂度：O(1)
  private int[] twoSum(int[] nums, int target) {
    // 解法一：使用穷举
    for (int i = 0; i < nums.length - 1; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) return new int[] {i, j};
      }
    }
    return null;
  }
  // 官方题解给了3种解法，我写了第一种穷举法，下面写一下剩余两种hashing法
  // 两种解法，时空间复杂度均为 O(N)
  private int[] twoSum1(int[] nums, int target) {
    // 解法二：使用hashing，两次循环
    Map<Integer, Integer> hashMap = new HashMap<>(nums.length);
    for (int i = 0; i < nums.length; i++) hashMap.put(nums[i], i);
    for (int i = 0; i < nums.length; i++) {
      if (hashMap.containsKey(target - nums[i])) return new int[]{i, hashMap.get(target - nums[i])};
    }
    return null;
  }
  private int[] twoSum2(int[] nums, int target) {
    // 解法三：使用hashing，一次循环；较之前两种解法，可能返回的数组下标顺序会有些问题
    Map<Integer, Integer> hashMap = new HashMap<>(nums.length);
    for (int i = 0; i < nums.length; i++) {
      int value = target - nums[i];
      if (hashMap.containsKey(value)) return new int[]{i, hashMap.get(value)};
      hashMap.put(nums[i], i);
    }
    return null;
  }
}