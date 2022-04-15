package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class CalendarRendering {

  @EpiTest(testDataFile = "calendar_rendering.tsv")
  public static int findMaxSimultaneousEvents(List<Event> list) {
    List<Endpoint> endpoints = new ArrayList<>(list.size() * 2);
    for (Event event : list) {
      endpoints.add(new Endpoint(event.start, true));
      endpoints.add(new Endpoint(event.finish, false));
    }

    endpoints.sort((e1, e2) -> {
      if (e1.time != e2.time) {
        return Integer.compare(e1.time, e2.time);
      }

      return e1.isStart && !e2.isStart ? -1 : !e1.isStart && e2.isStart ? 1 : 0;
    });

    int result = 0;
    int currentIntersection = 0;

    for (Endpoint endpoint : endpoints) {
      if (endpoint.isStart) {
        currentIntersection++;
        result = Math.max(result, currentIntersection);
      } else {
        currentIntersection--;
      }
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
