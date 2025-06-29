package auth;

import dao.UserDAO;
import model.User;

import java.util.Scanner;

public class LoginSystem {

    public User authenticate() {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("===== Login =====");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userDAO.login(username, password);

        if (user != null) {
            System.out.println("✅ Login successful. Welcome, " + user.getUsername() + "!");
            System.out.println("Logged in as: " + user.getRole().toUpperCase());
            return user; // Return full user object
        } else {
            System.out.println("❌ Invalid credentials. Please try again.");
            return null; // Return null on failure
        }
    }
}
