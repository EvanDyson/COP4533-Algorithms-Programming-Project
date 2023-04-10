// Maryam Alyounes

package ALGs;
import java.util.Scanner;

public class ALG3 {

    public static int run_ALG3(int[][] A) {
        final long startTime = System.nanoTime();

        // Find the number of rows and columns in the price matrix
        int m = A.length;
        int n = A[0].length;

        // Initialize variables
        int maxProfit = 0;
        int buyPrice = 0;
        int sellPrice = 0;
        int profit = 0;

        // Traverse through each row in the price matrix
        for(int i = 0; i < m; i++) {
            // set the buy price for the current day
            buyPrice = A[i][0];
            for(int j = 0; j < n; j++) {
                // set the sell price for the current day
                sellPrice = A[i][j];
                // calculate the profit that can be made
                profit = sellPrice - buyPrice;
                // If the profit is greater than the current maximum profit, update the maximum profit
                if(profit > maxProfit) {
                    maxProfit = profit;
                }
                // If the current sell price is less than the current buy price, 
                // update the buy price to the current sell price
                if(buyPrice > sellPrice) {
                    buyPrice = sellPrice;
                }
            }
        }
        final long endTime = System.nanoTime();
        long elapsedTimeMillis = (endTime - startTime);
        System.out.println("|| ALG 3 took " + elapsedTimeMillis + " nanoseconds\tusing m = " + A.length + "\tn = " + A[0].length);
        // Return the maximum profit that can be made
        return maxProfit;
    }
    
    // Memoization algorithm implementation
    public static void task3a() {
        Scanner scanner = new Scanner(System.in);
        String tempK = scanner.nextLine();
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

        // Initialize variables
        int[][] myMatrix = new int[m][n];
        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;

        // Loop through each row and column of the input array
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // If this is the first column, set the value of the first element
                // in the corresponding row to the value of the input array
                if(j == 0) {
                    myMatrix[i][j] = A[i][j];
                }
                // Otherwise, set the value of the current element to the maximum of
                // the previous element in the row and the difference between the current
                // element and the buy price on the same day
                else {
                    myMatrix[i][j] = Math.max(myMatrix[i][j-1], A[i][j] - A[i][buyDay]);
                }
                // If the profit from selling on the current day is greater than the
                // maximum profit seen so far, update the maximum profit and the sell day
                if(A[i][j] - A[i][buyDay] > maxProfit) {
                    maxProfit = A[i][j] - A[i][buyDay];
                    sellDay = j;
                }
                // If the profit calculated using the dynamic programming approach is greater
                // than the maximum profit seen so far, update the maximum profit, buy day, and sell day
                if(myMatrix[i][j] > maxProfit) {
                    maxProfit = myMatrix[i][j];
                    buyDay = j;
                    sellDay = j;
                }
            }
            // Print out the current row number, buy day, and sell day
            System.out.println(i + " " + buyDay + " " + sellDay);
        }
    }

    // Iterative BottomUp algorithm implementation
    public static void task3b() {
        Scanner scanner= new Scanner(System.in);
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

        // Initialize variables
        int[][] newMatrix = new int[m][n];
        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;    

        // Loop through each row and column of the input array
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // If we are in the first column, just copy the value from the input array
                if(j == 0) {
                    newMatrix[i][j] = A[i][j];
                }
                // Otherwise, calculate the maximum profit that can be made up to this day
                else {
                    newMatrix[i][j] = Math.max(newMatrix[i][j - 1], A[i][j] - A[i][buyDay]);
                }
                // If the profit for this day is higher than the maximum profit we've seen so far, update the variables
                if(A[i][j] - A[i][buyDay] > maxProfit) {
                    maxProfit = A[i][j] - A[i][buyDay];
                    sellDay = j;
                }
                // If the profit for this day is higher than the maximum profit we've seen so far, update the variables
                if(newMatrix[i][j] > maxProfit) {
                    maxProfit = newMatrix[i][j];
                    buyDay = j;
                    sellDay = j;
                }
            }
            // Print out the stock, buy day, and sell day.
            System.out.println(i + " " + buyDay + " " + sellDay);
        }
    }
}