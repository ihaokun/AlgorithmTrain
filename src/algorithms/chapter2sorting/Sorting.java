package algorithms.chapter2sorting;

/**
 * 排序算法模板类
 * 见 chapter2.1.1
 *
 * 所有排序算法，都应该有 less 和 exchange 方法
 *
 * @author ihaokun
 * @date 2019/8/31 19:27
 */
public abstract class Sorting {
    public abstract void sort(Comparable[] arr);

    public static boolean isSort(Comparable[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            if (!less(arr[i], arr[i + 1]))
                return false;
        }
        return true;
    }

    public static boolean less(Comparable foo, Comparable bar){
        return foo.compareTo(bar) < 0;
    }

    public static void exchange(Comparable[] arr, int i, int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
