// Maryam Alyounes

package ALGs;
import java.util.*;

public class ALG6 {
    public static int run_ALG6(int[][] A, int k) {
        int maxProfit = 0;
        int m = A.length;
        int n = A[0].length;
        int[][][] newMatrix = new int[m][n][k+1];

        // initialize memoization table with -1
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int s = 0; s <= k; s++) {
                    newMatrix[i][j][s] = -1;
                }
            }
        }
        
        // compute maximum profit for each (i, j, t) using memoization
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int s = 0; s <= k; s++) {
                    if(s == 0 || j == 0) {
                        newMatrix[i][j][s] = 0; // base case
                    }
                    else {
                        maxProfit = newMatrix[i][j-1][s]; // do nothing
                        for(int d = 0; d < i; d++) { // buy and sell
                            int profit = A[i][j] - A[d][j] + newMatrix[d][j-1][s-1];
                            maxProfit = Math.max(maxProfit, profit);
                        }
                        newMatrix[i][j][s] = maxProfit;
                    }
                }
            }
        }
        
        // find maximum profit
        for(int i = 0; i < m; i++) {
            for(int s = 1; s <= k; s++) {
                maxProfit = Math.max(maxProfit, newMatrix[i][n-1][s]);
            }
        }
        return maxProfit;
    }

    // Memoization algorithm implementation
    public static void task6() {
        Scanner scanner = new Scanner(System.in);
        String k = scanner.nextLine();
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

        int m = copy.length;
        int n = copy[0].length;

        int maxProfit = 0;
        int profit = 0;
        int[][][] newMatrix = new int[m][n][Integer.parseInt(k)+1];
        
        // Initialize memoization table with -1
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int s = 0; s <= Integer.parseInt(k); s++) {
                    newMatrix[i][j][s] = -1;
                }
            }
        }
        
        // compute the max profit for each i, j, s using memoization
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int s = 0; s <= Integer.parseInt(k); s++) {
                    if(s == 0 || j == 0) {
                        newMatrix[i][j][s] = 0; // base case
                    } 
                    else {
                        maxProfit = newMatrix[i][j-1][s]; // do nothing
                        for(int d = 0; d < i; d++) { // buy and sell
                            profit = copy[i][j] - copy[d][j] + newMatrix[d][j-1][s-1];
                            maxProfit = Math.max(maxProfit, profit);
                        }
                        newMatrix[i][j][s] = maxProfit;
                    } 
                }
            }
        }

        // find optimal transaction sequence by backtracking memoization table
        int i = m-1;
        int j = n-1;
        int s = Integer.parseInt(k);
        while(i >= 0 && j >= 0 && s > 0) {
            if(newMatrix[i][j][s] == newMatrix[i][j-1][s]) {
                j--; // do nothing
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
