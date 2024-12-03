package utils;

import models.Poll;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {
    public static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    public static void writeFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void appendToFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    public static void savePollsToFile(List<Poll> polls) {
        List<String> lines = new ArrayList<>();
        for (Poll poll : polls) {
            StringBuilder pollData = new StringBuilder(poll.getTitle())
                    .append("|").append(poll.getStartDate())
                    .append("|").append(poll.isActive() ? "active" : poll.getEndDate());
            poll.getResults().forEach((option, votes) -> pollData.append("|").append(option).append(":").append(votes));
            lines.add(pollData.toString());
        }
        writeFile("polls.txt", lines);
    }

    public static void saveUserVotesToFile(Map<String, List<String>> userVotes) {
        List<String> lines = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : userVotes.entrySet()) {
            String username = entry.getKey();
            String votes = String.join(",", entry.getValue());
            lines.add(username + "|" + votes);
        }
        writeFile("uservotes.txt", lines);
    }

    public static Map<String, List<String>> loadUserVotesFromFile() {
        Map<String, List<String>> userVotes = new HashMap<>();
        List<String> lines = readFile("uservotes.txt");
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length == 2) {
                String username = parts[0];
                String[] votedPolls = parts[1].split(",");
                userVotes.put(username, new ArrayList<>(List.of(votedPolls)));
            }
        }
        return userVotes;
    }
}
