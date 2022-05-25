from typing import List

from test_framework import generic_test


def can_reach_end(arr: List[int]) -> bool:
    next_furthest = 0
    i = 0
    last_index = len(arr) - 1

    while i <= next_furthest < last_index:
        next_furthest = max(next_furthest, i + arr[i])
        i += 1

    return next_furthest >= last_index


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('advance_by_offsets.py',
                                       'advance_by_offsets.tsv',
                                       can_reach_end))
