from typing import List

from test_framework import generic_test


def plus_one(arr: List[int]) -> List[int]:
    arr[len(arr) - 1] += 1
    for i in reversed(range(1, len(arr))):
        if arr[i] != 10:
            return arr
        arr[i] = 0
        arr[i - 1] += 1

    if arr[0] == 10:
        arr[0] = 1
        arr.append(0)

    return arr


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('int_as_array_increment.py',
                                       'int_as_array_increment.tsv', plus_one))
