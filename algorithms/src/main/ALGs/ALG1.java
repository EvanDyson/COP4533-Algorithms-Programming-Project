// Evan

package ALGs;

public class ALG1 {
    public static int run_ALG1(int[][] copy) {
        int maxProfit = 0;
        
        for(int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                for (int k = j+1; k < copy[i].length; k++) {
                    int profit = copy[i][k] - copy[i][j];
                    maxProfit = Math.max(maxProfit, profit);
                }
            }
        }
        System.out.print("|| Algorithm 1: ");       return maxProfit;
    }
}