package epi;

import epi.test_framework.*;

import java.util.*;

public class GroupEqualEntries {

  public static void groupByAge(List<Person> people) {
    Map<Integer, Integer> ageToCountMap = new HashMap<>();
    for (Person person : people) {
      ageToCountMap.merge(person.age, 1, Integer::sum);
    }

    Map<Integer, Integer> ageToOffsetMap = new HashMap<>();
    int offset = 0;
    for (Map.Entry<Integer, Integer> ageToCountEntry : ageToCountMap.entrySet()) {
      ageToOffsetMap.put(ageToCountEntry.getKey(), offset);
      offset += ageToCountEntry.getValue();
    }

    while (!ageToOffsetMap.isEmpty()) {
      Map.Entry<Integer, Integer> from = ageToOffsetMap.entrySet().iterator().next();
      Integer toAge = people.get(from.getValue()).age;
      Integer toValue = ageToOffsetMap.get(toAge);
      Collections.swap(people, from.getValue(), toValue);
      int count = ageToCountMap.get(toAge) - 1;
      ageToCountMap.put(toAge, count);
      if (count > 0) {
        ageToOffsetMap.put(toAge, toValue + 1);
      } else {
        ageToOffsetMap.remove(toAge);
      }
    }
  }

  @EpiUserType(ctorParams = {Integer.class, String.class})
  public static class Person {
    public Integer age;
    public String name;

    public Person(Integer k, String n) {
      age = k;
      name = n;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;

      Person person = (Person) o;

      if (!age.equals(person.age))
        return false;
      return name.equals(person.name);
    }

    @Override
    public int hashCode() {
      int result = age.hashCode();
      result = 31 * result + name.hashCode();
      return result;
    }
  }

  private static Map<Person, Integer> buildMultiset(List<Person> people) {
    Map<Person, Integer> m = new HashMap<>();
    for (Person p : people) {
      m.put(p, m.getOrDefault(p, 0) + 1);
    }
    return m;
  }

  @EpiTest(testDataFile = "group_equal_entries.tsv")
  public static void groupByAgeWrapper(TimedExecutor executor,
                                       List<Person> people) throws Exception {
    if (people.isEmpty()) {
      return;
    }
    Map<Person, Integer> values = buildMultiset(people);

    executor.run(() -> groupByAge(people));

    Map<Person, Integer> newValues = buildMultiset(people);
    if (!values.equals(newValues)) {
      throw new TestFailure("Entry set changed");
    }

    Set<Integer> ages = new HashSet<>();
    int lastAge = people.get(0).age;
    for (Person p : people) {
      if (ages.contains(p.age)) {
        throw new TestFailure("Entries are not grouped by age");
      }
      if (p.age != lastAge) {
        ages.add(lastAge);
        lastAge = p.age;
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "GroupEqualEntries.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
