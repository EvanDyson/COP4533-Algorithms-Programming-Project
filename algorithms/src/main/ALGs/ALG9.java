// Maryam Alyounes

package ALGs;
import java.util.*;

public class ALG9 {
    public static int run_ALG9(int[][] A, int c) {
        final long startTime = System.nanoTime();

        // Find the number of rows and columns in the price matrix
        int m = A.length;
        int n = A[0].length;

        int[][] newMatrix = new int[m][n];

        // Iterate through each day
        for(int j = 1; j < n; j++) {
            // Iterate through each stock
            for(int i = 0; i < m; i++) {
                // Set the initial maximum profit to the maximum profit on the previous day
                int maxProfit = newMatrix[i][j-1];

                // Iterate through each possible stock to buy on day j-c-1 and sell on day j
                for(int l = 0; l < m; l++) {
                    // Check if it is possible to buy stock k on day j-c-1 and sell stock i on day j
                    if(j >= c+1 && j-c-1 >= 0) {
                        // Calculate the profit if we buy stock k on day j-c-1 and sell stock i on day j
                        int profit = newMatrix[l][j-c-1] + A[i][j] - A[l][j-c];
                        // Update the maximum profit if the profit from buying stock k and selling stock i is greater
                        maxProfit = Math.max(maxProfit, profit);
                    }
                }
                // Store the maximum profit for stock i on day j in the newMatrix array
                newMatrix[i][j] = maxProfit;
            }
        }

        // Find the maximum profit by looking at the last column of the newMatrix array
        int maxProfit = 0;
        for(int i = 0; i < m; i++) {
            maxProfit = Math.max(maxProfit, newMatrix[i][n-1]);
        }
        // Return the maximum profit
        final long endTime = System.nanoTime();
        long elapsedTimeMillis = (endTime - startTime);
        System.out.println("|| ALG 9 took " + elapsedTimeMillis + " nanoseconds\tusing m = " + A.length + "\tn = " + A[0].length + "\tc = " + c);
        return maxProfit;
    }

    public static void task9() {
        Scanner scanner = new Scanner(System.in);
        String c = scanner.nextLine();
        String lineOne = scanner.nextLine();
        String[] lineOneParts = lineOne.split("\\s+");
        int[][] A = new int[Integer.valueOf(lineOneParts[0])][Integer.valueOf(lineOneParts[1])];
        for (int i = 0; i < Integer.valueOf(lineOneParts[0]); i++) {
            String temp = scanner.nextLine();
            String[] tempParts = temp.split("\\s+");
            for (int j = 0; j < Integer.valueOf(lineOneParts[1]); j++) {
                A[i][j] = Integer.valueOf(tempParts[j]);
            }
        }
        scanner.close();

        // Find the number of rows and columns in the price matrix
        int m = A.length;
        int n = A[0].length;

        // Initialize newMatrix table and auxiliary arrays
        int[][] newMatrix = new int[m][n];
        int[][] buyDays = new int[m][n];
        int[][] sellDays = new int[m][n];

        // fill in newMatrix array and buyDays/sellDays arrays using dynamic programming
        for(int j = 1; j < n; j++) {
            for(int i = 0; i < m; i++) {
                // maximum profit if we don't sell stock i on day j
                int maxProfit = newMatrix[i][j-1];
                // best buying day for stock i if we don't sell on day j 
                int bestBuyDay = buyDays[i][j-1];
                // best selling day for stock i if we don't sell on day j 
                int bestSellDay = sellDays[i][j-1]; 
                for(int l = 0; l < m; l++) {
                    // check if we can buy stock k on day j-c-1 and sell stock i on day j
                    if(j >= Integer.parseInt(c)+1 && j-Integer.parseInt(c)-1 >= 0) {
                        // profit if we buy stock k on day j-c-1 and sell stock i on day j
                        int profit = newMatrix[l][j-Integer.parseInt(c)-1] + A[i][j] - A[l][j-Integer.parseInt(c)];
                        if(profit > maxProfit) {
                            maxProfit = profit;
                            bestBuyDay = j-Integer.parseInt(c)-1;
                            bestSellDay = j;
                        }
                    }
                }
                newMatrix[i][j] = maxProfit;
                buyDays[i][j] = bestBuyDay;
                sellDays[i][j] = bestSellDay;
            }
        }

        // print transactions for each stock in reverse order, starting from the last day
        for(int i = 0; i < m; i++) {
            // last day
            int j = n-1; 
            // best buying day and best selling day for stock i on the last day
            int buyDay = buyDays[i][j];
            int sellDay = sellDays[i][j];
            
            // continue until we reach the first day 
            while(buyDay >= 0) { 
                // print transaction for stock i
                System.out.println(i + " " + buyDay + " " + sellDay);
                // move to the day before the best buying day 
                j = buyDay - 1; 
                // check if we have a valid day
                if(j >= 0) { 
                    // update best buying day for stock i on this day
                    buyDay = buyDays[i][j]; 
                    // update best selling day for stock i on this day
                    sellDay = sellDays[i][j];
                } 
                else {
                    // set buyDay to -1 to exit the loop
                    buyDay = -1; 
                }
            }
        }
    }
}
