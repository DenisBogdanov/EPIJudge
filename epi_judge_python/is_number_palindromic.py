import math

from test_framework import generic_test


def is_palindrome_number(x: int) -> bool:
    if x <= 0:
        return x == 0

    num_digits = math.floor(math.log10(x)) + 1
    most_significant_digit_mask = 10 ** (num_digits - 1)

    for i in range(num_digits // 2):
        if x // most_significant_digit_mask != x % 10:
            return False

        x %= most_significant_digit_mask
        x //= 10
        most_significant_digit_mask //= 100

    return True


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('is_number_palindromic.py',
                                       'is_number_palindromic.tsv',
                                       is_palindrome_number))
