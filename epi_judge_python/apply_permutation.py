from typing import List

from test_framework import generic_test


def apply_permutation(perm: List[int], arr: List[int]) -> None:
    for i in range(len(arr)):
        next_index = i
        while perm[next_index] >= 0:
            arr[i], arr[perm[next_index]] = arr[perm[next_index]], arr[i]
            temp = perm[next_index]
            perm[next_index] -= len(perm)
            next_index = temp

    perm[:] = [a + len(perm) for a in perm]


def apply_permutation_wrapper(perm, A):
    apply_permutation(perm, A)
    return A


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('apply_permutation.py',
                                       'apply_permutation.tsv',
                                       apply_permutation_wrapper))
