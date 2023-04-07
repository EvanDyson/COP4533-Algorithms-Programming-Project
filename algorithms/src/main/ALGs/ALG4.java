// Evan

package ALGs;
import java.util.*;

/*
 For the purpose of clearity and cleanliness I have made two pairs of functions.
        The "run_ALG4" function is to show the time complexity of the algorithm. As well as showing off the code for easier readability.


        The "task4" function is for the purpose of part 4 of the assignment, in order to use the proper input and give out the correct output I have made a seperate function
            that will take in the correct input and then correctly display all of the buy days, sell days, and stocks.
 */

public class ALG4 {

    // NEED TO ADD COMMENTS

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
                                if (f == t) {
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
        //System.out.println();
        for (int x = 0; x < k; x++) {
            tempTotal = Math.max(tempTotal, altMaxProfit(copy, k, buyDay[x], sellDay[x], stock[x]));
            if (profit[x] == -1)
                profit[x] = 0;
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
                                if (f == t) {
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
            if (profit[x] == -1)
                profit[x] = 0;
            total += profit[x];
        }
        //System.out.println();
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
                                if (f == t) {
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
        int altTotal = 0;
        String altTotalString;
        String[] altTotalParts = new String[k + 1];
        String[] maxAltTotal = new String[k + 1];
        for (int x = 0; x < k; x++) {
            altTotalString = altTask4(copy, k, buyDay[x], sellDay[x], stock[x]);
            altTotalParts = altTotalString.split("\\n");
            if (Integer.valueOf(altTotalParts[0]) > altTotal) {
                for (int w = 0; w < k + 1; w++)
                    maxAltTotal[w] = altTotalParts[w];
                altTotal = Integer.valueOf(maxAltTotal[0]);
            }
            if (profit[x] == -1)
                profit[x] = 0;
            total += profit[x];
        }
        if (Integer.valueOf(maxAltTotal[0]) > total) {
            for (int i = 1; i < k+1; i++) {
                System.out.println(maxAltTotal[i]);
            }
        }
        else {
            int index = -1;
            int[] savedBuy = new int[k];
            int[] savedSell = new int[k];
            int[] savedStock = new int[k];

            for (int xy = 0; xy < k; xy++) {
                int lowDay = Integer.MAX_VALUE;

                for (int x = 0; x < k; x++) {
                    if (profit[x] == -1)
                        profit[x] = 0;
                    if (buyDay[x] < lowDay && buyDay[x] != -1) {
                        lowDay = buyDay[x];
                        index = x;
                    }
                }
                savedBuy[xy] = buyDay[index];
                savedSell[xy] = sellDay[index];
                savedStock[xy] = stock[index];
                buyDay[index] = -1;
            }

            String profitString = "";
            for (int z = 0; z < k; z++) {
                profitString += (Integer.toString(savedStock[z] + 1) + " " + Integer.toString(savedBuy[z] + 1) + " " + Integer.toString(savedSell[z] + 1) + "\n");
            }

            System.out.println(profitString);
        }
    }

    public static String altTask4(int[][]copy, int k, int Xbuy, int Xsell, int Xstock) {
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
                                if (f == t) {
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
                    }
                    existingTrans = false;
                }
            }
            profit[t] = tempProfit;
            buyDay[t] = tempBuyDay;
            sellDay[t] = tempSellDay;
            stock[t] = tempStock;
        }

        int index = -1;
        int prevDay = -1;
        int total = 0;
        int[] savedBuy = new int[k];
        int[] savedSell = new int[k];
        int[] savedStock = new int[k];

        for (int xy = 0; xy < k; xy++) {
            int lowDay = Integer.MAX_VALUE;

            for (int x = 0; x < k; x++) {
                if (profit[x] == -1)
                    profit[x] = 0;
                // need to fix this if statement... order is not being saved properly
                if (buyDay[x] < lowDay /*&& lowDay > prevDay && prevDay != buyDay[x]*/ && buyDay[x] != -1) {
                    lowDay = buyDay[x];
                    index = x;
                    //buyDay[x] = -1;
                }
            }
            if (profit[index] != 0) {
                prevDay = lowDay;
                total += profit[index];
                savedBuy[xy] = buyDay[index];
                savedSell[xy] = sellDay[index];
                savedStock[xy] = stock[index];
                buyDay[index] = -1;
                profit[index] = 0;
            }
        }

        String profitString = Integer.toString(total) + "\n";
        for (int z = 0; z < k; z++) {
            //profitString = profitString.concat(Integer.toString(stock[z] + 1) + " " + Integer.toString(buyDay[z] + 1) + " " + Integer.toString(sellDay[z] + 1) + "\n");
            profitString += (Integer.toString(savedStock[z] + 1) + " " + Integer.toString(savedBuy[z] + 1) + " " + Integer.toString(savedSell[z] + 1) + "\n");
        }

        return profitString;
    }
}