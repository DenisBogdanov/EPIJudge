package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalendarRendering {

    @EpiTest(testDataFile = "calendar_rendering.tsv")
    public static int findMaxSimultaneousEvents(List<Event> events) {
        List<EventEntry> eventEntries = new ArrayList<>();
        for (var event : events) {
            eventEntries.add(new EventEntry(event.start, true));
            eventEntries.add(new EventEntry(event.finish, false));
        }
        eventEntries.sort((e1, e2) -> {
            if (e1.time == e2.time) return e1.isStart ? -1 : 1;
            return e1.time - e2.time;
        });
        int ans = 0;
        int curr = 0;
        for (var eventEntry : eventEntries) {
            if (eventEntry.isStart) {
                curr++;
                ans = Math.max(ans, curr);
            } else {
                curr--;
            }
        }
        return ans;
    }

    private record EventEntry(int time, boolean isStart) {}

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
