package leetcode;

/**
 * 136. 只出现一次的数字
 *
 * <p>给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了两次。找出那个只出现了一次的元素。
 *
 * <p>说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * <p>TAGS: Hashtable, bit operation
 * <p>LEVEL: EASY
 *
 * @author ihaokun
 * @date 2019/12/27 23:17
 */
public class SingleNumber {
  public static void main(String[] args) {
    SingleNumber instance = new SingleNumber();
    System.out.println(instance.solution(new int[] {2, 2, 1}));//excepted result: 1
    System.out.println(instance.solution(new int[] {4, 1, 2, 1, 2}));// excepted result: 4
  }

  // O(N)
  // 使用位运算，异或XOR，^运算符
  private int solution(int[] nums) {
    int xorSum = nums[0];
    for (int i = 1; i < nums.length; i++) {
      xorSum ^= nums[i];
    }
    return xorSum;
  }
}