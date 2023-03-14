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
	public static void main(String args[]){  
        System.out.println("===========================");
        int[][] stockList = {
            {51,7,23,57,53,16},
            {52,10,54,84,7,19},
            {71,16,25,19,20,39},
            {56,72,1,81,34,21},
            {80,82,81,73,33,12},
            {64,66,82,14,7,3}
        };
        for (int i = 0; i < stockList.length; i++)
        {
            for (int j = 0; j < stockList[0].length; j++)
            {
                if (j == stockList[0].length - 1)
                    System.out.print(stockList[i][j]);
                else {
                    System.out.print(stockList[i][j]);
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
	    //System.out.println("Hello world!!");  
        System.out.println("===========================");
	}  
}