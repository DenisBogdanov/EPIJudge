package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStockTwice {

    @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
    public static double buyAndSellStockTwice(List<Double> prices) {
        var size = prices.size();
        var buyFirstTime = new double[size];
        var minPriceSoFar = prices.getFirst();
        var maxProfit = 0.0;

        for (int i = 0; i < prices.size(); i++) {
            Double price = prices.get(i);
            minPriceSoFar = Math.min(minPriceSoFar, price);
            maxProfit = Math.max(maxProfit, price - minPriceSoFar);
            buyFirstTime[i] = maxProfit;
        }

        var result = maxProfit;

        var buySecondTime = new double[size];
        var maxPriceSoFar = prices.getLast();
        maxProfit = 0.0;
        for (int i = size - 1; i >= 0; i--) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
            maxProfit = Math.max(maxProfit, maxPriceSoFar - prices.get(i));
            buySecondTime[i] = maxProfit;
        }

        for (int i = 0; i < buyFirstTime.length - 2; i++) {
            result = Math.max(result, buyFirstTime[i] + buySecondTime[i + 1]);
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
