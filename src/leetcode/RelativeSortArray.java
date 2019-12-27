package leetcode;

import java.util.*;

/**
 * 1122. 数组的相对排序
 *
 * 两个数组，arr1和arr2
 * arr2中的元素各不相同
 * arr2的每个元素都出现在arr1中
 *
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 * TAGS: Sorting, Array
 * LEVEL: EASY
 *
 * @author ihaokun
 * @date 2019/12/26 23:50
 */
public class RelativeSortArray {
  public static void main(String[] args) {
    RelativeSortArray instance = new RelativeSortArray();
    System.out.println(
        Arrays.toString(
            instance.solution1(
                new int[] {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[] {2, 1, 4, 3, 9, 6})));
    //correct answer: [2,2,2,1,4,3,3,9,6,7,19]
  }

  private int[] solution(int[] arr1, int[] arr2) {
    // 思路：先遍历arr2，取arr1
    int[] ans = new int[arr1.length];
    List<Integer> aux = new ArrayList<>();
    int k = 0;
    for (int i : arr2) {
      for (int value : arr1) {
        if (value == i) ans[k++] = value;
      }
    }
    // 剩余数组元素的处理问题
    for (int i : arr1) {
      boolean flag = false;
      for (int j : arr2) {
        if (i == j){
          flag = true;
          break;
        }
      }
      if (!flag) aux.add(i);
    }
    aux.sort(Comparator.comparingInt(o -> o));
    for (Integer integer : aux) ans[k++] = integer;
    return ans;
  }
  // 过于繁琐，辣鸡

  // 解法2：基于HashMap
  private int[] solution1(int[] arr1, int[] arr2){
    int[] result = new int[arr1.length];
    int index = 0;
    Map<Integer, Boolean> aux = new HashMap<>();
    for (int i = 0; i < arr1.length; i++) aux.put(i, true);
    for (int i : arr2) {
      for (int j = 0; j < arr1.length; j++) {
        if (arr1[j] == i){
          result[index++] = i;
          aux.put(j, false);
        }
      }
    }
    List<Integer> remain = new ArrayList<>();
    aux.keySet().forEach(integer -> {
      if (aux.get(integer)) remain.add(arr1[integer]);
    });
    remain.sort(Integer::compareTo);
    for (Integer integer : remain) {
      result[index++] = integer;
    }
    return result;
  }
}