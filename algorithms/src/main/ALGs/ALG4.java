// Evan

package ALGs;
import java.util.*;

public class ALG4 {
    public static int run_ALG4(int[][] copy, int k) {
        int[] profit = new int[k+1];
        int[] buyDay = new int[k+1];
        int[] sellDay = new int[k+1];
        int[] stock = new int[k+1];

        for (int t = 1; t <= k; t++) {
            int profit_ = 0;
            int tempProfit = 0;
            int buyDay_ = 0;
            int tempBuyDay = 0;
            int tempSellDay = 0;
            int tempStock = 0;

            for (int m = 0; m < copy.length; m++) {
                int minPrice = Integer.MAX_VALUE;

                for (int n = 0; n < copy[m].length; n++) {
                    if (profit[t] == 0 && profit[t-1] == 0) {
                        // profit is empty currently 
                        // loop thru until the highest is found then save it temporarily till the end of the loop of k
                        if (copy[m][n] <= minPrice) {
                            minPrice = copy[m][n];
                            buyDay_ = n;
                        }
                        else {
                            profit_ = copy[m][n] - minPrice;
                            if (profit_ > tempProfit) {
                                tempProfit = profit_;
                                tempBuyDay = buyDay_;
                                tempSellDay = n;
                                tempStock = m;
                            }
                        }
                    }
                    else {
                        // profit is not currently empty but previous is empty
                        // this means this is the next transaction
                        for (int f = 1; f <= t-1; f++) {
                            if (copy[m][n] <= minPrice && (((buyDay[f-1] != n) && (n >= sellDay[f-1])) || (n < buyDay[f-1]))) {
                                minPrice = copy[m][n];
                                buyDay_ = n;
                            }
                            else {
                                if (n <= sellDay[f-1] && n > buyDay[f-1]) {
                                    System.out.print("");
                                }
                                else {
                                    profit_ = copy[m][n] - minPrice;
                                    if (profit_ > tempProfit && profit_ < profit[f-1]) {
                                        tempProfit = profit_;
                                        tempBuyDay = buyDay_;
                                        tempSellDay = n;
                                        tempStock = m;
                                    }   
                                }
                            }
                        }
                    }
                }
            }
            profit[t] = tempProfit;
            buyDay[t] = tempBuyDay;
            sellDay[t] = tempSellDay;
            stock[t] = tempStock;
        }
        int total = 0;
        int tempTotal = 0;
        
        for (int x = 1; x <= k; x++) {
            tempTotal = Math.max(tempTotal, altMaxProfit(copy, k, buyDay[x], sellDay[x], stock[x]));
            //System.out.println();
            //System.out.println(profit[x]);
            total += profit[x];
        }
        total = Math.max(total, tempTotal);
        //System.out.println(total);
        return total;
    }

    public static int run_ALG4V2(int[][] copy, int k) {
        int[] profit = new int[k+1];
        int[] buyDay = new int[k+1];
        int[] sellDay = new int[k+1];
        int[] stock = new int[k+1];

        for (int t = 1; t <= k; t++) {
            int profit_ = 0;
            int tempProfit = 0;
            int buyDay_ = 0;
            int tempBuyDay = 0;
            int tempSellDay = 0;
            int tempStock = 0;

            for (int m = 0; m < copy.length; m++) {
                int minPrice = Integer.MAX_VALUE;

                for (int n = 0; n < copy[m].length; n++) {
                    for (int f = 1; f <= t-1; f++) {
                        if (copy[m][n] <= minPrice && ( ( (buyDay[f-1] != n) && (n >= sellDay[f-1]) ) || (n < buyDay[f-1]) ) ) {
                            minPrice = copy[m][n];
                            buyDay_ = n;
                        }
                        else {
                            if (n <= sellDay[f-1] && n > buyDay[f-1]) {
                                System.out.print("");
                            }
                            else {
                                profit_ = copy[m][n] - minPrice;
                                if (profit_ > tempProfit && profit_ < profit[f-1]) {
                                    tempProfit = profit_;
                                    tempBuyDay = buyDay_;
                                    tempSellDay = n;
                                    tempStock = m;
                                }   
                            }
                        }
                    }                
                }
            }
            profit[t] = tempProfit;
            buyDay[t] = tempBuyDay;
            sellDay[t] = tempSellDay;
            stock[t] = tempStock;
        }
        int total = 0;
        int tempTotal = 0;
        
        for (int x = 1; x <= k; x++) {
            //tempTotal = Math.max(tempTotal, altMaxProfit(copy, k, buyDay[x], sellDay[x], stock[x]));
            //System.out.println();
            //System.out.println(profit[x]);
            total += profit[x];
        }
        total = Math.max(total, tempTotal);
        //System.out.println(total);
        return total;
    }

