#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

// Given n, return all primes up to and including n.
vector<int> GeneratePrimes(int n) {
    vector<bool> isPrime(n + 1, true);
    isPrime[0] = false;
    isPrime[1] = false;

    vector<int> result;

    for (int i = 2; i <= n; ++i) {
        if (!isPrime[i]) continue;
        result.push_back(i);

        for (int j = i + i; j <= n; j += i) {
            isPrime[j] = false;
        }
    }

    return result;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"n"};
    return GenericTestMain(args, "prime_sieve.cc", "prime_sieve.tsv",
                           &GeneratePrimes, DefaultComparator{}, param_names);
}
