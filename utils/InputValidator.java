package utils;

public class InputValidator {

    // Validate student name: only letters and spaces, min 2 characters
    public static boolean isValidName(String name) {
        return name != null && name.matches("[A-Za-z ]{2,}");
    }

    // Validate department: not null or empty
    public static boolean isValidDepartment(String dept) {
        return dept != null && !dept.trim().isEmpty();
    }

    // Validate marks: must be between 0 and 100
    public static boolean isValidMark(int mark) {
        return mark >= 0 && mark <= 100;
    }
}
	