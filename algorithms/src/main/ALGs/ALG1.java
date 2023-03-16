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

// Evan



public class ALG1 {
    public static int run_ALG1(int[][] copy) {
        int maxProfit = 0;
        
        for(int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                for (int k = j+1; k < copy[i].length; k++) {
                    int profit = copy[i][k] - copy[i][j];
                    maxProfit = Math.max(maxProfit, profit);
                }
            }
        }
        
        System.out.print("|| Algorithm 1: ");       return maxProfit;
    }
}