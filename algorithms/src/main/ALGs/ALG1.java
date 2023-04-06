// Evan

package ALGs;
import java.util.*;

/*
 For the purpose of clearity and cleanliness I have made two functions.
        The "run_ALG1" function is to show the time complexity of the algorithm. As well as cleanly showing off the code to easy readability.
        The "task1" function is for the purpose of part 4 of the assignment, in order to use the proper input and give out the correct output I have made a seperate function
            that will take in the correct input and then correctly display the buy day, sell day, and stock.
 */

public class ALG1 {
    public static int run_ALG1(int[][] copy) {
        // initiate a maxprofit for return
        int maxProfit = 0;
        // loop through all the stocks
        for (int m = 0; m < copy.length; m++) {
            // loop through all the days per stock
            for (int n = 0; n < copy[m].length; n++) {
                // loop through all the days after day n
                for (int i = n + 1; i < copy[m].length; i++) {
                    // get the profit when sold at day i - day n
                    int profit = copy[m][i] - copy[m][n];
                    // compare profit to max profit and save the higher value
                    maxProfit = Math.max(maxProfit, profit);
        }}}
        return maxProfit;
    }
    
    public static void task1() {
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
        int stock = 0, buyDay = 0, sellDay = 0;
        for (int m = 0; m < copy.length; m++) {
            for (int n = 0; n < copy[m].length; n++) {
                for (int i = n + 1; i < copy[m].length; i++) {
                    int profit = copy[m][i] - copy[m][n];
                    if (profit > maxProfit)
                    {
                        maxProfit = profit;
                        stock = i;
                        buyDay = n;
                        sellDay = i;
        }}}}
        System.out.println((stock+1) + " " + (buyDay+1) + " " + (sellDay+1));
    }
}