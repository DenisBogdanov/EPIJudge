from typing import List

from test_framework import generic_test


# Given n, return all primes up to and including n.
def generate_primes(n: int) -> List[int]:
    isPrime = [True] * (n + 1)

    result = []

    for num in range(2, n + 1):
        if isPrime[num]:
            result.append(num)

            for notPrime in range(num * num, n + 1, num):
                isPrime[notPrime] = False

    return result


if __name__ == '__main__':
    exit(
        generic_test.generic_test_main('prime_sieve.py', 'prime_sieve.tsv',
                                       generate_primes))
