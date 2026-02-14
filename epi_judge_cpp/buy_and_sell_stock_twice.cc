#include <vector>

#include "test_framework/generic_test.h"

using namespace std;

double BuyAndSellStockTwice(const vector<double> &prices) {
    vector<double> best_first_buy;
    double min_price_so_far = prices[0];
    double curr_best = 0;
    for (double price : prices) {
        curr_best = max(curr_best, price - min_price_so_far);
        min_price_so_far = min(min_price_so_far, price);
        best_first_buy.push_back(curr_best);
    }

    double result = curr_best;

    double max_price_so_far = prices.back();
    curr_best = 0;
    vector<double> best_second_buy(prices.size());
    for (int i = prices.size() - 1; i >= 0; i--) {
        curr_best = max(curr_best, max_price_so_far - prices[i]);
        max_price_so_far = max(max_price_so_far, prices[i]);
        best_second_buy[i] = curr_best;
    }

    for (int i = 1; i < prices.size() - 1; i++) {
        result = max(result, best_first_buy[i] + best_second_buy[i + 1]);
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
