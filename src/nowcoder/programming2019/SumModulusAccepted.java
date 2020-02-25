package nowcoder.programming2019;

import java.util.Scanner;

/**
 * @author ihaokun
 * @date 2020/2/25 22:11
 */
public class SumModulusAccepted {
  public static void main(String[] args) {
    // input
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    long result = 0;    //output
    for (int i = 0; i < n; i++) {
      result += sc.nextLong();
    }
    sc.close();
    // output
    System.out.println(result - n);
  }
}