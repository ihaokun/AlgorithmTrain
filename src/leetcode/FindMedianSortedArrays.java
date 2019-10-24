package leetcode;

import java.util.Arrays;

/**
 * 4. 寻找两个有序数组的中位数
 *
 * <p>题目描述：给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * <p>你可以假设 nums1 和 nums2 不会同时为空。
 *
 * <p>示例：
 * <pre>
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 * </pre>
 * <pre>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 * </pre>
 *
 * <p>Level：Difficult
 * TODO 暂时放弃，困难难度的题目确实挺难的，自己思考很难想到好的解法
 *
 * @author ihaokun
 * @date 2019/10/22 22:13
 */
public class FindMedianSortedArrays {
  public static void main(String[] args) {
    // Init
    /*int[] nums1 = {1,3};
    int[] nums2 = {2};*/
    /*int[] nums1 = {1,2};
    int[] nums2 = {3,4};*/
    /*int[] nums1 = {1};
    int[] nums2 = {1};*/
    /*int[] nums1 = {};
    int[] nums2 = {1};*/
    int[] nums1 = {-1,3};
    int[] nums2 = {1,2};
    // Test
    FindMedianSortedArrays instance = new FindMedianSortedArrays();
    System.out.println(instance.findMedianSortedArrays2(nums1, nums2));
  }

  /**
   * 该解法的时间复杂度为 O(m+n) O(N)；
   * 虽然leetcode上通过了，但实际是不符合题目要求的；
   * 这个解法比较简单清晰
   * <p>可参考之前 归并排序 的归并过程
   *
   * @param nums1
   * @param nums2
   * @return
   * @see algorithms.chapter2_sorting.MergeSort2#merge(int[], int, int, int)
   */
  private double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // 根据题意，第一个思路是两个有序数组排序（有种既视感，似乎哪道题也这么做过）
    // 想起来了，两个 有序 数组 归序，这是归并(Merge)排序的递归的Merge过程
    // 需要考虑四种情况，或许也可以联系一下两链表之和的加法过程
    int i = 0, j = 0, index = 0;
    int[] aux = new int[nums1.length + nums2.length];
    while (index < aux.length){
      if (i == nums1.length) aux[index] = nums2[j++];
      else if (j == nums2.length) aux[index] = nums1[i++];
      else if (nums1[i] >= nums2[j]) aux[index] = nums2[j++];
      else if (nums1[i] <= nums2[j]) aux[index] = nums1[i++];
      index++;
    }
    for (int element : aux) {
      System.out.println(element);
    }
    double median = aux.length % 2 == 1 ? aux[aux.length / 2] : (double) (aux[aux.length/2] + aux[aux.length/2 - 1]) / 2;
    return median;
  }

  private double findMedianSortedArrays1(int[] nums1, int[] nums2) {
    // 解法存在缺陷，数组内存在负数会得到错误结果
    if (nums1.length == 0 || nums2.length == 0)
      return nums1.length != 0
          ? nums1.length % 2 == 1
              ? nums1[nums1.length / 2]
              : (nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2.0
          : nums2.length % 2 == 1
              ? nums2[nums2.length / 2]
              : (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2.0;
    if (nums1.length == 1 && nums2.length == 1) return (nums1[0] + nums2[0]) / 2.0;
    // 看到的一种较为简单的新思路，不需使用 BinarySearch
    double midVal1 = nums1.length % 2 == 1 ? nums1[nums1.length / 2] : (nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2.0;
    double midVal2 = nums2.length % 2 == 1 ? nums2[nums2.length / 2] : (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2.0;
    if (midVal1 == midVal2) return midVal1;
    else if (midVal1 > midVal2) {
      return findMedianSortedArrays1(
          Arrays.copyOfRange(nums1, 0, nums1.length / 2),
          Arrays.copyOfRange(nums2, nums2.length / 2, nums2.length));
    } else {
      return findMedianSortedArrays1(
          Arrays.copyOfRange(nums1, nums1.length / 2, nums1.length),
          Arrays.copyOfRange(nums2, 0, nums2.length / 2));
    }
  }

  private double findMedianSortedArrays2(int[] nums1, int[] nums2) {
    // 官方题解：使用 递归+二分查找
    // 替换 三元运算符，使用 (nums[(len + 1) / 2 - 1] + nums[(len + 2) / 2 - 1]) / 2，即可以表示数组中位数
    int left1 = nums1.length / 2 - 1;
    int left2 = nums2.length / 2 - 1;
    double median1 = (nums1[(nums1.length + 1) / 2 - 1] + nums1[(nums1.length + 2) / 2 - 1]) / 2.0;
    double median2 = (nums2[(nums2.length + 1) / 2 - 1] + nums2[(nums2.length + 2) / 2 - 1]) / 2.0;
    if (nums1[left1] <= median2 && nums2[left2] <= median1) {
      if (median1 < median2) return (nums1[left1 + 1] + nums2[left2]) / 2.0;
      else if (median1 > median2) return (nums2[left2 + 1] + nums1[left1]) / 2.0;
      else return median1;
    } else if (nums1[left1] > median2){
      return findMedianSortedArrays2(Arrays.copyOfRange(nums1, 0, left1), Arrays.copyOfRange(nums2, left2 + 1, nums2.length));
    } else {
      return findMedianSortedArrays2(Arrays.copyOfRange(nums1, 0, left1), Arrays.copyOfRange(nums2, left2 + 1, nums2.length));
    }
  }

  private double doubleBinarySearch(int[] arr1, int off1, int len1, int[] arr2, int off2, int len2){

    return 0D;
  }

}