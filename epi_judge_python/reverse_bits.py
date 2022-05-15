from swap_bits import swap_bits
from test_framework import generic_test


def reverse_bits(x: int) -> int:
    left = 0
    right = 63
    while left < right:
        x = swap_bits(x, left, right)
        left += 1
        right -= 1
    return x


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('reverse_bits.py', 'reverse_bits.tsv',
                                       reverse_bits))
