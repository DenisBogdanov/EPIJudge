from test_framework import generic_test


def closest_int_same_bit_count(x: int) -> int:
    for i in range(63):
        if (x >> i) & 1 != (x >> (i + 1)) & 1:
            x ^= 1 << i
            x ^= 1 << (i + 1)
            break

    return x


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('closest_int_same_weight.py',
                                       'closest_int_same_weight.tsv',
                                       closest_int_same_bit_count))
