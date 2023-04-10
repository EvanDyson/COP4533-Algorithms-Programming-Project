// Maryam Alyounes

package ALGs;
import java.util.*;

public class ALG5 {
    public static int run_ALG5(int[][] A, int k) {
        final long startTime = System.nanoTime();

        int[][][] dp = new int[A.length][A[0].length][k + 1];

        for (int t = 1; t <= k; t++) {
            for (int m = 0; m < A.length; m++) {
                for (int n = 1; n < A[m].length; n++) {
                    int maxProfit = dp[m][n-1][t];
                    for (int j = 0; j < n; j++) {
                        for (int p = 0; p <= t-1; p++) {
                            int profit = (j == 0) ? 0 : dp[m][j-1][p];
                            profit -= A[m][j];
                            profit += A[m][n];
                            maxProfit = Math.max(maxProfit, profit + dp[m][j][t-1]);
                        }
                    }
                    dp[m][n][t] = maxProfit;
                }
            }
        }

        int maxProfit = 0;
        for (int m = 0; m < A.length; m++) {
            for (int n = 0; n < A[m].length; n++) {
                maxProfit = Math.max(maxProfit, dp[m][n][k]);
            }
        }
        final long endTime = System.nanoTime();
        long elapsedTimeMillis = (endTime - startTime);
        System.out.println("|| ALG 5 took " + elapsedTimeMillis + " nanoseconds\tusing m = " + A.length + "\tn = " + A[0].length + "\tk = " + k);
        return maxProfit;
    } 
    
    public static void task5() {
        Scanner scanner = new Scanner(System.in);
        String k = scanner.nextLine();
        String lineOne = scanner.nextLine();
        String[] lineOneParts = lineOne.split("\\s+");
        int[][] copy = new int[Integer.valueOf(lineOneParts[0])][Integer.valueOf(lineOneParts[1])];
        for (int i = 0; i < Integer.valueOf(lineOneParts[0]); i++) {
            String temp = scanner.nextLine();
            String[] tempParts = temp.split("\\s+");
            for (int j = 0; j < Integer.valueOf(lineOneParts[1]); j++) {
                copy[i][j] = Integer.valueOf(tempParts[j]);
            }
        }
        scanner.close();

        // Create a 3D array to store the maximum profit for each day, transaction, and stock
        int[][][] dp = new int[copy.length][copy[0].length][Integer.parseInt(k) + 1];

        // Iterate over each transaction up to k
        for (int t = 1; t <= Integer.parseInt(k); t++) {
            // Iterate over each stock
            for (int m = 0; m < copy.length; m++) {
                // Iterate over each day
                for (int n = 1; n < copy[m].length; n++) {
                    int maxProfit = dp[m][n-1][t];
                    // Iterate over each possible buy day
                    for (int j = 0; j < n; j++) {
                        // Iterate over each possible transaction up to t-1
                        for (int p = 0; p <= t-1; p++) {
                            int profit = (j == 0) ? 0 : dp[m][j-1][p];
                            profit -= copy[m][j];
                            profit += copy[m][n];
                            maxProfit = Math.max(maxProfit, profit + dp[m][j][t-1]);
                        }
                    }
                    dp[m][n][t] = maxProfit;
                }
            }
        }

        int maxProfit = 0;
        int maxM = 0;
        int maxBuyDay = 0;
        int maxSellDay = 0;

        // Iterate over each stock, day, and transaction to find the maximum profit
        for (int m = 0; m < copy.length; m++) {
            for (int n = 0; n < copy[m].length; n++) {
                for (int t = 0; t <= Integer.parseInt(k); t++) {
                    if (dp[m][n][t] > maxProfit) {
                        maxProfit = dp[m][n][t];
                        maxM = m;
                        maxSellDay = n;
                        // Find the earliest buy day that leads to this maximum profit
                        for (int j = n; j >= 0; j--) {
                            int profit = dp[m][j][t-1];
                            profit -= copy[m][j];
                            profit += copy[m][n];
                            if (profit + dp[m][j-1][t-1] == maxProfit) {
                                maxBuyDay = j;
                                break;
                            }
                        }
                    }
                }
            }
        }
        // Print the stock, buy day, and sell day indices
        System.out.println("Stock: " + maxM + ", BuyDay: " + maxBuyDay + ", SellDay: " + maxSellDay);
    }
}
