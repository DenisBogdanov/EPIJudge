package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStock {

    @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
    public static double computeMaxProfit(List<Double> prices) {
        if (prices.isEmpty()) {
            return 0.0;
        }
        var lowestPriceSoFar = prices.getFirst();
        var result = 0.0;

        for (Double price : prices) {
            lowestPriceSoFar = Math.min(lowestPriceSoFar, price);
            result = Math.max(result, price - lowestPriceSoFar);
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
