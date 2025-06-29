import auth.LoginSystem;
import file.FileExporter;
import model.User;
import service.StudentService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoginSystem login = new LoginSystem();
        User currentUser = login.authenticate();  // now returns User
        if (currentUser == null) {
            return;  // Exit if login fails
        }

        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();
        int choice;

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("Logged in as: " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student Details");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Search Student by Name");
            System.out.println("7. Search Student by Department");
            System.out.println("8. Report - Total Number of Students");
            System.out.println("9. Report - Average Marks by Department");
            System.out.println("10. Report - List of Toppers");
            System.out.println("11. Export Student Data to File");
            System.out.println("12. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> service.addStudent();
                    case 2 -> service.viewAllStudents();
                    case 3 -> service.updateStudent();
                    case 4 -> service.deleteStudent();
                    case 5 -> service.searchById();
                    case 6 -> service.searchByName();
                    case 7 -> service.searchByDepartment();
                    case 8 -> service.reportTotalStudents();
                    case 9 -> service.reportAvgMarksByDepartment();
                    case 10 -> service.reportTopStudents();
                    case 11 -> FileExporter.exportToFile();
                    case 12 -> {
                        System.out.println("Exiting the system... Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }
}
