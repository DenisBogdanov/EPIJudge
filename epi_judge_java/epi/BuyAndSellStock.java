package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    double currentMin = Double.MAX_VALUE;

    double result = 0.0;

    for (Double currentPrice : prices) {
      if (currentPrice < currentMin) {
        currentMin = currentPrice;
      }

      if (currentPrice - currentMin > result) {
        result = currentPrice - currentMin;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
