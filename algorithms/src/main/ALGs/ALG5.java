// Maryam Alyounes

package ALGs;
import java.util.*;

public class ALG5 {
    public static int run_ALG5(int[][] copy, int k) {
        int m = copy.length;
        int n = copy[0].length;

        int maxProfit = 0;
        int max = 0;
        int [][][] newMatrix = new int[m][n][k+1];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int s = 0; s <= k; s++) {
                    if(s == 0) {
                        newMatrix[i][j][s] = 0;
                    } 
                    else if(j == 0) {
                        newMatrix[i][j][s] = 0;
                    } 
                    else {
                        maxProfit = newMatrix[i][j-1][s];
                        for(int p = 0; p < j; p++) {
                            int profit = copy[i][j] - copy[i][p] + newMatrix[i][p][s-1];
                            maxProfit = Math.max(maxProfit, profit);
                        }
                        newMatrix[i][j][s] = maxProfit;
                    }
                }
            }
        }
        for(int i = 0; i <= k; i++) {
            max = Math.max(max, newMatrix[m-1][n-1][i]);
        }
        return max;
    }

    public static void task5() {
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

        int [][][] newMatrix = new int[m][n][Integer.parseInt(k)+1];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int s = 0; s <= Integer.parseInt(k); s++) {
                    if(s == 0) {
                        newMatrix[i][j][s] = 0;
                    } 
                    else if(j == 0) {
                        newMatrix[i][j][s] = 0;
                    } 
                    else {
                        int maxProfit = newMatrix[i][j-1][s];
                        int buyDay = -1;
                        for(int p = 0; p < j; p++) {
                            int profit = copy[i][j] - copy[i][p] + newMatrix[i][p][s-1];
                            if(profit > maxProfit) {
                                maxProfit = profit;
                                buyDay = p;
                            }
                        }
                        newMatrix[i][j][s] = maxProfit;
                        if(buyDay != -1) {
                            System.out.println((i+1) + " " + (buyDay+1) + " " + (j+1));
                        }
                    }
                }
            }
        }
    }
}
