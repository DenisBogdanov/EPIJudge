from test_framework import generic_test


def reverse(x: int) -> int:
    is_negative = x < 0
    x = abs(x)

    result = 0

    while x:
        result *= 10
        result += x % 10
        x //= 10

    return -result if is_negative else result


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('reverse_digits.py',
                                       'reverse_digits.tsv', reverse))
