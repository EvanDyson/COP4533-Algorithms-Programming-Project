// Evan

package ALGs;
import java.util.*;

import javax.xml.bind.JAXB;

public class ALG4 {
    public static int run_ALG4(int[][] copy, int k) {
        int maxProfit = 0;
        System.out.println("k = " + k);

        if (k == 0)
            maxProfit = 0;
        else if (k == 1) {
            for (int i = 0; i < copy.length; i++) {
                int maxProfitPerStock = 0;
                int minPrice = copy[i][0];
                for (int j = 1; j < copy[i].length; j++) {
                    if (copy[i][j] < minPrice)
                        minPrice = copy[i][j];
                    else {
                        int currentProfit = copy[i][j] - minPrice;
                        maxProfitPerStock = Math.max(currentProfit, maxProfitPerStock);
                    }
                }
                if (maxProfitPerStock > maxProfit)
                    maxProfit = maxProfitPerStock;
            }
        } else {
            int[][][] profit = new int[k][copy.length][copy[0].length];
            int[][][] buyDay = new int[k][copy.length][copy[0].length];
            int[][][] sellDay = new int[k][copy.length][copy[0].length];

            boolean holding = false;

            for (int t = 0; t < k; t++) {
                for (int i = 0; i < copy.length; i++) {
                    int maxProfitPerStock = 0;
                    int minPrice = copy[i][0];
                    int minDay = 0;
                    for (int j = 1; j < copy[0].length; j++) {
                        sellDay[t][i][j] = j;
                        if (copy[i][j] <= minPrice) {
                            minPrice = copy[i][j];
                            minDay = j;
                            buyDay[t][i][j] = j;
                        }
                        else {
                            profit[t][i][j] = copy[i][j] - minPrice;
                            buyDay[t][i][j] = minDay;
                            //maxProfitPerStock = Math.max(profit[t][i][j], maxProfitPerStock);
                        }
                    }
                }

            }

            //for (int x = 0; x < k; x++)
            //maxProfit += profit;
            for (int x = 0; x < k; x++) {
                for (int y = 0; y < copy.length; y++) {
                    for (int z = 0; z < copy[0].length; z++) {
                        if (z == copy[0].length - 1) {
                            System.out.print("profit:" + profit[x][y][z]);
                            System.out.print(" buyday:" + buyDay[x][y][z]);
                            System.out.println(" sellday:" + sellDay[x][y][z]);
                        }
                        else {
                            System.out.print("profit:" + profit[x][y][z]);
                            System.out.print(" buyday:" + buyDay[x][y][z]);
                            System.out.print(" sellday:" + sellDay[x][y][z] + ",\t");
                        }
                    }
                }
                System.out.println();
            }
        }
        
        return maxProfit;
    }
}
