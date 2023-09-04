#include <algorithm>
#include <functional>
#include <iterator>
#include <numeric>
#include <vector>
#include <random>

#include "test_framework/generic_test.h"
#include "test_framework/random_sequence_checker.h"

using namespace std;

// Returns a random k-sized subset of {0, 1, ..., n - 1}.
vector<int> RandomSubset(int n, int k) {
    vector<int> result;

    default_random_engine seed((random_device()) ());

    for (int i = 0; i < k; ++i) {
        result.push_back(i);
    }

    for (int i = k; i < n; i++) {
        int randomIndex = uniform_int_distribution<int>{0, i}(seed);
        if (randomIndex < k) {
            result[randomIndex] = i;
        }
    }

    return result;
}

bool RandomSubsetRunner(TimedExecutor &executor, int n, int k) {
    using namespace test_framework;
    vector<vector<int>> results;

    executor.Run([&] {
        std::generate_n(back_inserter(results), 100000,
                        std::bind(RandomSubset, n, k));
    });

    int total_possible_outcomes = BinomialCoefficient(n, k);
    vector<int> A(n);
    iota(begin(A), end(A), 0);
    vector<vector<int>> combinations;
    for (int i = 0; i < BinomialCoefficient(n, k); ++i) {
        combinations.emplace_back(ComputeCombinationIdx(A, n, k, i));
    }
    vector<int> sequence;
    for (vector<int> result: results) {
        sort(begin(result), end(result));
        sequence.emplace_back(
                distance(begin(combinations),
                         find(begin(combinations), end(combinations), result)));
    }
    return CheckSequenceIsUniformlyRandom(sequence, total_possible_outcomes,
                                          0.01);
}

void RandomSubsetWrapper(TimedExecutor &executor, int n, int k) {
    RunFuncWithRetries(bind(RandomSubsetRunner, std::ref(executor), n, k));
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "n", "k"};
    return GenericTestMain(args, "random_subset.cc", "random_subset.tsv",
                           &RandomSubsetWrapper, DefaultComparator{},
                           param_names);
}
