from test_framework import generic_test


def power(x: float, y: int) -> float:
    if y < 0:
        x, y = 1 / x, -y
    if y == 0:
        return 1
    if y & 1:
        return x * power(x, y - 1)
    else:
        half_power = power(x, y >> 1)
        return half_power * half_power


if __name__ == '__main__':
    exit(generic_test.generic_test_main('power_x_y.py', 'power_x_y.tsv',
                                        power))
