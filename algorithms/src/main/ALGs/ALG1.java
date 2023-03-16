// Evan

package ALGs;

public class ALG1 {
    public static int run_ALG1(int[][] copy) {
        // initiate a maxprofit for return
        int maxProfit = 0;

        // loop through all the stocks
        for(int i = 0; i < copy.length; i++) {

            // loop through all the days per stock
            for (int j = 0; j < copy[i].length; j++) {

                // loop through all the days after day J
                for (int k = j+1; k < copy[i].length; k++) {

                    // get the profit if sold at day k - day J
                    int profit = copy[i][k] - copy[i][j];

                    // compare profit to max profit and save the higher integer
                    maxProfit = Math.max(maxProfit, profit);
                }
            }
        }
        System.out.print("|| Algorithm 1: ");       return maxProfit;
    }
}