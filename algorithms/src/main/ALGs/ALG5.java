// Maryam Alyounes

package ALGs;

public class ALG5 {
    public static int run_ALG5(int[][] copy, int k) {
        int maxProfit = 0;
        int m = copy.length;
        int n = copy[0].length;
        int [][][] newMatrix = new int[m][n][k+1];

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < m; j++) {
                newMatrix[j][i][0] = newMatrix[j][i-1][0];
                for(int f = 1; f <= k; f++) {
                    newMatrix[j][i][f] = newMatrix[j][i-1][f];
                    for(int g = 0; g < j; g++) {
                        int profit = copy[j][i] - copy[g][i];
                        newMatrix[j][i][f] = Math.max(newMatrix[j][i][f], newMatrix[g][i-1][f-1] + profit);
                    }
                }
            }
        }
        for(int p = 0; p <= k; p++) {
            maxProfit = Math.max(maxProfit, newMatrix[m-1][n-1][p]);
        }
        System.out.println(maxProfit);
        return maxProfit;
    }
}
