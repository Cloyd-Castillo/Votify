package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimedPoll extends Poll {
    private final int durationDays; // Duration in days

    public TimedPoll(String title, String startDate, int durationDays) {
        super(title, startDate);
        this.durationDays = durationDays;
    }

    @Override
    public boolean isActive() {
        if (getEndDate() != null) {
            return false;
        }

        // Calculate the poll's expiration based on the duration
        LocalDate start = LocalDate.parse(getStartDate());
        LocalDate now = LocalDate.now();
        long elapsedDays = ChronoUnit.DAYS.between(start, now);
        return elapsedDays < durationDays;
    }

    @Override
    public void endPoll(String endDate) {
        if (isActive()) {
            super.endPoll(endDate);
        } else {
            System.out.println("Poll has already expired automatically.");
        }
    }

    @Override
    public String toString() {
        String status = isActive() ? "Active" : "Ended (Auto-expired)";
        return super.toString() + " (Timed Poll: " + durationDays + " days, Status: " + status + ")";
    }

}
