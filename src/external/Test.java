package external;

/**
 * @author ihaokun
 * @date 2019/8/8 15:56
 */
public class Test {
    static int N = 5;
    static int M = 3;
    static int[] a = new int[]{1, 2, 3, 4, 5};
    static int[] b = new int[M];

    public static void main(String[] args) {
        C(N, M);
    }

    static void C(int n, int m) {
        int i, j;
        for (i = m; i <= n; i++) {
            b[m - 1] = i - 1;
            if (m > 1)
                C(i - 1, m - 1);
            else {
                for (j = 0; j <= M - 1; j++)
                    System.out.print(a[b[j]] + "  ");
                System.out.println();
            }
        }
    }
}
