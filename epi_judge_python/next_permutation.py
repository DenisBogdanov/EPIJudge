from typing import List

from test_framework import generic_test


def next_permutation(perm: List[int]) -> List[int]:
    inversion_point = len(perm) - 1
    last_index = inversion_point

    while inversion_point > 0 and perm[inversion_point] <= perm[inversion_point - 1]:
        inversion_point -= 1

    if inversion_point == 0:
        return []

    right = last_index
    while perm[right] <= perm[inversion_point - 1]:
        right -= 1

    perm[inversion_point - 1], perm[right] = perm[right], perm[inversion_point - 1]

    right = last_index

    while inversion_point < right:
        perm[inversion_point], perm[right] = perm[right], perm[inversion_point]
        inversion_point += 1
        right -= 1

    return perm


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('next_permutation.py',
                                       'next_permutation.tsv',
                                       next_permutation))
