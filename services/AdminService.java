package services;

import models.Poll;
import models.TimedPoll;
import utils.FileManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "adminpass";
    private final List<Poll> polls = new ArrayList<>();

    public void adminLogin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            System.out.println("Admin login successful!");
            adminMenu(scanner);
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private void adminMenu(Scanner scanner) {
        loadPollsFromFile();

        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Create a Poll");
            System.out.println("2. View Poll Results");
            System.out.println("3. End a Poll");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1 -> createPoll(scanner);
                case 2 -> viewPollResults(scanner);
                case 3 -> endPoll(scanner);
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void createPoll(Scanner scanner) {
        System.out.print("Enter the title of the poll: ");
        String title = scanner.nextLine();
        String startDate = LocalDate.now().toString();

        System.out.println("Is this a timed poll? (yes/no)");
        String timedResponse = scanner.nextLine();
        Poll poll;

        if ("yes".equalsIgnoreCase(timedResponse)) {
            System.out.print("Enter duration in days: ");
            int duration = getIntInput(scanner);
            poll = new TimedPoll(title, startDate, duration);
        } else {
            poll = new Poll(title, startDate);
        }

        System.out.println("Enter the options for the poll. Type 'done' to finish:");
        while (true) {
            System.out.print("Option: ");
            String option = scanner.nextLine();
            if ("done".equalsIgnoreCase(option)) {
                break;
            }
            poll.addOption(option);
        }

        polls.add(poll);
        savePollsToFile();
        System.out.println("Poll created successfully!");
    }

    private void viewPollResults(Scanner scanner) {
        List<Poll> activePolls = polls.stream().filter(Poll::isActive).toList();
        if (activePolls.isEmpty()) {
            System.out.println("No active polls available.");
            return;
        }
    
        System.out.println("\nAvailable Active Polls:");
        for (int i = 0; i < activePolls.size(); i++) {
            Poll poll = activePolls.get(i);
            System.out.printf("%d. %s (Status: %s)\n", i + 1, poll.getTitle(), poll.isActive() ? "Active" : "Ended");
        }
    
        System.out.print("Select a poll to view results (or type 0 to cancel): ");
        int choice = getIntInput(scanner) - 1;
    
        if (choice >= 0 && choice < activePolls.size()) {
            Poll poll = activePolls.get(choice);
            System.out.println("\nResults for " + poll.getTitle() + ":");
            poll.getResults().forEach((option, votes) -> System.out.printf("%s: %d votes\n", option, votes));
        } else if (choice != -1) {
            System.out.println("Invalid selection. Try again.");
        }
    }    

    private void endPoll(Scanner scanner) {
        List<Poll> activePolls = polls.stream().filter(Poll::isActive).toList();
        if (activePolls.isEmpty()) {
            System.out.println("No active polls available.");
            return;
        }
    
        System.out.println("\nAvailable Active Polls:");
        for (int i = 0; i < activePolls.size(); i++) {
            Poll poll = activePolls.get(i);
            System.out.printf("%d. %s\n", i + 1, poll.getTitle());
        }
    
        System.out.print("Select a poll to end (or type 0 to cancel): ");
        int choice = getIntInput(scanner) - 1;
    
        if (choice >= 0 && choice < activePolls.size()) {
            Poll poll = activePolls.get(choice);
    
            // Warning if the poll is a TimedPoll
            if (poll instanceof TimedPoll && poll.isActive()) {
                System.out.println("You are trying to end a timed poll!! Do you want to force it to end? (yes/no)");
                String response = scanner.nextLine();
                if (!"yes".equalsIgnoreCase(response)) {
                    System.out.println("Poll end aborted.");
                    return;
                }
            }
    
            poll.endPoll(LocalDate.now().toString());
            savePollsToFile();
            System.out.println("Poll ended successfully!");
            System.out.println("\nFinal Results for " + poll.getTitle() + ":");
            poll.getResults().forEach((option, votes) -> System.out.printf("%s: %d votes\n", option, votes));
        } else if (choice != -1) {
            System.out.println("Invalid selection. Try again.");
        }
    }    
    

    private void loadPollsFromFile() {
        List<String> lines = FileManager.readFile("polls.txt");
        polls.clear();

        for (String line : lines) {
            String[] parts = line.split("\\|");
            Poll poll = new Poll(parts[0], parts[1]);

            if (!"active".equals(parts[2])) {
                poll.endPoll(parts[2]); // Set end date if poll is not active
            }

            for (int i = 3; i < parts.length; i++) {
                String[] optionVotes = parts[i].split(":");
                String option = optionVotes[0];
                int votes = Integer.parseInt(optionVotes[1]);
                poll.addOption(option, votes);
            }

            polls.add(poll);
        }
    }

    private void savePollsToFile() {
        FileManager.savePollsToFile(polls);
    }

    private int getIntInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
