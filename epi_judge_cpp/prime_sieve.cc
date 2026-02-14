#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

// Given n, return all primes up to and including n.
vector<int> GeneratePrimes(int n) {
    vector<bool> is_prime(n + 1, true);
    for (int num = 2; num <= n; ++num) {
        if (is_prime[num]) {
            for (int not_prime = num * 2; not_prime <= n; not_prime += num) {
                is_prime[not_prime] = false;
            }
        }
    }

    vector<int> ans;
    for (int num = 2; num <= n; ++num) {
        if (is_prime[num]) ans.push_back(num);
    }
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"n"};
    return GenericTestMain(args, "prime_sieve.cc", "prime_sieve.tsv",
                           &GeneratePrimes, DefaultComparator{}, param_names);
}
