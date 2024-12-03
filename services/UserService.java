package services;

import models.Poll;
import utils.FileManager;

import java.util.*;

public class UserService {
    private final List<Poll> polls = new ArrayList<>();
    private final Map<String, Set<String>> userVotes = new HashMap<>(); // Tracks polls each user has voted on
    private String loggedInUser;

    public void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        String password;
        while (true) {
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            if (password.length() < 8) {
                System.out.println("Password must be at least 8 characters long. Please try again.");
                continue;
            }
            break;
        }

        List<String> users = FileManager.readFile("users.txt");

        for (String user : users) {
            String[] credentials = user.split("\\|");
            if (credentials[0].equals(username)) {
                System.out.println("Username already exists. Please choose another username.");
                return;
            }
        }

        FileManager.appendToFile("users.txt", List.of(username + "|" + password));
        System.out.println("User registered successfully!");
    }

    public void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        List<String> users = FileManager.readFile("users.txt");

        for (String user : users) {
            String[] credentials = user.split("\\|");
            if (credentials[0].equals(username) && credentials[1].equals(password)) {
                System.out.println("Login successful!");
                loggedInUser = username;
                userMenu(scanner);
                return;
            }
        }

        System.out.println("Invalid username or password.");
    }

    private void userMenu(Scanner scanner) {
        loadPollsFromFile();

        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Vote in a Poll");
            System.out.println("2. View Poll Results");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1 -> voteInPoll(scanner);
                case 2 -> viewPollResults(scanner);
                case 3 -> {
                    loggedInUser = null;
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void voteInPoll(Scanner scanner) {
        List<Poll> activePolls = new ArrayList<>();
        for (Poll poll : polls) {
            if (poll.isActive()) {
                activePolls.add(poll);
            }
        }

        if (activePolls.isEmpty()) {
            System.out.println("No active polls available.");
            return;
        }

        System.out.println("\nAvailable Active Polls:");
        for (int i = 0; i < activePolls.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, activePolls.get(i).getTitle());
        }

        System.out.print("Select a poll to vote in (or type 0 to cancel): ");
        int choice = getIntInput(scanner) - 1;

        if (choice >= 0 && choice < activePolls.size()) {
            Poll poll = activePolls.get(choice);
            userVotes.putIfAbsent(loggedInUser, new HashSet<>());

            if (userVotes.get(loggedInUser).contains(poll.getTitle())) {
                System.out.println("You have already voted in this poll.");
                return;
            }

            System.out.println("Options:");
            poll.getResults().keySet().forEach(System.out::println);
            System.out.print("Enter your vote: ");
            String vote = scanner.nextLine();

            if (poll.castVote(loggedInUser, vote)) {
                userVotes.get(loggedInUser).add(poll.getTitle());
                savePollsToFile();
                System.out.println("Vote registered!");
            } else {
                System.out.println("Invalid option or vote not registered.");
            }
        } else if (choice != -1) {
            System.out.println("Invalid selection. Try again.");
        }
    }

    private void viewPollResults(Scanner scanner) {
        System.out.println("\nPoll Results (for ended polls):");
        for (Poll poll : polls) {
            if (!poll.isActive()) {
                System.out.println(poll.getTitle() + ":");
                poll.getResults().forEach((option, votes) -> System.out.printf("%s: %d votes\n", option, votes));
            }
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
