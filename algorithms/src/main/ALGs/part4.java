package ALGs;
import java.util.*;

public class part4 {
    public static void part4() {
        int[][] copy = createMatrix();
        run_problem1(copy);
    }

    public static int[][] createMatrix() {
        Scanner scanner= new Scanner(System.in);
        String lineOne = scanner.nextLine();
        String[] lineOneParts = lineOne.split("\\s+");
        int[][] newMatrix = new int[Integer.valueOf(lineOneParts[0])][Integer.valueOf(lineOneParts[1])];
        System.out.println(lineOneParts[0]);
        System.out.println(lineOneParts[1]);
        for(int i = 0; i < Integer.valueOf(lineOneParts[0]); i++) {
            String temp = scanner.nextLine();
            String[] tempParts = temp.split("\\s+");
            for (int j = 0; j < Integer.valueOf(lineOneParts[1]); j++) {
                newMatrix[i][j] = Integer.valueOf(tempParts[j]);
                System.out.println(tempParts[j]);
            }
        }
        return newMatrix;
    }

    public static void run_problem1(int[][] copy) {
        int maxProfit = 0;

        int stock = 0, buyDay = 0, sellDay = 0;

        // loop through all the stocks
        for(int i = 0; i < copy.length; i++) {

            // loop through all the days per stock
            for (int j = 0; j < copy[i].length; j++) {

                // loop through all the days after day J
                for (int k = j+1; k < copy[i].length; k++) {

                    // get the profit if sold at day k - day J
                    int profit = copy[i][k] - copy[i][j];

                    // compare profit to max profit and save the higher integer
                    if (profit > maxProfit)
                    {
                        maxProfit = profit;
                        stock = i;
                        buyDay = j;
                        sellDay = k;
                    }
                }
            }
        }
        System.out.println("|| Part 4: " + stock + " " + buyDay + " " + sellDay);
    }
}