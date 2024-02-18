#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

double BuyAndSellStockTwice(const vector<double> &prices) {
    double result = 0.0;
    vector<double> firstBuy;

    double minSoFar = prices[0];
    for (int i = 1; i < prices.size(); ++i) {
        minSoFar = std::min(minSoFar, prices[i]);
        result = std::max(result, prices[i] - minSoFar);
        firstBuy.push_back(result);
    }

    double maxSoFar = prices.back();

    for (int i = prices.size() - 1; i >= 2; --i) {
        maxSoFar = std::max(maxSoFar, prices[i]);
        double firstBuyMax = firstBuy[i - 1];
        result = std::max(result, firstBuyMax + maxSoFar - prices[i]);
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"prices"};
    return GenericTestMain(args, "buy_and_sell_stock_twice.cc",
                           "buy_and_sell_stock_twice.tsv", &BuyAndSellStockTwice,
                           DefaultComparator{}, param_names);
}
