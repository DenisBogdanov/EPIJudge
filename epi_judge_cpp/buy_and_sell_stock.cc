#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

double BuyAndSellStockOnce(const vector<double> &prices) {
    double minSoFar = prices[0];
    double result = 0.0;

    for (int i = 1; i < prices.size(); ++i) {
        minSoFar = std::min(minSoFar, prices[i]);
        result = std::max(result, prices[i] - minSoFar);
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"prices"};
    return GenericTestMain(args, "buy_and_sell_stock.cc",
                           "buy_and_sell_stock.tsv", &BuyAndSellStockOnce,
                           DefaultComparator{}, param_names);
}
