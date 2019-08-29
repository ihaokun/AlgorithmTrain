package jianzhi;

import java.util.ArrayList;

/**
 * <pre>
 * 剑指offer - 顺时针打印矩阵（二维数组）
 * 题目描述：
 * 		输入一个矩阵，
 * 		按照 从外向里 以 顺时针 的顺序依次打印出每一个数字，
 * 		例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 		则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
 * 考察知识点：
 * 		二维数组；短时间内找出规律并确立思路，用代码逻辑实现它
 * 小结：
 * 		这题AC不容易，从20+ - 30+ - 80+ - 100，大概做了4次才AC
 *
 * </pre>
 *
 * @author ihaokun
 * @date 2019年8月15日
 * @see <a href="https://blog.csdn.net/u013132035/article/details/80594659">reference link</a>
 */
public class PrintMatrixClockwise {

    public static void main(String[] args) {
        // init
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
        };
        // print
        System.out.println(printMatrix(matrix));
    }

    /**
     * 解题思路：
     * 二维数组，<strong>起点 和 行列数</strong>是关键
     * 由外向内 顺时针 的顺序，由外向内遍历的每一层 均 顺时针遍历
     * 由外向内:(x,y)轴形式，起点坐标各+1，同时行、列数均-2
     * 顺时针：left -> right，up -> down，right -> left，down -> up，第一个一定有，后三个不一定有
     *
     *
     * 递归、循环、迭代、遍历
     *
     * @param matrix
     * @return
     */
    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        // 得到行数、列数、原点X、Y轴坐标
        int rows = matrix.length;
        int columns = matrix[0].length;
        int originX = 0;
        int originY = 0;
        while (rows > 0 && columns > 0) {
            printTier(arrayList, matrix, originX, originY, rows, columns);
            originX += 1;
            originY += 1;
            rows -= 2;
            columns -= 2;
        }
        return arrayList;
    }

    /**
     * 打印矩阵的一层
     *
     * @param arrayList ArrayList
     * @param matrix    矩阵
     * @param originX   原点X坐标
     * @param originY   原点Y坐标
     * @param rows      行数
     * @param columns   列数
     */
    public static void printTier(ArrayList<Integer> arrayList, int[][] matrix, int originX, int originY, int rows,
                                 int columns) {
        int x = originX, y = originY;
        int boardX = columns + x, boardY = rows + y;
        // left -> right
        for (; x < boardX; x++) {
            arrayList.add(matrix[y][x]);
            System.out.println(matrix[y][x]);
        }
        // up -> down
        if (rows > 1) {
            for (--x, ++y; y < boardY; y++) {
                arrayList.add(matrix[y][x]);
                System.out.println(matrix[y][x]);
            }
            // right -> left
            if (columns > 1) {
                for (--y, --x; x >= originX; x--) {
                    arrayList.add(matrix[y][x]);
                    System.out.println(matrix[y][x]);
                }
                // down -> up
                if (rows > 2) {
                    for (++x, --y; y > originY; y--) {
                        arrayList.add(matrix[y][x]);
                        System.out.println(matrix[y][x]);
                    }
                }
            }

        }
    }

}
