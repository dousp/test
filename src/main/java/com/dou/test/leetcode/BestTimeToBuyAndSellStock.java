package com.dou.test.leetcode;

public class BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
        int max = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if( (prices[i] - min) > max){
                max = prices[i] - min;
            }
            if(prices[i] < min) {
                min = prices[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] price = {7,1,5,3,6,4} ;
        // int[] price1 = new int[]{7,1,5,3,6,4} ;

        System.out.println(maxProfit(price));


    }
}
