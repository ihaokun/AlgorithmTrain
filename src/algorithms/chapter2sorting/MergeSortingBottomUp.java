package algorithms.chapter2sorting;

import java.util.Arrays;

/**
 * 自顶向下 + 自底向上 的归并排序实现
 *
 * @author ihaokun
 * @date 2019/11/5 8:54
 */
public class MergeSortingBottomUp {
  public static void main(String[] args) {
    // Init
    int[] arr = new int[]{1,4,3,9,7};
    // Test
    MergeSortingBottomUp instance = new MergeSortingBottomUp();
    instance.sort(arr);
    System.out.println(Arrays.toString(arr));
  }
  private int[] aux;
  private void sort(int[] arr){
    aux = new int[arr.length];
    // Top - Bottom
    // divide(arr, 0 , arr.length - 1);
    // Bottom - Top
    // Bottom approach Top，使用循环而不是递归来实现“分治”中分的功能，外层是数组的大小，内层的范围内符合大小的所有数组
    for (int size = 1; size < arr.length; size*=2) {
      for (int i = 0; i < arr.length - size; i += size*2) {
        merge(arr, i, i + size - 1, Math.min(i + size*2 - 1, arr.length - 1));
      }
    }
  }
  private void divide(int[] arr, int head, int tail){
    if (head < tail){
      int mid = (head + tail) / 2;
      // notice recursion order
      divide(arr, head, mid);
      divide(arr, mid + 1, tail);
      merge(arr, head, mid, tail);
    }
  }
  // conquer
  private void merge(int[] arr, int head, int mid, int tail){
    System.arraycopy(arr, 0, aux, 0, aux.length);
    // i,j,k 分别是 左数组起点、右数组起点、原起点
    int i = head, j = mid + 1, k = head;
    while (k <= tail){
      if (i > mid) arr[k] = aux[j++];
      else if (j > tail) arr[k] = aux[i++];
      else if (i <= mid && aux[i] < aux[j]) arr[k] = aux[i++];
      else arr[k] = aux[j++];
      k++;
    }
  }

}