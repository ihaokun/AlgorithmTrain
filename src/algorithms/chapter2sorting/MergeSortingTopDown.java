package algorithms.chapter2sorting;

import java.util.Arrays;

/**
 * 基于 上一次的归并排序不熟练，在美团笔试有遇到，再更纯的手写一次
 * 自然语言 转 程序语言，这样记得更好；首先要明白 自然语言 如何描述算法，然后再自己用程序语言实现它，而不是相反的顺序
 *
 * @author ihaokun
 * @date 2019/10/16 21:20
 */
public class MergeSortingTopDown {
  public static void main(String[] args) {
    // Initialization
    int[] arr1 = {1, 3, 5};
    int[] arr2 = {2, 4, 6};
    int[] arr = new int[arr1.length + arr2.length];
    System.arraycopy(arr1, 0, arr, 0, arr1.length);
    System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
    // Test
    MergeSortingTopDown instance = new MergeSortingTopDown();
    // instance.merge(arr, 0, length - 1, arr.length - 1);
    int[] test = {364,637,341,406,747,995,234,971,571,219,993,407,416,366,315,301,601,650,418,355,460,505,360,965,516,648,727,667,465,849,455,181,486,149,588,233,144,174,557,67,746,550,474,162,268,142,463,221,882,576,604,739,288,569,256,936,275,401,497,82,935,983,583,523,697,478,147,795,380,973,958,115,773,870,259,655,446,863,735,784,3,671,433,630,425,930,64,266,235,187,284,665,874,80,45,848,38,811,267,575};
    instance.sort(test);
    System.out.println(instance.count);
    System.out.println(Arrays.toString(test));
    System.out.println(Arrays.toString(arr));
  }

  // 归并操作
  private void merge(int[] arr, int head, int mid, int tail){
    // 先copy原数组给aux辅助数组
    System.arraycopy(arr, head, aux, head, tail - head + 1);
    int leftPos = head, rightPos = mid + 1;
    // 注意该循环结构的写法：XXX 四种情况需要分开写，而且左右数组用尽的2种情况要先写，否则可能会数组下标越界
    for (;head <= tail; head++) {
      if (leftPos > mid && rightPos <= tail) arr[head] = aux[rightPos++];
      else if (rightPos > tail && leftPos <= mid) arr[head] = aux[leftPos++];
      else if ((aux[leftPos] > aux[rightPos])) {
        arr[head] = aux[rightPos++];
        count += (mid - leftPos + 1);
      }
      else if ((aux[leftPos] < aux[rightPos])) arr[head] = aux[leftPos++];
    }
  }
  // 分治过程（使用递归）：自顶向下 Top approach Down
  //（递归 不断 一分为二，再在出栈过程中两两归并排序）
  private void division(int[] arr, int head, int tail){
    if (head < tail){
      int mid = (head + tail) / 2;
      division(arr, head, mid);
      division(arr, mid + 1, tail);
      // merge
      merge(arr, head, mid, tail);
    }
  }

  private int count = 0;
  // 辅助数组
  private int[] aux;
  //XXX 注意：三个方法的参数均为 数组索引，要注意length-1，<和<=的使用
  private void sort(int[] arr){
    // aux仅声明一次
    aux = new int[arr.length];
    // 先分治 division
    division(arr, 0 , arr.length - 1);
  }
}
