// Evan

package ALGs;

import java.util.Scanner;

/*
 For the purpose of clearity and cleanliness I have made two functions.
        The "run_ALG2" function is to show the time complexity of the algorithm. As well as cleanly showing off the code to easy readability.
        The "task2" function is for the purpose of part 4 of the assignment, in order to use the proper input and give out the correct output I have made a seperate function
            that will take in the correct input and then correctly display the buy day, sell day, and stock.
 */

public class ALG2 {
    public static int run_ALG2(int[][] copy) {      
        final long startTime = System.nanoTime();

        // initiate a maxprofit for return
        int maxProfit = 0;

        // loop through all the stocks
        for (int m = 0; m < copy.length; m++) {
            // initiate a maxprofitperstock and minprice to be reset per stock
            int maxProfitPerStock = 0;
            int minPrice = copy[m][0];

            // loop though all the days per stock
            for (int n = 1; n < copy[m].length; n++) {
                /*
                    if the current price is less than the current saved minPrice then replace min price
                    otherwise check to see if the current maxProfitPerStock is greater than 
                    the potential profit if sold at the current day. if it is replace maxProfitPerStock 
                */
                if (copy[m][n] < minPrice)
                    minPrice = copy[m][n];
                else {
                    int currentProfit = copy[m][n] - minPrice;
                    maxProfitPerStock = Math.max(currentProfit, maxProfitPerStock);
                }
            }
            // check if the current stock's max profit is larger than the overall max profit
            // if it is replace it, otherwise continue
            if (maxProfitPerStock > maxProfit)
                maxProfit = maxProfitPerStock;
        }
        final long endTime = System.nanoTime();
        long elapsedTimeMillis = (endTime - startTime);
        System.out.println("|| ALG 2 took " + elapsedTimeMillis + " nanoseconds\tusing m = " + copy.length + "\tn = " + copy[0].length);
        return maxProfit;
    }
    
    public static void task2() {
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
        
        int maxProfit = 0;
        int stock = 0, buyDay = 0, sellDay = 0, tempStock = 0, tempBuyDay = 0, tempSellDay = 0;

        for (int m = 0; m < copy.length; m++) {
            int maxProfitPerStock = 0;
            int minPrice = copy[m][0];
            for (int n = 1; n < copy[m].length; n++) {
                if (copy[m][n] < minPrice) {
                    minPrice = copy[m][n];
                    tempBuyDay = n;
                }
                else {
                    int currentProfit = copy[m][n] - minPrice;
                    if (currentProfit > maxProfitPerStock) {
                        maxProfitPerStock = currentProfit;
                        tempStock = m;
                        tempSellDay = n;
                    }
                }
            }
            if (maxProfitPerStock > maxProfit) {
                maxProfit = maxProfitPerStock;
                stock = tempStock;
                buyDay = tempBuyDay;
                sellDay = tempSellDay;
            }
        }
        System.out.println((stock+1) + " " + (buyDay+1) + " " + (sellDay+1));
    }
}

