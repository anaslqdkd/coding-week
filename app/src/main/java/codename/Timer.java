package codename;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Timer {

  private Timeline timeline;
  private int seconds;
  private int initialSeconds;
  private TimerCallback callback;

  public Timer(int initialSeconds, TimerCallback callback) {
    this.initialSeconds = initialSeconds;
    this.seconds = initialSeconds; // Set initial time
    this.callback = callback;

    this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
    this.timeline.setCycleCount(Timeline.INDEFINITE); // Keep running indefinitely
  }

  public void start() {
    this.seconds = initialSeconds; // Reset timer to initial time
    this.timeline.play(); // Start the countdown
  }

  public void stop() {
    this.timeline.stop(); // Stop the countdown timer
  }

  public boolean isTimeUp() {
    return this.seconds <= 0;
  }

  public void reset() {
    this.seconds = initialSeconds; // Reset the timer to initial time
    updateTimer(); // Immediately update the time
  }

  private void updateTimer() {
    this.seconds--; // Decrement seconds
    int minutes = seconds / 60;
    int remainingSeconds = seconds % 60;

    // Call the callback to update the timer display or perform other actions
    if (callback != null) {
      callback.onTimeUpdate(minutes, remainingSeconds);
    }

    // Check if time is up
    if (seconds <= 0) {
      // Call time-up logic (e.g., switch teams, end round)
      stop();
      callback.onTimeUp();
    }
  }

  // Interface for the callback to update timer and handle time up
  public interface TimerCallback {
    void onTimeUpdate(int minutes, int seconds);

    void onTimeUp(); // Called when the timer reaches 0
  }
}