    public static int altMaxProfit(int[][]copy, int k, int Xbuy, int Xsell, int Xstock)
    {
        int[] profit = new int[k+1];
        int[] buyDay = new int[k+1];
        int[] sellDay = new int[k+1];
        int[] stock = new int[k+1];

        for (int t = 1; t <= k; t++) {
            int profit_ = 0;
            int tempProfit = 0;
            int buyDay_ = 0;
            int tempBuyDay = 0;
            int tempSellDay = 0;
            int tempStock = 0;

            for (int m = 0; m < copy.length; m++) {
                int minPrice = Integer.MAX_VALUE;

                for (int n = 0; n < copy[m].length; n++) {
                    if (profit[t] == 0 && profit[t-1] == 0) {
                        // profit is empty currently 
                        // loop thru until the highest is found then save it temporarily till the end of the loop of k
                        if (copy[m][n] <= minPrice) {
                            minPrice = copy[m][n];
                            buyDay_ = n;
                        }
                        else {
                            if (buyDay_ == Xbuy && n == Xsell && m == Xstock)
                                System.out.print("");
                            else {
                                profit_ = copy[m][n] - minPrice;
                                if (profit_ > tempProfit) {
                                    tempProfit = profit_;
                                    tempBuyDay = buyDay_;
                                    tempSellDay = n;
                                    tempStock = m;
                                }
                            }
                        }
                    }
                    else {
                        // profit is not currently empty but previous is empty
                        // this means this is the next transaction
                        if (copy[m][n] <= minPrice && (((buyDay[t-1] != n) && (n >= sellDay[t-1])) || (n < buyDay[t-1]))) {
                            minPrice = copy[m][n];
                            buyDay_ = n;
                        }
                        else {
                            if (n <= sellDay[t-1] && n > buyDay[t-1])
                                System.out.print("");
                            else if (buyDay_ == Xbuy && n == Xsell && m == Xstock)
                                System.out.print("");
                            else {
                                profit_ = copy[m][n] - minPrice;
                                if (profit_ > tempProfit && profit_ < profit[t-1]) {
                                    tempProfit = profit_;
                                    tempBuyDay = buyDay_;
                                    tempSellDay = n;
                                    tempStock = m;
                                }   
                            }
                        }
                    }
                }
            }
            profit[t] = tempProfit;
            buyDay[t] = tempBuyDay;
            sellDay[t] = tempSellDay;
            stock[t] = tempStock;
        }
        int total = 0;
        
        for (int x = 1; x <= k; x++) {
            //System.out.println(profit[x]);
            total += profit[x];
        }
        return total;
    }

    public static int altMaxProfitV2(int[][]copy, int k, int Xbuy, int Xsell, int Xstock)
    {
        int[] profit = new int[k+1];
        int[] buyDay = new int[k+1];
        int[] sellDay = new int[k+1];
        int[] stock = new int[k+1];

        for (int t = 1; t <= k; t++) {
            int profit_ = 0;
            int tempProfit = 0;
            int buyDay_ = 0;
            int tempBuyDay = 0;
            int tempSellDay = 0;
            int tempStock = 0;

            for (int m = 0; m < copy.length; m++) {
                int minPrice = Integer.MAX_VALUE;

                for (int n = 0; n < copy[m].length; n++) {
                    for (int f = 1; f <= t-1; f++) {
                        if (copy[m][n] <= minPrice && ( ( (buyDay[f-1] != n) && (n >= sellDay[f-1]) ) || (n < buyDay[f-1]) ) ) {
                            minPrice = copy[m][n];
                            buyDay_ = n;
                        }
                        else {
                            if (n <= sellDay[f-1] && n > buyDay[f-1]) {
                                System.out.print("");
                            }
                            else {
                                profit_ = copy[m][n] - minPrice;
                                if (profit_ > tempProfit && profit_ < profit[f-1]) {
                                    tempProfit = profit_;
                                    tempBuyDay = buyDay_;
                                    tempSellDay = n;
                                    tempStock = m;
                                }   
                            }
                        }
                    }      
                }
            }
            profit[t] = tempProfit;
            buyDay[t] = tempBuyDay;
            sellDay[t] = tempSellDay;
            stock[t] = tempStock;
        }
        int total = 0;
        
        for (int x = 1; x <= k; x++) {
            //System.out.println(profit[x]);
            total += profit[x];
        }
        return total;
    }
}