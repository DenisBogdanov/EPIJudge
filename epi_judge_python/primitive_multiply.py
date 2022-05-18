from test_framework import generic_test


def multiply(x: int, y: int) -> int:
    result = 0

    while y:
        if y & 1:
            result += x
        x <<= 1
        y >>= 1

    return result


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('primitive_multiply.py',
                                       'primitive_multiply.tsv', multiply))
