# 《Algorithms》 fourth edition

## 第二章 排序(Sorting)  
2.1.1.3 额外的内存使用

　　排序算法的额外内存开销和运行时间是同等重要的。  
　　排序算法可以分为两类：
除了函数调用所需的*stack*和固定数目的实例变量外无需额外内存的<u>原地排序（in-place）</u>算法；
以及需要额外内存空间来存储另一份数组副本的其它排序算法。

## 第三章 查找(Searching)
<s>摘要</s>（关键词）：  
经典查找算法  
‘符号表’，有时被称为‘字典’或‘索引’

>Chapter 3: **_Searching_**, describes several classic symbol-table implementations,
include binary search trees, red-black trees, and hash tables.  
上述描述摘自官网

### 3.1 符号表

存储形式为key-val的数据结构，高效地插入(put)和查找(get)  
Java对其的实现是Map接口

3.1.2.8 成本模型  
学习符号表的实现过程：  
equals()方法和compareTo()方法（对于实现Comparable(I)接口的类）  
查找的成本模型：**比较** 和 数组**访问**的次数  
get()、put()方法