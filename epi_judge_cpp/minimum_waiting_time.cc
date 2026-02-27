#include <vector>

#include "test_framework/generic_test.h"
using namespace std;

int MinimumTotalWaitingTime(vector<int> service_times) {
    sort(service_times.begin(), service_times.end());
    int ans = 0;
    for (int i = 0; i < service_times.size(); i++) {
        ans += service_times[i] * (service_times.size() - 1 - i);
    }
    return ans;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"service_times"};
    return GenericTestMain(args, "minimum_waiting_time.cc",
                           "minimum_waiting_time.tsv", &MinimumTotalWaitingTime,
                           DefaultComparator{}, param_names);
}
