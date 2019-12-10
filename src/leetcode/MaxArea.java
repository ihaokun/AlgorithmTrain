package leetcode;

/**
 * 11 盛最多水的容器
 *
 * <p>给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i,
 * 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * TAGS: 数组、双指针
 * LEVEL: MEDIUM
 *
 * @author ihaokun
 * @date 2019/12/8 16:19
 * @see <a href="https://leetcode-cn.com/problems/container-with-most-water/">原题链接</a>
 */
public class MaxArea {
  public static void main(String[] args) {
    int[] ints = {1, 8, 6, 2, 5, 4, 8, 3, 7};
    MaxArea maxArea = new MaxArea();
    System.out.println(maxArea.solution(ints));
    System.out.println(maxArea.solution1(ints));
  }

  // 分析题意：应该是 组合 问题
  // 有点类似于 子序列 的解法
  private int solution(int[] heights) {
    // max area
    int result = 0;
    for (int i = 0; i < heights.length; i++) {
      for (int j = i +1; j < heights.length; j++) {
        int area = (j - i) * Math.min(heights[j], heights[i]);
        if (area > result) result = area;
      }
    }
    return result;
  }
  // 该解法是穷举
  // 分析该算法时间复杂度：(n-1) + (n-2) + ... + 1 = n*(n-1)/2; O(N²)；空间复杂度为O(1)

  // 尝试使用 双指针法
  //TODO 需要证明 双指针法不会漏掉最优解
  private int solution1(int[] height) {
    int i = 0, j = height.length - 1;
    int area = (j - i) * Math.min(height[i], height[j]);
    while (i != j){
      //CRUX width缩小不可避，那么就需要找到更大的height
      if (height[i] < height[j]) {
        i += 1;
      } else {
        j -= 1;
      }
      int currArea = (j - i) * Math.min(height[i], height[j]);
      if (currArea > area) area = currArea;
    }
    return area;
  }
  // 分析该解法的时间复杂度：O(N)，空间复杂度O(1)
  // 以 {1, 8, 6, 2, 5, 4, 8, 3, 7} 为例
  // 1,7 8*(1)=8
  // 8,7 7*(7)=49
  //XXX <a href="https://leetcode-cn.com/problems/container-with-most-water/solution/bao-li-fa-de-you-hua-ke-yi-de-dao-shuang-zhi-zhen-/">穷举法优化得到双指针法证明题解</a>
}