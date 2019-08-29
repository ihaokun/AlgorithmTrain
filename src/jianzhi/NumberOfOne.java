package jianzhi;

/**
 * <pre>
 * 剑指offer题目 - 二进制中一的个数
 *
 * 题目描述：
 * 		输入一个整数，输出该整数二进制中表示1的个数。其中负数用补码表示。
 *
 * 考点：位运算
 * 知识点：进制转化、原码反码补码
 *
 * 考察部分：
 * 		计算机基础、Java位运算符&|^~的运用
 * 
 * 解法：
 * 		前两个解法都是先将整数转为二进制字符串，再统计字符串中1的个数；
 * 		解法一直接用了JDK类库，详见{@link #solution1(int)}，类库{@link Integer#toBinaryString(int)}
 * 		解法二手写实现了int转二进制字符串的方法
 * 		
 * 小结：
 * 		解法上看，解法二不推荐用，比较费时间，可以直接用解法一替代
 * 		最优解是解法三，抓住了题目中心，是聪明解法
 * </pre>
 * 
 * @author ihaokun
 * @date 2019年8月14日
 */
public class NumberOfOne {

	public static void main(String[] args) {
		// Java直接表示二bin、八octal、十六hex进制，直接常量（字面量literal）
		System.out.println(0B111);
		System.out.println(0777);
		System.out.println(0Xabf);
		System.out.println(Integer.valueOf("111", 2));
		System.out.println("*********************************************");
		int integer = 0;
		System.out.println(solution1(integer));
		System.out.println(solution2(integer));
		System.out.println(solution3(integer));
	}
	
	/**
	 * 解法3：
	 * 		去1操作
	 * 
	 * 基本实现原理：
	 * 		整数减去1，对应的二进制修改为：最右边开始第一个1变成0，后面的所有0变成1
	 * 		例如：3 - 1 = 2； 3 : 011； 2 ： 010		6:110,5:101
	 * 			  3 & 2 = 010						6 & 5 = 100
	 * 		那么，一个整数integer & (integer-1)，结果为消去了右起第一个1
	 * 
	 * 
	 * @param integer 整数
	 * @return 1的个数
	 */
	private static int solution3(int integer) {
		int count = 0;
		while (integer != 0) {
			count++;
			// 关键点在这，循环体内将二进制字符串抽掉最右端的1
			integer = integer & (integer - 1);
		}
		return count;
	}
	
	/**
	 * 解法2：
	 * 		手写十进制转二进制
	 * 		过程：先得到十进制的绝对值，转化为正数的二进制字符串，是负数的话就再转一次补码表示
	 * 
	 * @param integer 整数
	 * @return 1的个数
	 */
	private static int solution2(int integer) {
		boolean flag = false;
		// 若为负数，先转为绝对值
		if (integer < 0) {
			integer = Math.abs(integer);
			flag = true;
		}
		// 对正数进行二进制转换
		StringBuilder builder = new StringBuilder(decimal2Binary(integer));
		// 若为负数，则转为补码表示
		if (flag) {
			// 取反
			for (int i = 0; i < builder.length(); i++) {
				builder.setCharAt(i, builder.substring(i, i + 1).equals("0") ? '1' : '0');
			}
			// 加1
			for (int i = builder.length() - 1; i >= 0; i--) {
				if (builder.substring(i, i+1).equals("0")) {
					builder.setCharAt(i, '1');
					break;
				}
				builder.setCharAt(i, '0');
				builder.setCharAt(i - 1, '1');
			}
			// 前面补满32位
			int length = builder.length();
			for (int i = 0; i < 32 - length; i++) {
				builder.insert(0, 1);
			}
		}
		System.out.println(builder);
		return countOneOfBinaryStr(builder.toString());
	}
	
	/**
	 * 正整数转为二进制
	 * 
	 * @param integer 十进制正整数
	 * @return 二进制字符串
	 */
	private static String decimal2Binary(int integer) {
		if (integer == 0) {
			return "0";
		}
		StringBuilder builder = new StringBuilder();
		while (integer > 0) {
			builder.append(integer % 2);
			integer /= 2;
		}
		builder.reverse();
		return builder.toString();
	}
	
	/**
	 * 解法1：
	 * 		使用JDK类库，lang包下的Integer类的toBinaryString(int)方法，可直接得到整型int的二进制字符串
	 * 		然后统计 二进制字符串中 1的个数
	 * 
	 * @param integer 整数
	 * @return 1的个数
	 */
	private static int solution1(int integer) {
		String binaryString = Integer.toBinaryString(integer);
		System.out.println(binaryString);
		return countOneOfBinaryStr(binaryString);
	}
	
	/**
	 * 统计二进制字符串中1的个数
	 * 
	 * @param binaryStr 二进制字符串
	 * @return 1的个数
	 */
	private static int countOneOfBinaryStr(String binaryStr) {
		int count = 0;
		for (char foo : binaryStr.toCharArray()) {
			if (foo == '1') {
				count++;
			}
		}
		return count;
	}
	
}
