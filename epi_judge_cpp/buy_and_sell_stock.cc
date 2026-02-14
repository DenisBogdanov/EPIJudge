#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

double BuyAndSellStockOnce(const vector<double> &prices) {
    double min_so_far = prices[0];
    double ans = 0.0;
    for (auto price : prices) {
        ans = std::max(ans, price - min_so_far);
        min_so_far = std::min(min_so_far, price);
    }
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"prices"};
    return GenericTestMain(args, "buy_and_sell_stock.cc",
                           "buy_and_sell_stock.tsv", &BuyAndSellStockOnce,
                           DefaultComparator{}, param_names);
}
