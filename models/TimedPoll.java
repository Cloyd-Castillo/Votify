package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimedPoll extends Poll {
    private final int durationDays;

    public TimedPoll(String title, String startDate, int durationDays) {
        super(title, startDate);
        this.durationDays = durationDays;
    }

    public LocalDate getPlannedEndDate() {
        LocalDate start = LocalDate.parse(getStartDate());
        return start.plusDays(durationDays);
    }

    public String getPlannedEndDateTime() {
        LocalDateTime plannedEndDateTime = getPlannedEndDate().atStartOfDay();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm a");
        return plannedEndDateTime.format(formatter);
    }

    @Override
    public boolean isActive() {
        return getEndDate() == null;
    }

    @Override
    public void endPoll(String endDate) {
        if (isActive()) {
            super.endPoll(endDate);
        } else {
            System.out.println("Poll has already been ended.");
        }
    }

    @Override
    public String toString() {
        String status = isActive() ? "Active" : "Ended";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return super.toString() + " (Timed Poll: " + durationDays + " days, Planned End: " + getPlannedEndDate().format(formatter) + ", Status: " + status + ")";
    }
}
