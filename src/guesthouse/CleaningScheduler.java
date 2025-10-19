package guesthouse;

import java.time.LocalDate;

/*
 * Another subsystem: cleaning team needs the date.
 * I only print to console to keep it simple.
 */
public class CleaningScheduler {
    public void schedule(LocalDate date, RoomType type) {
        System.out.println("Cleaning scheduled on " + date + " for " + type);
    }
    public void cancel(LocalDate date, RoomType type) {
        System.out.println("Cleaning canceled on " + date + " for " + type);
    }
}
