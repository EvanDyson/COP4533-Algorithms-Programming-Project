// Evan

package ALGs;

public class ALG4 {
    public static int run_ALG4(int[][] copy, int k) {
        int maxProfit = 0;

        /*
         Initialize two arrays buy and sell, both of size m, to keep track of the minimum buy price and maximum sell price seen so far for each stock. Initialize all elements of buy to Integer.MAX_VALUE and all elements of sell to 0.
Loop through each day j from 1 to n:
Loop through each stock i from 1 to m:
Update the value of buy[i] to be the minimum of its current value and A[i][j] - sell[i-1]. This means that we are considering buying stock i on day j and selling it on the previous day, after selling all previously bought stocks.
Update the value of sell[i] to be the maximum of its current value and A[i][j] - buy[i]. This means that we are considering selling stock i on day j, after buying it at some previous day with a minimum price.
The maximum profit after performing at most k transactions can be found in the last element of the sell array. Return this value.

         */



        System.out.print("|| Algorithm 4: ");   return maxProfit;
    }
}
