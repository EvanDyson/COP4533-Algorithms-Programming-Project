// Evan

package ALGs;
import java.util.*;

public class ALG4 {
    public static int run_ALG4(int[][] copy, int k) {
        int[] profit = new int[k];
        int[] buyDay = new int[k];
        int[] sellDay = new int[k];
        int[] stock = new int[k];
        boolean existingTrans = false;

        for (int t = 0; t < k; t++) {
            int tempProfit = -1;
            int tempBuyDay = -1;
            int tempSellDay = -1;
            int tempStock = -1;

            for (int m = 0; m < copy.length; m++) {
                int minPrice = Integer.MAX_VALUE;
                int buyDay_ = -1;
                int prevMinPrice = Integer.MAX_VALUE;
                int prevBuyDay = -1;

                for (int n = 0; n < copy[m].length; n++) {
                    if (t == 0) {
                        // first transaction grab the highest transaction.
                        // loop thru until the highest is found then save it temporarily till the end of the loop of t
                        if (copy[m][n] <= minPrice) {
                            minPrice = copy[m][n];
                            buyDay_ = n;
                        }
                        else {
                            int currentTransaction = copy[m][n] - minPrice;
                            if (currentTransaction > tempProfit) {
                                tempProfit = currentTransaction;
                                tempBuyDay = buyDay_;
                                tempSellDay = n;
                                tempStock = m;
                            }
                        }
                    }
                    else {
                        // the first transaction has happened. 
                        // grab all following transactions compared to all previous transactions
                        for (int f = 1; f <= t; f++) {
                            // cycling through all previous transactions
                            if (existingTrans == true)
                                break;
                            // check if current price is less than saved price
                            else if (copy[m][n] <= minPrice) {
                                // check that the buy date isnt between other transcation dates.
                                if (n >= buyDay[f - 1] && n < sellDay[f - 1]) {
                                    existingTrans = true;
                                    minPrice = prevMinPrice;
                                    buyDay_ = prevBuyDay;
                                    break;
                                } 
                                else {
                                    prevMinPrice = minPrice;
                                    prevBuyDay = buyDay_;
                                    minPrice = copy[m][n];
                                    buyDay_ = n;
                                }
                            }
                            // if price is not smaller then try to sell it
                            // if the current sell day is between a previous transaction sell date break out
                            else if (n > buyDay[f - 1] && n <= sellDay[f - 1]) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            }
                            // do a check to make sure you dont save the same transaction
                            else if (n == sellDay[f - 1] && buyDay_ == buyDay[f - 1] && minPrice == profit[f - 1]) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            } else {
                                int currentTransaction = copy[m][n] - minPrice;
                                // do a check that the new transaction is bigger than the current saved temp profit
                                // do a check that the new transaction is smaller than the previous transactions
                                if (currentTransaction > tempProfit && currentTransaction <= profit[f - 1]) {
                                    tempProfit = currentTransaction;
                                    tempBuyDay = buyDay_;
                                    tempSellDay = n;
                                    tempStock = m;
                                }
                            }
                        }
                    }
                    existingTrans = false;
                }                
            }
        
