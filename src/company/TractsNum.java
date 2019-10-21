package company;

/**
 * 数字广东 - 判断一个数是否为回文数
 *
 * @author ihaokun
 * @date 2019/10/17 10:02
 */
public class TractsNum {
  public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // int i = sc.nextInt();
    StringBuilder builder = new StringBuilder(String.valueOf(12321));
    System.out.println(builder.toString().equals(builder.reverse().toString()));
  }
}
