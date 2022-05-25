from typing import List

from test_framework import generic_test


def buy_and_sell_stock_twice(prices: List[float]) -> float:
    diff = []

    result = 0.0

    cur_min = prices[0]
    for price in prices:
        if price < cur_min:
            cur_min = price
        result = max(result, price - cur_min)
        diff.append(result)

    cur_max = prices[-1]
    for i in range(len(prices) - 1, -1, -1):
        if prices[i] > cur_max:
            cur_max = prices[i]
        result = max(result, cur_max - prices[i] + diff[i])

    return result


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('buy_and_sell_stock_twice.py',
                                       'buy_and_sell_stock_twice.tsv',
                                       buy_and_sell_stock_twice))