            profit[t] = tempProfit;
            buyDay[t] = tempBuyDay;
            sellDay[t] = tempSellDay;
            stock[t] = tempStock;
        }
        int total = 0;
        int tempTotal = 0;
        System.out.println();
        for (int x = 0; x < k; x++) {
            tempTotal = Math.max(tempTotal, altMaxProfit(copy, k, buyDay[x], sellDay[x], stock[x]));
            total += profit[x];
        }
        total = Math.max(total, tempTotal);
        return total;
    }

    public static int altMaxProfit(int[][]copy, int k, int Xbuy, int Xsell, int Xstock)
    {
        int[] profit = new int[k];
        int[] buyDay = new int[k];
        int[] sellDay = new int[k];
        int[] stock = new int[k];
        boolean existingTrans = false;

        for (int t = 0; t < k; t++) {
            int tempProfit = -1;
            int tempBuyDay = -1;
            int tempSellDay = -1;
            int tempStock = -1;

            for (int m = 0; m < copy.length; m++) {
                int minPrice = Integer.MAX_VALUE;
                int buyDay_ = -1;
                int prevMinPrice = Integer.MAX_VALUE;
                int prevBuyDay = -1;

                for (int n = 0; n < copy[m].length; n++) {
                    if (t == 0) {
                        // first transaction grab the highest transaction.
                        // loop thru until the highest is found then save it temporarily till the end of the loop of t
                        if (copy[m][n] <= minPrice) {
                            minPrice = copy[m][n];
                            buyDay_ = n;
                        } else {
                            if (buyDay_ == Xbuy && n == Xsell && m == Xstock)
                                break;
                            else {
                                int currentTransaction = copy[m][n] - minPrice;
                                if (currentTransaction > tempProfit) {
                                    tempProfit = currentTransaction;
                                    tempBuyDay = buyDay_;
                                    tempSellDay = n;
                                    tempStock = m;
                                }
                            }
                        }
                    } else {
                        // the first transaction has happened. 
                        // grab all following transactions compared to all previous transactions
                        for (int f = 1; f <= t; f++) {
                            // cycling through all previous transactions
                            // check if current price is less than saved price
                            if (existingTrans == true)
                                break;
                            else if (copy[m][n] <= minPrice) {
                                // check that the buy date isnt between other transcation dates.
                                if (n >= buyDay[f - 1] && n < sellDay[f - 1]) {
                                    existingTrans = true;
                                    minPrice = prevMinPrice;
                                    buyDay_ = prevBuyDay;
                                    break;
                                } else {
                                    prevMinPrice = minPrice;
                                    prevBuyDay = buyDay_;
                                    minPrice = copy[m][n];
                                    buyDay_ = n;
                                }
                            }
                            // do a check to make sure the sent transaction is not saved into profits
                            else if (buyDay_ == Xbuy && n == Xsell && m == Xstock) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            }
                            // if price is not smaller then try to sell it
                            // if the current sell day is between a previous transaction sell date break out
                            else if (n > buyDay[f - 1] && n <= sellDay[f - 1]) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            }
                            // do a check to make sure you dont save the same transaction
                            else if (n == sellDay[f - 1] && buyDay_ == buyDay[f - 1] && minPrice == profit[f - 1]) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            } else {
                                int currentTransaction = copy[m][n] - minPrice;
                                // do a check that the new transaction is bigger than the current saved temp profit
                                // do a check that the new transaction is smaller than the previous transactions
                                if (currentTransaction > tempProfit && currentTransaction <= profit[f - 1]) {
                                    tempProfit = currentTransaction;
                                    tempBuyDay = buyDay_;
                                    tempSellDay = n;
                                    tempStock = m;
                                }
                            }
                        }
                    }
                    existingTrans = false;
                }
            }
            profit[t] = tempProfit;
            buyDay[t] = tempBuyDay;
            sellDay[t] = tempSellDay;
            stock[t] = tempStock;
        }
        int total = 0;

        for (int x = 0; x < k; x++) {
            //System.out.println(profit[x]);
            //System.out.println("sub-transaction #" + (x+1) + ": " + profit[x] + "\tBuy Day: " + buyDay[x] + "\tSell Day: " + sellDay[x] + "\tStock: " + stock[x]);
            total += profit[x];
        }
        return total;
    }
    
    public static void task4() {
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
        int[] profit = new int[k];
        int[] buyDay = new int[k];
        int[] sellDay = new int[k];
        int[] stock = new int[k];
        boolean existingTrans = false;

        for (int t = 0; t < k; t++) {
            int tempProfit = -1;
            int tempBuyDay = -1;
            int tempSellDay = -1;
            int tempStock = -1;

            for (int m = 0; m < copy.length; m++) {
                int minPrice = Integer.MAX_VALUE;
                int buyDay_ = -1;
                int prevMinPrice = Integer.MAX_VALUE;
                int prevBuyDay = -1;

                for (int n = 0; n < copy[m].length; n++) {
                    if (t == 0) {
                        // first transaction grab the highest transaction.
                        // loop thru until the highest is found then save it temporarily till the end of the loop of t
                        if (copy[m][n] <= minPrice) {
                            minPrice = copy[m][n];
                            buyDay_ = n;
                        }
                        else {
                            int currentTransaction = copy[m][n] - minPrice;
                            if (currentTransaction > tempProfit) {
                                tempProfit = currentTransaction;
                                tempBuyDay = buyDay_;
                                tempSellDay = n;
                                tempStock = m;
                            }
                        }
                    }
                    else {
                        // the first transaction has happened. 
                        // grab all following transactions compared to all previous transactions
                        for (int f = 1; f <= t; f++) {
                            // cycling through all previous transactions
                            if (existingTrans == true)
                                break;
                            // check if current price is less than saved price
                            else if (copy[m][n] <= minPrice) {
                                // check that the buy date isnt between other transcation dates.
                                if (n >= buyDay[f - 1] && n < sellDay[f - 1]) {
                                    existingTrans = true;
                                    minPrice = prevMinPrice;
                                    buyDay_ = prevBuyDay;
                                    break;
                                } 
                                else {
                                    prevMinPrice = minPrice;
                                    prevBuyDay = buyDay_;
                                    minPrice = copy[m][n];
                                    buyDay_ = n;
                                }
                            }
                            // if price is not smaller then try to sell it
                            // if the current sell day is between a previous transaction sell date break out
                            else if (n > buyDay[f - 1] && n <= sellDay[f - 1]) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            }
                            // do a check to make sure you dont save the same transaction
                            else if (n == sellDay[f - 1] && buyDay_ == buyDay[f - 1] && minPrice == profit[f - 1]) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            } else {
                                int currentTransaction = copy[m][n] - minPrice;
                                // do a check that the new transaction is bigger than the current saved temp profit
                                // do a check that the new transaction is smaller than the previous transactions
                                if (currentTransaction > tempProfit && currentTransaction <= profit[f - 1]) {
                                    tempProfit = currentTransaction;
                                    tempBuyDay = buyDay_;
                                    tempSellDay = n;
                                    tempStock = m;
                                }
                            }
                        }
                    }
                    existingTrans = false;
                }                
            }
        
            profit[t] = tempProfit;
            buyDay[t] = tempBuyDay;
            sellDay[t] = tempSellDay;
            stock[t] = tempStock;
        }
        int total = 0;
        int tempTotal = 0;
        for (int x = 0; x < k; x++) {
            tempTotal = Math.max(tempTotal, altMaxProfit(copy, k, buyDay[x], sellDay[x], stock[x]));
            total += profit[x];
        }
        total = Math.max(total, tempTotal);

        /*
        need to add print for when altMaxProfit has the maxprofit
         * String lineOne = scanner.nextLine();
        String[] lineOneParts = lineOne.split("\\s+");
         */


        for (int z = 0; z < k; z++) {
            System.out.println((stock[z]+1) + " " + (buyDay[z]+1) + " " + (sellDay[z]+1));
        }
    }
}