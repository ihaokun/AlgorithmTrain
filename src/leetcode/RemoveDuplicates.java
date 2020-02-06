package leetcode;

/**
 * 26. 删除排序数组中的重复项
 *
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * TAGS：Array, Double Points
 * LEVEL：EASY
 *
 * @author ihaokun
 * @date 2020/2/5 18:10
 */
public class RemoveDuplicates {
  public static void main(String[] args) {
    RemoveDuplicates instance = new RemoveDuplicates();
    // 无需考虑新长度之外的元素
    // sample
    // input: [1, 1, 2]
    // output: [1, 2]
    int[] nums = {1, 1, 2};
    // int[] nums = {0,0,1,1,1,2,2,3,3,4};
    // unAccept cases
    // int[] nums = {1, 2};
    // int[] nums = {1, 1, 1};
    int length = instance.officialSolution(nums);
    for (int i = 0; i < length; i++) {
      System.out.println(nums[i]);
    }
  }

  // 之前的思路错误，应该使用双指针
  private int solution1(int[] nums){
    int cur = 1, offset = 1;
    while (offset < nums.length) {
      try{
        while (nums[offset] == nums[cur - 1]){
          offset++;
        }
        nums[cur++] = nums[offset];
      } catch (ArrayIndexOutOfBoundsException e){
        e.printStackTrace();
      }
    }
    return cur;
  }

  // try-catch，异常捕捉导致的性能问题，改用if-else
  private int solution2(int[] nums){
    int cur = 1, offset = 1;
    while (offset < nums.length){
      while (offset < nums.length && nums[offset] == nums[cur - 1]){
        offset++;
      }
      if (offset < nums.length) nums[cur++] = nums[offset];
    }
    return cur;
  }
  // 上个方法耗时17ms，本方法耗时2ms


  // 官方解法，也是用的双指针，思路和我一样，但用了更简洁的方式来实现它
  private int officialSolution(int[] nums){
    int i = 0;
    for (int j = 1 ; j < nums.length; j++) {
      if (nums[i] != nums[j]){
        nums[++i] = nums[j];
      }
    }
    return i + 1;
  }
  // 不需要嵌套循环，也不需要那么多判断，直接一个循环就够了
  // 耗时1ms
}