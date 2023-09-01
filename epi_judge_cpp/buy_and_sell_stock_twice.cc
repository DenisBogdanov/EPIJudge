#include <vector>

#include "test_framework/generic_test.h"

using std::vector;
using std::min;
using std::max;

double BuyAndSellStockTwice(const vector<double> &prices) {
    int n = prices.size();
    if (n <= 1) return 0.0;

    vector<double> maxFirstBuy;
    maxFirstBuy.push_back(0);

    double minSoFar = prices[0];
    double result = 0.0;

    for (int i = 1; i < n; ++i) {
        minSoFar = min(minSoFar, prices[i]);
        result = max(result, prices[i] - minSoFar);
        maxFirstBuy.push_back(result);
    }

    vector<double> maxLastBuy;
    maxLastBuy.push_back(0);

    double maxSoFar = prices[n - 1];
    result = 0.0;
    for (int i = n - 2; i >= 0; --i) {
        maxSoFar = max(maxSoFar, prices[i]);
        result = max(result, maxSoFar - prices[i]);
        maxLastBuy.push_back(result);
    }

    std::reverse(maxLastBuy.begin(), maxLastBuy.end());

    for (int i = 0; i < n - 1; ++i) {
        result = max(result, maxFirstBuy[i] + maxLastBuy[i + 1]);
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
