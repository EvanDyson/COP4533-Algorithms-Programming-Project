// Maryam Alyounes

package ALGs;
import java.util.Scanner;

public class ALG3 {

    public static int run_ALG3(int[][] copy) {
        int m = copy.length;
        int n = copy[0].length;
        int maxProfit = 0;
        int buyPrice = 0;
        int sellPrice = 0;
        int profit = 0;

        for(int i = 0; i < m; i++) {
            buyPrice = copy[i][0];
            for(int j = 0; j < n; j++) {
                sellPrice = copy[i][j];
                profit = sellPrice - buyPrice;
                if(profit > maxProfit) {
                    maxProfit = profit;
                }
                if(buyPrice > sellPrice) {
                    buyPrice = sellPrice;
                }
            }
        }
        return maxProfit;
    }
    
    // Memoization algorithm implementation
    public static void task3a() {
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

        int m = copy.length;
        int n = copy[0].length;

        int[][] myMatrix = new int[m][n];
        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(j == 0) {
                    myMatrix[i][j] = copy[i][j];
                }
                else {
                    myMatrix[i][j] = Math.max(myMatrix[i][j-1], copy[i][j] - copy[i][buyDay]);
                }
                if(copy[i][j] - copy[i][buyDay] > maxProfit) {
                    maxProfit = copy[i][j] - copy[i][buyDay];
                    sellDay = j;
                }
                if (myMatrix[i][j] > maxProfit) {
                    maxProfit = myMatrix[i][j];
                    buyDay = j;
                    sellDay = j;
                }
            }
            System.out.println(i + " " + buyDay + " " + sellDay);
        }
    }

    // Iterative BottomUp algorithm implementation
    public static void task3b() {
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

        int m = copy.length;
        int n = copy[0].length;

        int[][] newMatrix = new int[m][n];
        int maxProfit = 0;
        int buyDay = 0;
        int sellDay = 0;    

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (j == 0) {
                    newMatrix[i][j] = copy[i][j];
                }
                else {
                    newMatrix[i][j] = Math.max(newMatrix[i][j - 1], copy[i][j] - copy[i][buyDay]);
                }
                if(copy[i][j] - copy[i][buyDay] > maxProfit) {
                    maxProfit = copy[i][j] - copy[i][buyDay];
                    sellDay = j;
                }
                if(newMatrix[i][j] > maxProfit) {
                    maxProfit = newMatrix[i][j];
                    buyDay = j;
                    sellDay = j;
                }
            }
            System.out.println(i + " " + buyDay + " " + sellDay);
        }
    }
}