// Maryam Alyounes

package ALGs;
import java.util.*;

public class ALG8 {
    public static int run_ALG8(int[][] A, int c) {
        final long startTime = System.nanoTime();

        // Find the number of rows and columns in the price matrix
        int m = A.length;
        int n = A[0].length;

        int[][] newMatrix = new int[m][n];

        // Iterate through each day
        for(int j = 0; j < n; j++) {
            // Iterate through each stock
            for(int i = 0; i < m; i++) {
                // If it's the first day, there can be no profit, so set it to 0
                if(j == 0) {
                    newMatrix[i][j] = 0;
                }
                else {
                    // Initialize the current max profit to be the same as the previous day
                    newMatrix[i][j] = newMatrix[i][j-1];
                    // Iterate through all possible buy days within the waiting period
                    for(int l = j-c; l < j; l++) {
                        // Only compute the profit if it's a valid buy day and it's not the same as the current stock
                        if(l >= 0 && i != l) {
                            // Compute the profit from buying at k and selling at j for the current stock and add the profit
                            // from previous transactions if the waiting period has passed
                            newMatrix[i][j] = Math.max(newMatrix[i][j], A[l][j] - A[i][l] + (l >= c+1 ? newMatrix[i][l-c-1] : 0));
                        }
                    }
                }
            }
        }
        
        // Find the maximum profit among all stocks at the last day
        int maxProfit = 0;
        for(int i = 0; i < m; i++) {
            maxProfit = Math.max(maxProfit, newMatrix[i][n-1]);
        }
        // Add 1 to the maximum profit to account for the fact that the profit can be 0
        final long endTime = System.nanoTime();
        long elapsedTimeMillis = (endTime - startTime);
        System.out.println("|| ALG 8 took " + elapsedTimeMillis + " nanoseconds\tusing m = " + A.length + "\tn = " + A[0].length + "\tc = " + c);
        return maxProfit+1;
    }

    public static void task8() {
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
        int[][] buyDay = new int[m][n];
        int[][] sellDay = new int[m][n];

        for(int j = 0; j < n; j++) {
            for(int i = 0; i < m; i++) {
                if(j == 0) {
                    // Base case: no profit on first day
                    newMatrix[i][j] = 0;
                }
                // Iterate over all possible buy days
                else {
                    // Start by not buying/selling on day j
                    newMatrix[i][j] = newMatrix[i][j-1];
                    // Same as previous day
                    buyDay[i][j] = buyDay[i][j-1];
                    sellDay[i][j] = sellDay[i][j-1];
                    for(int l = j-Integer.parseInt(c); l < j; l++) {
                        // Ensure valid indices and not buying from self
                        if(l >= 0 && i != l) {
                            int profit = A[l][j] - A[i][l] + (l >= Integer.parseInt(c)+1 ? newMatrix[i][l-Integer.parseInt(c)-1] : 0);
                            // Compute profit if buying on day k, selling on day j, and using newMatrix table
                            if(profit > newMatrix[i][j]) {
                                // Update newMatrix table if better profit
                                newMatrix[i][j] = profit;
                                buyDay[i][j] = l;
                                sellDay[i][j] = j;
                            }
                        }
                    }
                }
            }
        }
        // Find maximum profit and corresponding index i
        int maxProfit = 0;
        int maxValue = 0;
        for(int i = 0; i < m; i++) {
            if(newMatrix[i][n-1] > maxProfit) {
                maxProfit = newMatrix[i][n-1];
                maxValue = i;
            }
        }
        // Backtrack from last day to first day and print out indices of buy/sell days
        int curProfit = maxProfit;
        int curValue = maxValue;
        int curValueJ = n-1;
        Stack<String> stack = new Stack<>();
        while(curValueJ > 0) {
            // Bought/sold on day j
            if (newMatrix[curValue][curValueJ] != newMatrix[curValue][curValueJ-1]) {
                stack.push((curValue+1) + " " + (buyDay[curValue][curValueJ]+1) + " " + (sellDay[curValue][curValueJ]+1));
                // Subtract profit from buying/selling on day j
                curProfit -= (A[sellDay[curValue][curValueJ]][curValueJ] - A[buyDay[curValue][curValueJ]][sellDay[curValue][curValueJ]]);
                // Move to previous buy day
                curValue = buyDay[curValue][curValueJ];
                // Next sell day is c days after previous buy day
                curValueJ = curValue + Integer.parseInt(c);
            }
            else {
                // Didn't buy/sell on day j, move to previous day
                curValueJ--;
            }
        }
        // Print out buy/sell day indices in reverse order
        while(!stack.isEmpty()) {
            System.out.println((stack.size() + 1) + " " + stack.pop());
        }
    }
}
