import services.UserService;
import services.AdminService;

import java.util.Scanner;

public class Votify {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        AdminService adminService = new AdminService();

        while (true) {
            System.out.println("\n--- Votify Main Menu ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1 -> userService.registerUser(scanner);
                case 2 -> userService.login(scanner);
                case 3 -> adminService.adminLogin(scanner);
                case 4 -> {
                    System.out.println("Thank you for using Votify!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int getIntInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
