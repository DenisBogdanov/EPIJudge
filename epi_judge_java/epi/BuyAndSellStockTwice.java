package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStockTwice {

    @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
    public static double buyAndSellStockTwice(List<Double> prices) {
        int n = prices.size();
        double[] left = new double[n];
        double minPriceSoFar = prices.get(0);
        double maxProfitSoFar = 0.0;
        for (int i = 1; i < n; i++) {
            minPriceSoFar = Math.min(minPriceSoFar, prices.get(i));
            maxProfitSoFar = Math.max(maxProfitSoFar, prices.get(i) - minPriceSoFar);
            left[i] = maxProfitSoFar;
        }
        double ans = maxProfitSoFar;

        double maxPriceSoFar = prices.get(n - 1);
        maxProfitSoFar = 0.0;
        for (int i = n - 2; i > 0; i--) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
            maxProfitSoFar = Math.max(maxProfitSoFar, maxPriceSoFar - prices.get(i));
            ans = Math.max(ans, maxProfitSoFar + left[i - 1]);
        }
        return ans;
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
