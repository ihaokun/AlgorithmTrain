package leetcode;

import java.util.Arrays;

/**
 * 31. 下一个排列
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须<i>原地</i>修改，只允许使用额外常数空间。
 *
 * TAGS:
 * LEVEL:
 *
 * @author ihaokun
 * @date 2020/2/10 21:35
 */
public class NextPermutation {
  private static int[][] matrix;
  static {
    // sample
    matrix = new int[5][];
    matrix[0] = new int[]{1, 2, 3}; // expected result = 1, 3, 2
    matrix[1] = new int[]{3, 2, 1}; // expected result = 1, 2, 3
    matrix[2] = new int[]{1, 1, 5}; // expected result = 1, 5, 1
    matrix[3] = new int[]{1, 3, 2}; // expected result = 2, 1, 3
    matrix[4] = new int[]{1, 2, 3, 2}; // expected result = 1, 3, 2, 2;
  }
  public static void main(String[] args) {
    // 这题似乎和 离散数学（组合数学）有关
    NextPermutation instance = new NextPermutation();
    // use cases
    for (int[] ints : matrix) {
      instance.solution1(ints);
    }
    // unAccept cases
    instance.solution1(new int[]{5,4,7,5,3,2});  // should result is [5,5,2,3,4,7]
    instance.solution1(new int[]{2,2,0,4,3,1});  // should result is [2,2,1,0,3,4]
  }

  // 这个思路还是挺清晰简单的，就是一个数学问题，代码实现并不难，参考官方题解的动图就可以看懂
  private void solution(int[] nums) {
    boolean isMax = true;
    for (int i = nums.length - 1; i > 0; i--) {
      // First, find the first decreasing element
      if (nums[i] > nums[i - 1]){
        isMax = false;
        for (int j = nums.length - 1; j > i - 1; j--) {
          // Second, swap with the just greater element
          if (nums[j] > nums[i - 1]){
            int foo = nums[j];
            nums[j] = nums[i - 1];
            nums[i - 1] = foo;
            // Finally, reverse the remain sequence
            for (int k = nums.length - 1, tmp = i; k >= i + (nums.length - i) / 2; k--, tmp++) {
              int bar = nums[k];
              nums[k] = nums[tmp];
              nums[tmp] = bar;
            }
            break;
          }
        }
        break;
      }
    }
    if (isMax) Arrays.sort(nums);  // set nums is min permutation(order by asc)
    System.out.println(Arrays.toString(nums));
  }
  // 这个不是官方题解，是个人按照官方题解思路自己实现的，但运行效率不太行，下面试试官方题解的方式

  // 模仿官方题解，提炼函数，代码逻辑清晰一些
  private void solution1(int[] nums){
    int i = nums.length - 1;
    for (; i > 0; i--) {
      if (nums[i] > nums[i - 1]){ // First
        for (int j = nums.length - 1; j > i - 1; j--) {
          if (nums[j] > nums[i - 1]){
            swap(nums, j, i - 1); //Second
            break;
          }
        }
        break;
      }
    }
    reverse(nums, i);  // Finally
    System.out.println(Arrays.toString(nums));
  }
  private void swap(int[] nums, int i, int j){
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
  private void reverse(int[] nums, int offset){
    int end = nums.length - 1;
    while (offset < end){
      swap(nums, offset++, end--);
    }
  }
}