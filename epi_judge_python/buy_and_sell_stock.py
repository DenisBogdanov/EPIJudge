from typing import List

from test_framework import generic_test


def buy_and_sell_stock_once(prices: List[float]) -> float:
    cur_min = prices[0]
    result = 0.0

    for price in prices:
        if price < cur_min:
            cur_min = price
        result = max(result, price - cur_min)

    return result


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('buy_and_sell_stock.py',
                                       'buy_and_sell_stock.tsv',
                                       buy_and_sell_stock_once))
