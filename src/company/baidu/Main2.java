package company.baidu;

import java.util.Arrays;

/**
 * 还原数列
 *
 * @author ihaokun
 * @date 2019/9/24 20:13
 */
public class Main2 {
    public static void main(String[] args) {
        solution(new int[]{1, 0, 2}, 0);
    }

    private static void solution(int[] ints, int k) {
        Arrays.sort(ints);
        int max = ints[ints.length - 1];
        if (max < ints.length) {
            System.out.println("k = " + k);
            System.out.println("max = " + max);
            return;
        }
        ints[ints.length - 1] -= ints.length;
        for (int i = 0; i < ints.length - 1; i++) {
            ints[i] += 1;
        }
        solution(ints, k+1);
    }
}
