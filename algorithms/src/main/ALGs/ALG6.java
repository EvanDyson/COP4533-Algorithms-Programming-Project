// Maryam Alyounes

package ALGs;
import java.util.*;

public class ALG6 {
    public static int run_ALG6(int[][] A, int k) {
        final long startTime = System.nanoTime();

        int m = A.length;
        int n = A[0].length;

        int[][][] newMatrix = new int[k+1][n][2];
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(newMatrix[i][j], -1);
            }
        }

        final long endTime = System.nanoTime();
        long elapsedTimeMillis = (endTime - startTime);
        System.out.println("|| ALG 6 took " + elapsedTimeMillis + " nanoseconds\tusing m = " + A.length + "\tn = " + A[0].length + "\tk = " + k);
        // return the maximum profit and transaction sequence as an array

        return helper(A, k, n-1, 0, newMatrix);
    }
    
    private static int helper(int[][] A, int k, int j, int status, int[][][] newMatrix) {
        if (j < 0 || k == 0) {
            return 0;
        }
        if (newMatrix[k][j][status] != -1) {
            return newMatrix[k][j][status];
        }
        int res = helper(A, k, j-1, status, newMatrix);
        if (status == 0) {
            res = Math.max(res, helper(A, k, j-1, 1, newMatrix) + A[k-1][j]);
        } else {
            res = Math.max(res, helper(A, k-1, j-1, 0, newMatrix) - A[k-1][j]);
        }
        newMatrix[k][j][status] = res;
        return res;
    }

    // Memoization algorithm implementation
    public static void task6() {
        Scanner scanner = new Scanner(System.in);
        String tempK = scanner.nextLine();
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
        int k = Integer.valueOf(tempK);

        int m = copy.length;
        int n = copy[0].length;

        int maxProfit = 0;
        int profit = 0;
        int[][][] newMatrix = new int[m][n][k+1];
        
        // Initialize memoization table with -1
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int s = 0; s <= k; s++) {
                    newMatrix[i][j][s] = -1;
                }
            }
        }
        
        // compute the max profit for each i, j, s using memoization
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int s = 0; s <= k; s++) {
                    if(s == 0 || j == 0) {
                        // base case
                        newMatrix[i][j][s] = 0; 
                    } 
                    else {
                        // do nothing
                        maxProfit = newMatrix[i][j-1][s]; 
                        // buy and sell
                        for(int d = 0; d < i; d++) { 
                            profit = copy[i][j] - copy[d][j] + newMatrix[d][j-1][s-1];
                            maxProfit = Math.max(maxProfit, profit);
                        }
                        newMatrix[i][j][s] = maxProfit;
                    }
                    // update max profit
                    maxProfit = Math.max(maxProfit, newMatrix[i][j][s]);
                }
            }
        }

        // find optimal transaction sequence by backtracking memoization table
        int i = m-1;
        int j = n-1;
        int s = k;
        while(i >= 0 && j >= 0 && s > 0) {
            if(newMatrix[i][j][s] == newMatrix[i][j-1][s]) {
                // do nothing
                j--; 
            } 
            else {
                int max = newMatrix[i][j][s];
                for(int d = 0; d < i; d++) { // find buy and sell indices
                    int p = copy[i][j] - copy[d][j] + newMatrix[d][j-1][s-1];
                    if(max == profit) {
                        System.out.println(i + " " + j + " " + d);
                        i = p;
                        s--;
                        break;
                    }
                }
            }
        }
    }
}
