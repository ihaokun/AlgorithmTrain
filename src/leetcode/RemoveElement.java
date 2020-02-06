package leetcode;

/**
 * 27. 移除元素
 *
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * TAGS: Array, Double Points
 * LEVEL: EASY
 *
 * @author ihaokun
 * @date 2020/2/6 17:43
 */
public class RemoveElement {
  public static void main(String[] args) {
    RemoveElement instance = new RemoveElement();
    // sample
    // input: [3, 2, 2, 3], 3
    // input: [0,1,2,2,3,0,4,2], 2
    // int[] nums = {3, 2, 2, 3};
    int[] nums = {0,1,2,2,3,0,4,2};
    int length = instance.solution(nums, 2);
    for (int i = 0; i < length; i++) {
      System.out.println(nums[i]);
    }
  }

  private int solution(int[] nums, int val){
    int i = 0;
    for (int j = 0; j < nums.length; j++) {
      if (nums[j] != val){
        nums[i++] = nums[j];
      }
    }
    return i;
  }
  // 用了和上一道题一样的双指针解法，耗时0ms

  // 题示给的不需要考虑元素顺序，或许可以有个新的解法

}