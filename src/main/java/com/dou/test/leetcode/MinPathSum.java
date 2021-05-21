package com.dou.test.leetcode;

import cn.hutool.core.util.NumberUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 已知一个二维数组，其中存储了非负整数，我们要找到从左上角到右下角的一条路径，
 * 使得路径上的数字加在一起的和最小，求出这个最小值。
 */
public class MinPathSum {

    static int[][] grid = {
            {1, 7, 8, 0, 5},
            {7, 5, 6, 2, 8},
            {5, 8, 6, 7, 0},
            {3, 8, 6, 2, 7},
            {4, 6, 0, 8, 5}
    };

    public static void main(String[] args) {
        System.out.println(minPathSum(grid));
    }

    /**
     * 元素grid[i][j]的最优解释他的上方或者左方最优解中最小的+本身
     */
    static int minPathSum(int[][] grid) {
        int rowSize = grid.length;
        int colSize = grid[0].length;
        int[][] dp = new int[rowSize][colSize];

        dp[0][0] = grid[0][0];

        // 第一行
        for (int c = 1; c < colSize; c++) {
            dp[0][c] = dp[0][c - 1] + grid[0][c];
        }
        // 第一列
        for (int r = 1; r < rowSize; r++) {
            dp[r][0] = dp[r - 1][0] + grid[r][0];
        }

        // 其他
        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                dp[i][j] = NumberUtil.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        printArr(grid);
        printArr(dp);
        minPathArr(dp, grid);
        return dp[rowSize - 1][colSize - 1];
    }

    static void minPathArr(int[][] dp, int[][] arr) {
        int rowSize = dp.length;
        int colSize = dp[0].length;
        List<Integer> list = new ArrayList<>();

        int r = rowSize - 1;
        int c = colSize - 1;
        list.add(arr[r][c]);

        while (r >= 0 && c >= 0) {

            if (r == 0 && c > 0) {
                list.add(arr[r][c - 1]);
                c = c - 1;
                continue;
            }

            if (c == 0 && r > 0) {
                list.add(arr[r - 1][c]);
                r = r - 1;
                continue;
            }

            if (c > 0 && r > 0) {
                if (dp[r][c - 1] == (dp[r][c] - arr[r][c])) {
                    c = c - 1;
                } else {
                    r = r - 1;
                }
                list.add(arr[r][c]);
            } else {
                break;
            }

        }

        Collections.reverse(list);
        list.forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }

    static void printArr(int[][] array) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.print(anInt);
                System.out.print(" ");
            }
            System.out.println();
        }
    }


}
