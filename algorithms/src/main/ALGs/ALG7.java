package ALGs;

import java.util.*;

public class ALG7 {

    /* 
        Given a matrix A of m ×n integers (non-negative) representing the predicted prices of m
        stocks for n days and an integer c (positive), find the maximum profit with no restriction
        on number of transactions. However, you cannot buy any stock for c days after selling any
        stock. If you sell a stock at day i, you are not allowed to buy any stock until day i + c
    
        Design a Θ(m ∗ 2^n) time brute force algorithm for solving Problem 3   
    */

    public static int run_ALG7(int[][] copy, int c) {
        ArrayList<Integer> profit = new ArrayList<Integer>();
        ArrayList<Integer> buyDay = new ArrayList<Integer>();
        ArrayList<Integer> sellDay = new ArrayList<Integer>();
        ArrayList<Integer> stock = new ArrayList<Integer>();

        boolean existingTrans = false;

        // need to reset up the loop to allow for infinite transactions now
        // when looping thru add C days to the sell date to account for the C days not allowed to make another transaction
        int t = 0;
        int stop_ = copy.length;
        while (t < stop_) {
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
                    if (profit.size() == 0) {
                        // first transaction grab the highest transaction.
                        // loop thru until the highest is found then save it temporarily till the end of the loop of t
                        if (copy[m][n] <= minPrice) {
                            minPrice = copy[m][n];
                            buyDay_ = n;
                        } else {
                            int currentTransaction = copy[m][n] - minPrice;
                            if (currentTransaction > tempProfit) {
                                tempProfit = currentTransaction;
                                tempBuyDay = buyDay_;
                                tempSellDay = n;
                                tempStock = m;
                            }
                        }
                    } else {
                        // the first transaction has happened. 
                        // grab all following transactions compared to all previous transactions
                        for (int f = 1; f <= profit.size(); f++) {
                            // cycling through all previous transactions
                            if (existingTrans == true)
                                break;
                            // check if current price is less than saved price
                            else if (copy[m][n] <= minPrice) {
                                // check that the buy date isnt between other transcation dates.
                                if (n >= buyDay.get(f - 1) && n < sellDay.get(f - 1)) {
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
                            // if price is not smaller then try to sell it
                            // if the current sell day is between a previous transaction sell date break out
                            else if (n > buyDay.get(f - 1) && n <= sellDay.get(f - 1)) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            }
                            // do a check to make sure you dont save the same transaction
                            else if (n == sellDay.get(f - 1) && buyDay_ == buyDay.get(f - 1)
                                    && minPrice == profit.get(f - 1)) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            } else {
                                if (f == profit.size()) {
                                    int currentTransaction = copy[m][n] - minPrice;
                                    // do a check that the new transaction is bigger than the current saved temp profit
                                    // do a check that the new transaction is smaller than the previous transactions
                                    if (currentTransaction > tempProfit && currentTransaction <= profit.get(f - 1)) {
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
            profit.add(tempProfit);
            buyDay.add(tempBuyDay);
            sellDay.add(tempSellDay + c);
            stock.add(tempStock);
            t++;
        }
        int total = 0;
        int tempTotal = 0;
        //System.out.println(profit.size());
        for (int x = 0; x < profit.size(); x++) {
            tempTotal = Math.max(tempTotal, altMaxProfit(copy, c, buyDay.get(x), sellDay.get(x), stock.get(x)));
            if (profit.get(x) == -1)
                profit.set(x, 0);
            total += profit.get(x);
            System.out.println("profit: " + profit.get(x) + "\tbuy day: " + buyDay.get(x) + "\t sell day: " + sellDay.get(x) + "\tstock: " + stock.get(x));
        }
        total = Math.max(total, tempTotal);
        return total;
    }

    public static int altMaxProfit(int[][]copy, int c, int Xbuy, int Xsell, int Xstock)
    {
        ArrayList<Integer> profit = new ArrayList<Integer>();
        ArrayList<Integer> buyDay = new ArrayList<Integer>();
        ArrayList<Integer> sellDay = new ArrayList<Integer>();
        ArrayList<Integer> stock = new ArrayList<Integer>();

        boolean existingTrans = false;

        // need to reset up the loop to allow for infinite transactions now
        // when looping thru add C days to the sell date to account for the C days not allowed to make another transaction
        int t = 0;
        int stop_ = copy.length;
        while (t < stop_) {
        
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
                    if (profit.size() == 0) {
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
                                if (n >= buyDay.get(f - 1) && n < sellDay.get(f - 1)) {
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
                            // do a check to make sure the sent transaction is not saved into profits
                            else if (buyDay_ == Xbuy && n == Xsell && m == Xstock) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            }
                            // if price is not smaller then try to sell it
                            // if the current sell day is between a previous transaction sell date break out
                           else if (n > buyDay.get(f - 1) && n <= sellDay.get(f - 1)) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            }
                            // do a check to make sure you dont save the same transaction
                            else if (n == sellDay.get(f - 1) && buyDay_ == buyDay.get(f - 1) && minPrice == profit.get(f - 1)) {
                                existingTrans = true;
                                minPrice = prevMinPrice;
                                buyDay_ = prevBuyDay;
                                break;
                            } else {
                                if (f == t) {
                                    int currentTransaction = copy[m][n] - minPrice;
                                    // do a check that the new transaction is bigger than the current saved temp profit
                                    // do a check that the new transaction is smaller than the previous transactions
                                    if (currentTransaction > tempProfit && currentTransaction <= profit.get(f - 1)) {
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
            profit.add(tempProfit);
            buyDay.add(tempBuyDay);
            sellDay.add(tempSellDay + c);
            stock.add(tempStock);
            t++;
        }
        int total = 0;

        for (int x = 0; x < profit.size(); x++) {
            //System.out.println(profit[x]);
            //System.out.println("sub-transaction #" + (x+1) + ": " + profit[x] + "\tBuy Day: " + buyDay[x] + "\tSell Day: " + sellDay[x] + "\tStock: " + stock[x]);
            if (profit.get(x) == -1)
                profit.set(x, 0);
            total += profit.get(x);
        }
        //System.out.println();
        return total;
    }
}
