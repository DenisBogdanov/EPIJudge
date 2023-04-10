package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStockTwice {

    @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
    public static double buyAndSellStockTwice(List<Double> prices) {
        int n = prices.size();
        double[] firstBuySellProfits = new double[n];
        double currMin = prices.get(0);
        for (int i = 1; i < n; i++) {
            currMin = Math.min(currMin, prices.get(i));
            firstBuySellProfits[i] = Math.max(firstBuySellProfits[i - 1], prices.get(i) - currMin);
        }

        double[] secondBuySellProfits = new double[n];
        double currMax = prices.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            currMax = Math.max(currMax, prices.get(i));
            secondBuySellProfits[i] = Math.max(secondBuySellProfits[i + 1], currMax - prices.get(i));
        }

        double result = firstBuySellProfits[n - 1];

        for (int i = 0; i < n - 1; i++) {
            result = Math.max(result, firstBuySellProfits[i] + secondBuySellProfits[i + 1]);
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
