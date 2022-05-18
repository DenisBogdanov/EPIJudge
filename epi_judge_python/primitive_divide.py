from test_framework import generic_test


def divide(x: int, y: int) -> int:
    result = 0

    for power in range(31, -1, -1):
        y_power = y << power
        if x >= y_power:
            result += 1 << power
            x -= y_power

    return result


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('primitive_divide.py',
                                       'primitive_divide.tsv', divide))
