package external;

/**
 * <pre>
 * 饿了么 一道面试题
 * 题目描述：
 * 		从n个字符中找到m个字符的组合，比如abc，找出2个字符的组合即为ab,ac,bc
 * 解题思路：
 * 		根据《离散数学及其应用》chapter6计数，具体参考chapter6.6.3生成组合
 * 		《离散数学》这章的内容，在《组合数学》一书中有详细说明，有空也可以看看
 * </pre>
 *
 * @author ihaokun
 * @date 2019年8月13日
 */
public class Eleme {
	
	public static void main(String[] args) {
		System.out.println(factorial(5));
		System.out.println(countCombination(5, 3));
		System.out.println("****************************************************");
		String str = "fdsagdas";
		printCombination(str, 2);
	}

	/**
	 * 打印 组合可能
	 * 方法1：使用离散数学的 位串、字典序
	 * 方法2：使用递归
	 * 
	 * @param str 原字符串
	 * @param r 需组合元素个数
	 */
	private static void printCombination(String str, int r) {
		StringBuilder bitStrMin = new StringBuilder(str.length());
		StringBuilder bitStrMax = new StringBuilder(str.length());
		// 初始化最小值、最大值
		for (int i = 0; i < str.length() -r; i++) {
			bitStrMin.append(0);
			bitStrMax.append(0);
		}
		for (int i = 0; i < r; i++) {
			bitStrMin.append(1);
			bitStrMax.insert(0, 1);
		}
		System.out.println(bitStrMin);
		transform(str, bitStrMin.toString());
		System.out.println(bitStrMax);
		transform(str, bitStrMax.toString());
		for (int i = Integer.valueOf(bitStrMin.toString(), 2) + 1; i < Integer.valueOf(bitStrMax.toString(), 2); i++) {
			String binaryString = Integer.toBinaryString(i);
			// Java包装类Integer的toBinaryString()方法，trim了开头的0，这里补位0
			if (binaryString.length() < str.length()) {
				StringBuilder foo = new StringBuilder(binaryString);
				for (int j = 0; j < str.length() - binaryString.length(); j++) {
					foo.insert(0, 0);					
				}
				binaryString = foo.toString();
			}
			// 符合条件，打印输出
			if (countOneFromStr(binaryString) == r) {
				System.out.println(binaryString);
				transform(str, binaryString);
			}
		}
	}
	
	/**
	 * 简单统计二进制字符串中1的个数
	 * 
	 * @param binaryStr 二进制字符串
	 * @return 二进制字符串中1的个数
	 */
	private static int countOneFromStr(String binaryStr) {
		int count = 0;
		for (char i : binaryStr.toCharArray()) {
			if(i == '1') {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 位串翻译<br/>
	 * 获得翻译后的字符串并打印输出
	 * 
	 * @param str 原字符串
	 * @param bitStr 对应的位串
	 */
	private static void transform(String str, String bitStr) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (bitStr.substring(i, i + 1).equals("1")) {
				builder.append(str.subSequence(i, i + 1));
			}
		}
		System.out.println(builder);
	}
	
	/**
	 * 计数，组合数
	 * 根据数学公式 C(n,r) = n! / r!(n-r)!
	 * <pre>
	 * 推导过程：已知 排列公式为P(n,r) = n * (n-1) *...* (n-r+1) 
	 * 								  = n! / (n-r)!
	 * 								  = C(n,r) * P(r,r)
	 * 						 故C(n,r) = P(n,r) / P(r,r)
	 * 								  = n! / r!(n-r)!
	 * </pre>
	 * 
	 * @param n 集合元素数
	 * @param r 需组合元素个数
	 * @return
	 */
	private static int countCombination(int n, int r) {
		return factorial(n) / (factorial(n - r) * factorial(r));
	}
	
	/**
	 * 数学 阶乘计算
	 * 
	 * @param n 正整数
	 * @return 阶乘结果
	 */
	private static int factorial(int n) {
		if (n <= 0) {
			return -1;
		}
		int product = 1;
		while(n > 0) {
			product *= n--;
		}
		return product;
	}
	
}
