
package solutions.test_framework;

public class TimeoutException extends Exception {
  private solutions.test_framework.TestTimer timer;

  TimeoutException(long timeoutSeconds) {
    timer = new solutions.test_framework.TestTimer(timeoutSeconds);
  }

  TestTimer getTimer() { return timer; }
}
