package ALGs;
/* 
We are given a array of price predictions for m stocks for n consecutive days. 
The price of stock i for day j is A[i][j] for i = 1, ... ,m and j = 1, ... ,n. 
You are tasked with finding the maximum possible prot by buying and selling stocks. 
The predicted price at any day will always be a non-negative integer. 
You can hold at most one share of any stock at any time. 
You are allowed to buy a stock on the same day you sell another stock. More formally,

Problem1: Given a matrix A of m x n integers (non-negative) representing the predicted prices of m stocks 
for n days, find a single transaction (buy and sell) that gives maximum prot.
*/

/*
 7 40 66 36 61 22
41 50 41 58 4 32
63 22 10 48 19 74
56 48 85 66 82 45
18 36 1 62 51 21
10 41 19 51 14 5
 */

// Evan



public class ALG1 {
    public static int run_ALG1() {
        //System.out.println("ALG1 GOOD");

        // case 1 where one stock and one day, return 0 profit.
        // case 2 where one stock and multiple days, return the highest and lowest price day given that the lowest is before the highest
        // case 3 where more than 1 stock, return the highest different between the highest and lowest price day given that the lowest is before the highest on any stock
        int[][] stockList1 = {
            {51,7,23,57,53,16},
            {52,10,54,84,7,19},
            {71,16,25,19,20,39},
            {56,72,1,81,34,21},
            {80,82,81,73,33,12},
            {64,66,82,14,7,3}
        };// answer is in row 4 80 (81 - 1)

        int[][] stockList2 = {
            {51,7,23,57,53,16},
        };// answer is 50 (57 - 7)

        int[][] stockList3 = {
            {100}
        };// answer is 0

        int[][] stockList4 = {
            { 100 },
            {80, 23, 13, 3},
            {5, 160},
            {3, 6, 8, 10, 11}
        };// answer is 155 (160 - 5)

        // i = stocks
        // j = days
        int[][] copy = stockList4;
        int maxProfit = 0;

        for (int i = 0; i < copy.length; i++) {
            int maxProfitPerStock = 0;
            int minPrice = copy[i][0];

            for (int j = 0; j < copy[i].length; j++) {
                if (copy[i][j] < minPrice)
                    minPrice = copy[i][j];
                else {
                    int currentProfit = copy[i][j] - minPrice;
                    maxProfitPerStock = Math.max(currentProfit, maxProfitPerStock);
                }
            }

            // check if the current stock's max profit is larger than the overall max profit
            // if it is replace it, otherwise continue
            if (maxProfitPerStock > maxProfit)
                maxProfit = maxProfitPerStock;
        }

        System.out.print("|| Algorithm 1: ");
        return maxProfit;
    }
}