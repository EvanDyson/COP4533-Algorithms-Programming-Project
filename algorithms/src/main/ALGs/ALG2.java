// Evan

package ALGs;

public class ALG2 {
    public static int run_ALG2(int[][] copy) {      
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
        
        System.out.print("|| Algorithm 2: ");       return maxProfit;
    }
}
