package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class BuyAndSellStockTwice {

  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    double maxTotalProfit = 0.0;

    List<Double> firstBuySellProfits = new ArrayList<>(prices.size());

    double currentMin = Double.MAX_VALUE;

    for (Double price : prices) {
      currentMin = Math.min(currentMin, price);
      maxTotalProfit = Math.max(maxTotalProfit, price - currentMin);
      firstBuySellProfits.add(maxTotalProfit);
    }

    double currentMax = Double.MIN_VALUE;

    for (int i = prices.size() - 1; i > 0; i--) {
      currentMax = Math.max(currentMax, prices.get(i));
      maxTotalProfit = Math.max(
          maxTotalProfit,
          currentMax - prices.get(i) + firstBuySellProfits.get(i - 1)
      );
    }

    return maxTotalProfit;
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
