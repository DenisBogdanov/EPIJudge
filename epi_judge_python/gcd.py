from test_framework import generic_test


def gcd(x: int, y: int) -> int:
    if y == 0:
        return x
    return gcd(y, x % y)


if __name__ == '__main__':
    exit(generic_test.generic_test_main('gcd.py', 'gcd.tsv', gcd))
