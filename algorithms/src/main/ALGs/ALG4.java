// Evan

package ALGs;
import java.util.*;

public class ALG4 {
    public static int run_ALG4(int[][] copy, int k) {
        int maxProfit = 0;
        int[][] profit = new int[copy.length][copy[0].length];
        int[][] buyDay = new int[copy.length][copy[0].length];
        int[][] sellDay = new int[copy.length][copy[0].length];
        int[][] stock = new int[copy.length][copy[0].length];

        if (k == 0) {
            maxProfit = 0;
            return maxProfit;
        }
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
            return maxProfit;
        } 
        else {
            /*
             need to do checks to make sure that the new stock has a different sell date
             maybe check for same sale date to overwrite old profit.
             need to check that buy date is not between other saved profits buy and sell
             */
            for (int i = 0; i < copy.length; i++) {
                int minPrice = copy[i][0];
                int minDay = 0;
                for (int j = 0; j < copy[0].length; j++) {
                    sellDay[i][j] = j + 1;
                    stock[i][j] = i + 1;
                    if (copy[i][j] <= minPrice) {
                        minPrice = copy[i][j];
                        minDay = j + 1;
                        buyDay[i][j] = j + 1;
                    } else {
                        profit[i][j] = copy[i][j] - minPrice;
                        buyDay[i][j] = minDay;
                    }
                }
            }
            { // test printing
                System.out.println();
                System.out.println("k = " + k);
                System.out.println();
                for (int y = 0; y < copy.length; y++) {
                    System.out.println("stock: " + (y + 1));
                    for (int z = 0; z < copy[0].length; z++) {
                        if (z == copy[0].length - 1) {
                            System.out.print("profit:" + profit[y][z]);
                            System.out.print(" buyday:" + buyDay[y][z]);
                            System.out.println(" sellday:" + sellDay[y][z]);
                        } else {
                            System.out.print("profit:" + profit[y][z]);
                            System.out.print(" buyday:" + buyDay[y][z]);
                            System.out.print(" sellday:" + sellDay[y][z] + ",\t");
                        }
                    }
                    System.out.println();
                }
            }
        }

        int[] maxPrice = new int[k];
        int[] maxBuyDay = new int[k];
        int[] maxSellDay = new int[k];
        int[] maxStock = new int[k];
            
        for (int i = 0; i < k; i++) {
            for (int n = 1; n < copy[0].length; n++) {
                for (int m = 0; m < copy.length; m++) {
                    //System.out.println(profit[m][n]);
                    if (maxPrice[i] < profit[m][n]) {
                        if (maxStock[i] == 0) {
                            maxStock[i] = stock[m][n];
                            maxPrice[i] = profit[m][n];
                            maxBuyDay[i] = buyDay[m][n];
                            maxSellDay[i] = sellDay[m][n];
                            System.out.println(i + " " + maxPrice[i]);
                        }
                        else {
                            if (maxStock[i] == stock[m][n]) {
                                // looking at the same stock purchase skip this... already saved
                                if (maxBuyDay[i] == buyDay[m][n] && maxSellDay[i] == sellDay[m][n])
                                    break;
                                // looking at the same stock with a higher profit that has a different sell date but the same purchase date resave this
                                else if (maxBuyDay[i] == buyDay[m][n] && maxSellDay[i] != sellDay[m][n]) {

                                }
                            }
                        }
                    }
                }
                    //System.out.println(i + " " + maxPrice[i]);
            }
                //System.out.println(i + " " + maxPrice[i]);
        }
            //System.out.println(i + " " + maxPrice[i]);
        
        System.out.println();
        for (int x : maxPrice) {
            System.out.println(x);
            maxProfit += x;
        }
        return maxProfit;
    }
}
