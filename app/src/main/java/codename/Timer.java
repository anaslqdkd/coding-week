package codename;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Timer {

  private Timeline timeline;
  private int seconds;
  private TimerCallback callback;

  public Timer(TimerCallback callback) {
    this.callback = callback;
    this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
    this.timeline.setCycleCount(Timeline.INDEFINITE);
    this.seconds = 0;
  }

  public void start() {
    this.seconds = 0; // Reset timer
    this.timeline.play(); // Start the timer
  }

  public void stop() {
    this.timeline.stop(); // Stop the timer
  }

  public void reset() {
    this.seconds = 0; // Reset the timer
    updateTimer(); // Immediately update the time
  }

  private void updateTimer() {
    this.seconds++; // Increment seconds
    int minutes = seconds / 60;
    int remainingSeconds = seconds % 60;

    // Call the callback to update the timer display or perform other actions
    if (callback != null) {
      callback.onTimeUpdate(minutes, remainingSeconds);
    }

    // Check if time is up, or other logic
    // For example, switch teams after a certain time limit
    if (seconds >= 60) {
      // Example: switchTeam();
    }
  }

  public interface TimerCallback {
    void onTimeUpdate(int minutes, int seconds);
  }
}
