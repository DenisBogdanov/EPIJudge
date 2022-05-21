import functools
from typing import List

from test_framework import generic_test
from test_framework.test_failure import TestFailure
from test_framework.test_utils import enable_executor_hook

RED, WHITE, BLUE = range(3)


def dutch_flag_partition(pivot_index: int, arr: List[int]) -> None:
    # initial_solution(arr, pivot_index)


    pivot = arr[pivot_index]
    less = 0
    equal = 0
    greater = len(arr)

    while equal < greater:
        if arr[equal] < pivot:
            arr[less], arr[equal] = arr[equal], arr[less]
            less += 1
            equal += 1
        elif arr[equal] == pivot:
            equal += 1
        else:
            greater -= 1
            arr[greater], arr[equal] = arr[equal], arr[greater]




def initial_solution(arr, pivot_index):
    pivot = arr[pivot_index]
    index = 0
    less_index = 0
    while index < len(arr):
        if arr[index] < pivot:
            if index != less_index:
                arr[index], arr[less_index] = arr[less_index], arr[index]
            less_index += 1
        index += 1
    index = len(arr) - 1
    greater_index = index
    while index >= 0 and arr[index] >= pivot:
        if arr[index] > pivot:
            if index != greater_index:
                arr[index], arr[greater_index] = arr[greater_index], arr[index]
            greater_index -= 1
        index -= 1


@enable_executor_hook
def dutch_flag_partition_wrapper(executor, A, pivot_idx):
    count = [0, 0, 0]
    for x in A:
        count[x] += 1
    pivot = A[pivot_idx]

    executor.run(functools.partial(dutch_flag_partition, pivot_idx, A))

    i = 0
    while i < len(A) and A[i] < pivot:
        count[A[i]] -= 1
        i += 1
    while i < len(A) and A[i] == pivot:
        count[A[i]] -= 1
        i += 1
    while i < len(A) and A[i] > pivot:
        count[A[i]] -= 1
        i += 1

    if i != len(A):
        raise TestFailure('Not partitioned after {}th element'.format(i))
    elif any(count):
        raise TestFailure('Some elements are missing from original array')


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('dutch_national_flag.py',
                                       'dutch_national_flag.tsv',
                                       dutch_flag_partition_wrapper))
