package nowcoder.jianzhi.list.array;

import java.util.Arrays;

/**
 * <pre>
 *     剑指offer - 构建乘积数组
 *
 *     题目描述：
 *          给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 *          不能使用除法。
 *
 *     <em>注意看题目描述：
 *          乘积中没有A[i]
 *          不能使用除法</em>
 *
 *     考点：
 *          数组
 *     知识点：
 *          数组
 * </pre>
 *
 * @author ihaokun
 * @date 2019/9/21 21:45
 */
public class ArrayMultiply {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(multiply(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(multiply1(new int[]{1, 2, 3, 4, 5})));
    }

    /**
     * <pre>
     *     解法一：
     *          使用双重for循环，时、空间复杂度 均为 O（N²）
     * </pre>
     *
     * @param array 原数组
     * @return 乘积数组
     */
    private static int[] multiply(int[] array) {
        if (array.length == 0)
            return null;
        int[] productArray = new int[array.length];
        for (int i = 0; i < productArray.length; i++) {
            int product = 1;
            for (int j = 0; j < array.length; j++) {
                if (j != i)
                    product *= array[j];
            }
            productArray[i] = product;
        }
        return productArray;
    }

    /**
     * <pre>
     *     解法二：
     *          注意题目的乘积计算方式：B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]
     *          可以分为两步来做，分别计算 下标i 之前 和 之后的部分
     * </pre>
     *
     * @param array 原数组
     * @return 乘积数组
     */
    private static int[] multiply1(int[] array) {
        int len = array.length;
        int[] prodArr = new int[len];
        int prod = 1;
        for (int i = 0; i < len; i++) {
            prodArr[i] = prod;
            prod *= array[i];
        }
        prod = 1;
        for (int i = len - 1; i >= 0; i--) {
            prodArr[i] *= prod;
            prod *= array[i];
        }
        return prodArr;
    }
}
