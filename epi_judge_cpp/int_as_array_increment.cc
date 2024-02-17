#include <vector>

#include "test_framework/generic_test.h"

using std::vector;

vector<int> PlusOne(vector<int> v) {
    ++v.back();
    for (int i = v.size() - 1; i > 0; --i) {
        if (v[i] < 10) break;
        v[i] = 0;
        ++v[i - 1];
    }

    if (v[0] == 10) {
        v[0] = 0;
        v.insert(v.begin(), 1);
    }

    return v;
}

int main(int argc, char *argv[]) {
    std::vector<std::string> args{argv + 1, argv + argc};
    std::vector<std::string> param_names{"A"};
    return GenericTestMain(args, "int_as_array_increment.cc",
                           "int_as_array_increment.tsv", &PlusOne,
                           DefaultComparator{}, param_names);
}
