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

    public LocalDate getExpirationDate() {
        LocalDate start = LocalDate.parse(getStartDate());
        return start.plusDays(durationDays);
    }

    public String getEndDateTime() {
        LocalDateTime endDateTime = getExpirationDate().atStartOfDay();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm a");
        return endDateTime.format(formatter);
    }

    @Override
    public boolean isActive() {
        if (getEndDate() != null) {
            return false;
        }

        LocalDate now = LocalDate.now();
        return now.isBefore(getExpirationDate());
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return super.toString() + " (Timed Poll: " + durationDays + " days, Expires on: " + getExpirationDate().format(formatter) + ", Status: " + status + ")";
    }
}
