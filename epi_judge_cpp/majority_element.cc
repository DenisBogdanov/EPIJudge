#include <string>
#include <vector>

#include "test_framework/generic_test.h"
using std::string;
using std::vector;

string MajoritySearch(vector<string>::const_iterator stream_begin,
                      const vector<string>::const_iterator stream_end) {

  auto it = stream_begin;
  string ans = "";
  int count = 1;
  while (it != stream_end) {
    if (ans == *it) {
      count++;
    } else {
      count--;
      if (count == 0) {
        ans = *it;
        count = 1;
      }
    }
    it++;
  }
  return ans;
}
string MajoritySearchWrapper(const vector<string>& stream) {
  return MajoritySearch(cbegin(stream), cend(stream));
}

int main(int argc, char* argv[]) {
  std::vector<std::string> args{argv + 1, argv + argc};
  std::vector<std::string> param_names{"stream"};
  return GenericTestMain(args, "majority_element.cc", "majority_element.tsv",
                         &MajoritySearchWrapper, DefaultComparator{},
                         param_names);
}
