package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Poll implements Pollable {
    private final String title;
    private final String startDate;
    private String endDate;
    private final Map<String, Integer> options = new HashMap<>();
    private final Set<String> voters = new HashSet<>();

    public Poll(String title, String startDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = null;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isActive() {
        return endDate == null;
    }

    @Override
    public void endPoll(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public String getEndDate() {
        return endDate;
    }

    @Override
    public void addOption(String option) {
        options.put(option, 0);
    }

    @Override
    public void addOption(String option, int votes) {
        options.put(option, votes);
    }

    @Override
    public Map<String, Integer> getResults() {
        return options;
    }

    @Override
    public boolean hasUserVoted(String username) {
        return voters.contains(username);
    }

    @Override
    public boolean castVote(String username, String option) {
        if (voters.contains(username) || !options.containsKey(option)) {
            return false;
        }
        options.put(option, options.get(option) + 1);
        voters.add(username);
        return true;
    }

    @Override
    public String toString() {
        String status = isActive() ? "Active" : "Ended";
        StringBuilder sb = new StringBuilder();
        sb.append(title).append("|").append(startDate).append("|");
        sb.append(status).append("|");
        options.forEach((option, votes) -> sb.append(option).append(":").append(votes).append("|"));
        return sb.toString();
    }
}
