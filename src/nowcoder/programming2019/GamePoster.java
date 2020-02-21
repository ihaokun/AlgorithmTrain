package nowcoder.programming2019;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 游戏海报——快手
 *
 * 题目描述：
 * 小明有26种游戏海报，用小写字母"a"到"z"表示。小明会把游戏海报装订成册（可能有重复的海报），册子可以用一个字符串来表示，每个字符就表示对应的海报，例如abcdea。
 * 小明现在想做一些“特别版”，然后卖掉。特别版就是会从所有海报（26种）中随机选一张，加入到册子的任意一个位置。
 * 那现在小明手里已经有一种海报册子，再插入一张新的海报后，他一共可以组成多少不同的海报册子呢？
 *
 * 输入描述：
 * 海报册子的字符串表示，1 <= 字符串长度<= 20
 *
 * 输出描述：
 * 一个整数，表示可以组成的不同的海报册子种类数
 *
 * @author ihaokun
 * @date 2020/2/21 17:35
 */
public class GamePoster {
  // one time pass
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();
    // 这是一个插入问题，这道题可以使用hashtable来解，穷举法+额外的空间换时间
    char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
    'u', 'v', 'w', 'x', 'y', 'z'};
    HashSet<String> hashSet = new HashSet<>();
    StringBuilder sb = new StringBuilder(s);
    for (char c : chars) {
      for (int i = 0; i < s.length() + 1; i++) {  //CRUX 注意插入在尾端的情况
        sb.insert(i, c);
        hashSet.add(sb.toString());
        sb.delete(i, i + 1);
      }
    }
    System.out.println(hashSet.size());
  }
}