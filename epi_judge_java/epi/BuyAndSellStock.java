package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStock {

    @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
    public static double computeMaxProfit(List<Double> prices) {
        if (prices.size() <= 1) return 0.0;

        double result = 0;
        double currMin = prices.get(0);

        for (Double price : prices) {
            currMin = Math.min(currMin, price);
            result = Math.max(result, price - currMin);
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
