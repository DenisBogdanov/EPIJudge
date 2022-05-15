from test_framework import generic_test


def multiply(x: int, y: int) -> int:
    if x == 0 or y == 0:
        return 0

    result = 0

    while y > 1:
        if y & 1:
            result += x
            y -= 1
        else:
            x <<= 1
            y >>= 1

    return result + x


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('primitive_multiply.py',
                                       'primitive_multiply.tsv', multiply))
