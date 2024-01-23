package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class CalendarRendering {

    @EpiTest(testDataFile = "calendar_rendering.tsv")
    public static int findMaxSimultaneousEvents(List<Event> events) {
        int latestEnd = 0;
        for (Event event : events) {
            latestEnd = Math.max(latestEnd, event.finish);
        }

        int[] amountOfEventsByDay = new int[latestEnd + 2];
        for (Event event : events) {
            amountOfEventsByDay[event.start]++;
            amountOfEventsByDay[event.finish + 1]--;
        }

        int result = amountOfEventsByDay[0];
        for (int i = 1; i < amountOfEventsByDay.length; i++) {
            amountOfEventsByDay[i] += amountOfEventsByDay[i - 1];
            result = Math.max(result, amountOfEventsByDay[i]);
        }

        return result;
    }

    @EpiUserType(ctorParams = {int.class, int.class})
    public static class Event {
        public int start, finish;

        public Event(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    private static class Endpoint {
        public int time;
        public boolean isStart;

        Endpoint(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
