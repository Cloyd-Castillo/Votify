package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Poll {
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

    public String getTitle() {
        return title;
    }

    public boolean isActive() {
        return endDate == null;
    }

    public void endPoll(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void addOption(String option) {
        options.put(option, 0);
    }

    public void addOption(String option, int votes) {
        options.put(option, votes);
    }

    public Map<String, Integer> getResults() {
        return options;
    }

    public boolean hasUserVoted(String username) {
        return voters.contains(username);
    }

    public boolean castVote(String username, String option) {
        if (voters.contains(username)) {
            return false;
        }
        if (!options.containsKey(option)) {
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
