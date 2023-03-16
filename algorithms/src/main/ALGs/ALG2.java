// Evan

package ALGs;

public class ALG2 {
    public static int run_ALG2(int[][] copy) {
        // case 1 where one stock and one day, return 0 profit.
        // case 2 where one stock and multiple days, return the highest and lowest price day given that the lowest is before the highest
        // case 3 where more than 1 stock, return the highest different between the highest and lowest price day given that the lowest is before the highest on any stock
        
        int maxProfit = 0;

        for (int i = 0; i < copy.length; i++) {
            int maxProfitPerStock = 0;
            int minPrice = copy[i][0];

            for (int j = 0; j < copy[i].length; j++) {
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
