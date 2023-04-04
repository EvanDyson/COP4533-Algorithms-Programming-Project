// Maryam Alyounes

package ALGs;
import java.util.Arrays;
import java.util.Scanner;

public class ALG3 {
    private int[][] myMatrix;

    public static int run_ALG3(int[][] copy) {      
        // initiate a maxprofit for return
        int maxProfit = 0;

        // loop through all the stocks
        for (int i = 0; i < copy.length; i++) {
            // initiate a maxprofitperstock and minprice to be reset per stock
            int maxProfitPerStock = 0;
            int minPrice = copy[i][0];

            // loop though all the days per stock
            for (int j = 1; j < copy[i].length; j++) {
                // if the current price is less than the current saved minPrice then replace min price
                // otherwise check to see if the current maxProfitPerStock is greater than 
                // the potential profit if sold at the current day. if it is replace maxProfitPerStock
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
        
        return maxProfit;
    }
    
    // Memoization algorithm implementation
    public int task3a(int[][] price) {
        Scanner scanner= new Scanner(System.in);
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
        
        int m = price.length;
        int n = price[0].length;

        myMatrix = new int[m][n];
        for(int i = 0; i < m; i++) {
            Arrays.fill(myMatrix[i], -1);
        }
        
        int maxProfit = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int myProfit = computedMaxProfit(price, i, j);
                if(myProfit > maxProfit) {
                    maxProfit = myProfit;
                }
            }
        }
        return maxProfit;
    }

    private int computedMaxProfit(int[][] price, int i, int j) {
        if(i >= price.length || j >= price[0].length) {
            return 0;
        }
        if(myMatrix[i][j] != -1) {
            return myMatrix[i][j];
        }
        int maxProfit = 0;
        int buyPrice = price[i][j];
        for(int f = j+1; f < price[0].length; f++) {
            int sellPrice = price[i][f];
            int profit = sellPrice - buyPrice;
            if(profit > 0) {
                int nextProfit = computedMaxProfit(price, i+1, f+1);
                maxProfit = Math.max(maxProfit, profit + nextProfit);
            }
        }
        myMatrix[i][j] = maxProfit;
        return maxProfit;
    }

    // Iterative BottomUp algorithm implementation
    public static int task3b(int[][] price) {
        int m = price.length;
        int n = price[0].length;
        int[][] newMatrix = new int[m][n];

        for(int k = 1; k < n; k++) {
            newMatrix[0][k] = price[0][k] - price[0][0];
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                int currentMaxProfit = 0;
                int currentMinProfit = price[i][0];
                for(int f = 1; f <= j; f++) {
                    int profit = price[i][f] - currentMinProfit;
                    currentMaxProfit = Math.max(currentMaxProfit, profit);
                    currentMinProfit = Math.min(currentMinProfit, price[i][f]); 
                }
                newMatrix[i][j] = Math.max(newMatrix[i][j-1], currentMaxProfit);
            }
        }
        return newMatrix[m-1][n-1];
    }
}