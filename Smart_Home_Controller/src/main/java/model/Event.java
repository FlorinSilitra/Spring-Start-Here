package model;

import java.time.LocalDateTime;

public class Event {

    private LocalDateTime timestamp;

    // True = device ON, False = device OFF
    private boolean active;

    public Event() {
    }

    public Event(LocalDateTime timestamp, boolean active) {
        this.timestamp = timestamp;
        this.active = active;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Event{" +
                "timestamp=" + timestamp +
                ", active=" + active +
                '}';
    }
}
