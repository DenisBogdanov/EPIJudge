package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStockTwice {

    @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
    public static double buyAndSellStockTwice(List<Double> prices) {
        int n = prices.size();

        double[] firstSell = new double[n];
        double minPriceSoFar = prices.get(0);
        double res1 = 0.0;
        for (int i = 1; i < n; i++) {
            minPriceSoFar = Math.min(minPriceSoFar, prices.get(i));
            res1 = Math.max(res1, prices.get(i) - minPriceSoFar);
            firstSell[i] = res1;
        }

        double[] secondSell = new double[n];
        double maxPriceSoFar = prices.get(n - 1);
        double res2 = 0.0;
        for (int i = n - 2; i >= 0; i--) {
            res2 = Math.max(res2, maxPriceSoFar - prices.get(i));
            maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
            secondSell[i] = res2;
        }

        double result = Math.max(res1, res2);
        for (int i = 1; i < n - 1; i++) {
            result = Math.max(result, firstSell[i] + secondSell[i + 1]);
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
