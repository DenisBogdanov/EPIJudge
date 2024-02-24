#include <algorithm>
#include <functional>
#include <iterator>
#include <vector>
#include <random>

#include "test_framework/generic_test.h"
#include "test_framework/random_sequence_checker.h"
#include "test_framework/timed_executor.h"

using namespace std;

// Assumption: there are at least k elements in the stream.
vector<int> OnlineRandomSample(vector<int>::const_iterator stream_begin,
                               const vector<int>::const_iterator stream_end,
                               int k) {

    vector<int> result;
    default_random_engine seed((random_device())());

    int count = 0;
    for (auto i = stream_begin; i != stream_end; ++i) {
        if (count < k) {
            result.push_back(*i);
        } else {
            auto index = uniform_int_distribution<int>{0, static_cast<int>(count)}(seed);
            if (index < k) {
                result[index] = *i;
            }
        }

        count++;
    }

    return result;
}

bool OnlineRandomSamplingRunner(TimedExecutor &executor, vector<int> stream,
                                int k) {
    using namespace test_framework;
    vector<vector<int>> results;

    executor.Run([&] {
        std::generate_n(
                back_inserter(results), 100000,
                std::bind(OnlineRandomSample, cbegin(stream), cend(stream), k));
    });

    int total_possible_outcomes = BinomialCoefficient(stream.size(), k);
    sort(begin(stream), end(stream));
    vector<vector<int>> combinations;
    for (int i = 0; i < BinomialCoefficient(stream.size(), k); ++i) {
        combinations.emplace_back(
                ComputeCombinationIdx(stream, stream.size(), k, i));
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

void OnlineRandomSampleWrapper(TimedExecutor &executor,
                               const vector<int> &stream, int k) {
    RunFuncWithRetries(bind(OnlineRandomSamplingRunner, std::ref(executor),
                            std::cref(stream), k));
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"executor", "stream", "k"};
    return GenericTestMain(args, "online_sampling.cc", "online_sampling.tsv",
                           &OnlineRandomSampleWrapper, DefaultComparator{},
                           param_names);
}
