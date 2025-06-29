package service;

import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import model.Course;
import model.Department;
import model.Student;
import utils.InputValidator;

import java.util.List;
import java.util.Scanner;

public class StudentService {
    private final StudentDAO dao = new StudentDAO();
    private final DepartmentDAO deptDAO = new DepartmentDAO();
    private final CourseDAO courseDAO = new CourseDAO();
    private final Scanner scanner = new Scanner(System.in);

    // Add new student
    public void addStudent() {
        String name;
        int deptId, courseId, m1, m2, m3;

        do {
            System.out.print("Enter student name: ");
            name = scanner.nextLine();
            if (!InputValidator.isValidName(name)) {
                System.out.println("❌ Invalid name.");
            }
        } while (!InputValidator.isValidName(name));

        // Show departments
        List<Department> departments = deptDAO.getAllDepartments();
        System.out.println("Departments:");
        for (Department d : departments) {
            System.out.println(d.getId() + ". " + d.getName());
        }
        while (true) {
            System.out.print("Enter department ID: ");
            try {
                deptId = Integer.parseInt(scanner.nextLine());
                if (deptDAO.getDepartmentById(deptId) != null) break;
                System.out.println("❌ Invalid department ID.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Enter a valid number.");
            }
        }

        // Show courses
        List<Course> courses = courseDAO.getAllCourses();
        System.out.println("Courses:");
        for (Course c : courses) {
            System.out.println(c.getId() + ". " + c.getName());
        }
        while (true) {
            System.out.print("Enter course ID: ");
            try {
                courseId = Integer.parseInt(scanner.nextLine());
                if (courseDAO.getCourseById(courseId) != null) break;
                System.out.println("❌ Invalid course ID.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Enter a valid number.");
            }
        }

        m1 = readValidMark("Enter mark1: ");
        m2 = readValidMark("Enter mark2: ");
        m3 = readValidMark("Enter mark3: ");

        Student student = new Student(name, deptId, courseId, m1, m2, m3);
        if (dao.addStudent(student)) {
            System.out.println("✅ Student added successfully!");
        } else {
            System.out.println("❌ Error adding student.");
        }
    }

    // Helper method
    private int readValidMark(String prompt) {
        int mark;
        while (true) {
            try {
                System.out.print(prompt);
                mark = Integer.parseInt(scanner.nextLine());
                if (InputValidator.isValidMark(mark)) return mark;
                System.out.println("❌ Mark must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Enter a number.");
            }
        }
    }

    // View all students
    public void viewAllStudents() {
        List<Student> list = dao.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.printf("%-5s %-20s %-15s %-15s %-6s %-6s %-6s %-6s%n", 
                "ID", "Name", "Department", "Course", "M1", "M2", "M3", "Total");

        for (Student s : list) {
            String deptName = deptDAO.getDepartmentNameById(s.getDepartmentId());
            String courseName = courseDAO.getCourseNameById(s.getCourseId());
            System.out.printf("%-5d %-20s %-15s %-15s %-6d %-6d %-6d %-6d%n",
                    s.getId(), s.getName(), deptName, courseName,
                    s.getMark1(), s.getMark2(), s.getMark3(), s.getTotalMarks());
        }
    }

    // Update student
    public void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        Student existing = dao.getStudentById(id);
        if (existing == null) {
            System.out.println("❌ Student not found.");
            return;
        }

        System.out.print("New name [" + existing.getName() + "]: ");
        String name = scanner.nextLine();
        if (!InputValidator.isValidName(name)) name = existing.getName();

        viewAllDepartments();
        System.out.print("New Department ID [" + existing.getDepartmentId() + "]: ");
        String deptInput = scanner.nextLine();
        int deptId = deptInput.isEmpty() ? existing.getDepartmentId() : Integer.parseInt(deptInput);

        viewAllCourses();
        System.out.print("New Course ID [" + existing.getCourseId() + "]: ");
        String courseInput = scanner.nextLine();
        int courseId = courseInput.isEmpty() ? existing.getCourseId() : Integer.parseInt(courseInput);

        int m1 = parseValidMark("New Mark1 [" + existing.getMark1() + "]: ", existing.getMark1());
        int m2 = parseValidMark("New Mark2 [" + existing.getMark2() + "]: ", existing.getMark2());
        int m3 = parseValidMark("New Mark3 [" + existing.getMark3() + "]: ", existing.getMark3());

        Student updated = new Student(id, name, deptId, courseId, m1, m2, m3);
        if (dao.updateStudent(updated)) {
            System.out.println("✅ Student updated successfully.");
        } else {
            System.out.println("❌ Update failed.");
        }
    }

    private int parseValidMark(String prompt, int fallback) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        try {
            int mark = Integer.parseInt(input);
            return InputValidator.isValidMark(mark) ? mark : fallback;
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    private void viewAllDepartments() {
        for (Department d : deptDAO.getAllDepartments()) {
            System.out.println(d.getId() + ". " + d.getName());
        }
    }

    private void viewAllCourses() {
        for (Course c : courseDAO.getAllCourses()) {
            System.out.println(c.getId() + ". " + c.getName());
        }
    }

    // Delete student
    public void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (dao.deleteStudent(id)) {
            System.out.println("✅ Deleted successfully.");
        } else {
            System.out.println("❌ Deletion failed.");
        }
    }

    // Search by ID
    public void searchById() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Student s = dao.getStudentById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        printStudentDetails(s);
    }

    // Search by name
    public void searchByName() {
        System.out.print("Enter name keyword: ");
        String name = scanner.nextLine();
        List<Student> list = dao.searchByName(name);
        if (list.isEmpty()) {
            System.out.println("No results.");
        } else {
            list.forEach(this::printStudentDetails);
        }
    }

    // Search by department
    public void searchByDepartment() {
        viewAllDepartments();
        System.out.print("Enter department ID: ");
        int deptId = Integer.parseInt(scanner.nextLine());
        List<Student> list = dao.searchByDepartmentId(deptId);
        if (list.isEmpty()) {
            System.out.println("No results.");
        } else {
            list.forEach(this::printStudentDetails);
        }
    }

    // Total students
    public void reportTotalStudents() {
        System.out.println("Total number of students: " + dao.getTotalStudents());
    }

    // Average by department
    public void reportAvgMarksByDepartment() {
        List<String> report = dao.getAverageMarksByDepartment();
        report.forEach(System.out::println);
    }

    // Top performers
    public void reportTopStudents() {
        List<Student> topList = dao.getTopStudents();
        System.out.println("Top Performing Students:");
        topList.forEach(this::printStudentDetails);
    }

    // Utility
    private void printStudentDetails(Student s) {
        String deptName = deptDAO.getDepartmentNameById(s.getDepartmentId());
        String courseName = courseDAO.getCourseNameById(s.getCourseId());
        System.out.println("ID: " + s.getId());
        System.out.println("Name: " + s.getName());
        System.out.println("Department: " + deptName);
        System.out.println("Course: " + courseName);
        System.out.println("Marks: " + s.getMark1() + ", " + s.getMark2() + ", " + s.getMark3());
        System.out.println("Total: " + s.getTotalMarks());
        System.out.println("Average: " + s.getAverageMarks());
        System.out.println("---------------------------");
    }
}
