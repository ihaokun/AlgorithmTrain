package company.trend;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 排列组合问题
 *
 * @author ihaokun
 * @date 2019/8/8 19:21
 */
public class Main {
    private static final int amount1 = 1;
    private static final int amount2 = 5;
    private static final int amount3 = 10;
    private static final int amount4 = 20;
    private static final int amount5 = 50;
    private static final int amount6 = 100;

    private static String process(String num1, String num2)
    {
        //Please write your code here
        String[] s = num1.split(" ");
        Map<Integer, Integer> map = new LinkedHashMap<>();
        map.put(amount1, Integer.valueOf(s[0]));
        map.put(amount2, Integer.valueOf(s[1]));
        map.put(amount3, Integer.valueOf(s[2]));
        map.put(amount4, Integer.valueOf(s[3]));
        map.put(amount5, Integer.valueOf(s[4]));
        map.put(amount6, Integer.valueOf(s[5]));
        return "-1";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String strValueSequences = sc.nextLine();
        String strChargeNum = sc.nextLine();

        String sum = process(strValueSequences, strChargeNum);
        System.out.println(sum);
    }
}
