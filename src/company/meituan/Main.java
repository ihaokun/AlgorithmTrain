package company.meituan;

/**
 * 美团骑手包裹区间分组
 *
 * <p>题目描述： 2110年美团外卖火星第3000号配送站点有26名骑手，分别以大写字母A-Z命名，因此可以称呼这些骑手为黄家骑士特工A，黄家骑士特工B…黄家骑士特工Z，
 * 某美团黑珍珠餐厅的外卖流水线上会顺序产出一组包裹，美团配送调度引擎已经将包裹分配到骑手，并在包裹上粘贴好骑手名称， 如RETTEBTAE代表一组流水线包裹共9个，同时分配给了名字为A B E R
 * T的5名骑手。
 * 请在<i>不打乱流水线产出顺序</i>的情况下，把这组包裹划分为<i>尽可能多的片段</i>，<i>同一个骑手只会出现在其中的一个片段</i>，返回一个表示每个包裹片段的长度的列表。
 *
 * <p>Test Case: 输入：MPMPCPMCMDEFEGDEHINHKLIN 输出：9 7 8 MPMPCPMCM,DEFEGDE,HINHKLIN
 *
 * <p>猜测：应该是 动态规划 题目，估计做不出来，有空要先做做下剑指offer的那最后一道动态规划题{@link nowcoder.jianzhi.CutRope}
 *
 * @author ihaokun
 * @date 2019/10/16 15:49
 */
public class Main {
  public static void main(String[] args) {
    // InOut
    // Scanner sc = new Scanner(System.in);
    // String str = sc.nextLine();
    // Test Case
    String literal = "MPMPCPMCMDEFEGDEHINHKLIN";
    Main main = new Main();
    main.segment(literal);
  }

  // 递归
  private void segment(String str) {
    // 思路简要描述：从第一个分段开始，先得到第一个字符的截止
    // 再判断，剩余数组中是否含有第一个分段中其他元素；若有，则分段继续增加，否，则分段完毕
    if (str.length() > 0) {
      // 第一字段的初始生成
      StringBuilder builder = new StringBuilder(str);
      String firstChar = builder.substring(0, 1);
      int lastIndexOfFirst = builder.lastIndexOf(firstChar);
      StringBuilder firstSegment = new StringBuilder(builder.subSequence(0, lastIndexOfFirst + 1));
      builder.delete(0, lastIndexOfFirst + 1);
      // 剩余数组的判断
      for (int i = 1; i < firstSegment.length(); i++) {
        String current = firstSegment.substring(i, i + 1);
        if (!current.equals(firstChar) && builder.toString().contains(current)) {
          //XXX 这里出了问题，导致当时提交时没通过，应该使用while，而不是fori结构
          while (builder.toString().contains(current)){
            firstSegment.append(builder, 0, 1);
            builder.delete(0, 1);
          }
        }
      }
      //
      System.out.println(firstSegment);
      System.out.println(firstSegment.length());
      segment(builder.toString());
    }
  }
}
