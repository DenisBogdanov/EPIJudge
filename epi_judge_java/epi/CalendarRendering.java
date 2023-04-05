package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class CalendarRendering {

    @EpiTest(testDataFile = "calendar_rendering.tsv")
    public static int findMaxSimultaneousEvents(List<Event> events) {
        if (events == null || events.isEmpty()) return 0;
        int maxTime = events.stream().mapToInt(e -> e.finish).max().getAsInt();
        int[] concurrentEvents = new int[maxTime + 2];
        for (Event event : events) {
            concurrentEvents[event.start]++;
            concurrentEvents[event.finish + 1]--;
        }

        for (int i = 1; i < concurrentEvents.length; i++) {
            concurrentEvents[i] += concurrentEvents[i - 1];
        }

        int result = 0;
        for (int concurrentEvent : concurrentEvents) {
            result = Math.max(result, concurrentEvent);
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
