// Maryam Alyounes

package ALGs;

public class ALG6 {
    public static int run_ALG6(int[][] copy, int k) {
        int maxProfit = 0;
        int m = copy.length;
        int n = copy[0].length;
        int[][][] newMatrix = new int[m][n][k+1];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int s = 0; s <= k; s++) {
                    newMatrix[i][j][s] = Integer.MIN_VALUE;
                }
            }
        }
        
        for(int s = 0; s <= k; s++) {
            for(int j = 0; j < n; j++) {
                newMatrix[0][j][s] = 0;
            }
        }

        for(int i = 1; i < m; i++) {
            for(int s = 1; s <= k; s++) {
                int maxDiff = -copy[i][0];
                for(int j = 1; j < n; j++) {
                    newMatrix[i][j][s] = Math.max(newMatrix[i][j-1][s], maxDiff + copy[i][j]);
                    maxDiff = Math.max(maxDiff, newMatrix[i-1][j][s-1] - copy[i][j]);
                }
            }
        }
        for(int s = 0; s <= k; s++) {
            maxProfit = Math.max(maxProfit, newMatrix[m-1][n-1][s]);
        }
        
        return maxProfit;
    }

    // Memoization algorithm implementation
    public static void task6(int[][] copy, int k) {
        int maxProfit = 0;
        int m = copy.length;
        int n = copy[0].length;
        int[][] newMatrix = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                newMatrix[i][j] = 0;
            }
        }

        for(int s = 1; s <= k; s++) {
            int maxDiff = -copy[0][0];
            for(int j = 1; j < n; j++) {
                for(int i = 1; i < m; i++) {
                    newMatrix[i][j] = Math.max(newMatrix[i][j-1], maxDiff + copy[i][j]);
                    maxDiff = Math.max(maxDiff, newMatrix[i-1][j] - copy[i][j]);
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                maxProfit = Math.max(maxProfit, newMatrix[i][j]);
            }
        }
        
        System.out.println(maxProfit);
    }
}
