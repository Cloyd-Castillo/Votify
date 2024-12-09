package models;

import java.util.Map;

public interface Pollable {
    String getTitle();
    boolean isActive();
    void endPoll(String endDate);
    String getStartDate();
    String getEndDate();
    void addOption(String option);
    void addOption(String option, int votes);
    Map<String, Integer> getResults();
    boolean hasUserVoted(String username);
    boolean castVote(String username, String option);
}
