package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.List;

public class RefuelingSchedule {
    private static final int MPG = 20;

    // gallons[i] is the amount of gas in city i, and distances[i] is the distance
    public static int findAmpleCity(List<Integer> gallons, List<Integer> distances) {
        int mpg = 20;

        int cityIndex = 0;
        int min = Integer.MAX_VALUE;
        int currGas = 0;
        int n = gallons.size();

        for (int i = 0; i < n; i++) {
            if (currGas < min) {
                min = currGas;
                cityIndex = i;
            }

            currGas += gallons.get(i);

            int dist = distances.get(i);
            currGas -= dist / mpg;
        }

        return cityIndex;
    }
    // city i to the next city.

    @EpiTest(testDataFile = "refueling_schedule.tsv")
    public static void findAmpleCityWrapper(TimedExecutor executor,
                                            List<Integer> gallons,
                                            List<Integer> distances)
            throws Exception {
        int result = executor.run(() -> findAmpleCity(gallons, distances));
        final int numCities = gallons.size();
        int tank = 0;
        for (int i = 0; i < numCities; ++i) {
            int city = (result + i) % numCities;
            tank += gallons.get(city) * MPG - distances.get(city);
            if (tank < 0) {
                throw new TestFailure(String.format("Out of gas on city %d", city));
            }
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RefuelingSchedule.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
