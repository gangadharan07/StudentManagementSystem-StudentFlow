package file;

import dao.CourseDAO;
import dao.DepartmentDAO;
import dao.StudentDAO;
import model.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileExporter {

    private static final String FILE_DIRECTORY = "file";
    private static final String FILE_PATH = FILE_DIRECTORY + "/students_backup.txt";

    public static void exportToFile() {
        StudentDAO studentDAO = new StudentDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        CourseDAO courseDAO = new CourseDAO();

        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found to export.");
            return;
        }

        try {
            // Create the 'file/' directory if it doesn't exist
            File dir = new File(FILE_DIRECTORY);
            if (!dir.exists()) {
                boolean created = dir.mkdir();
                if (!created) {
                    System.out.println("❌ Failed to create directory: " + FILE_DIRECTORY);
                    return;
                }
            }

            // Write student data to file
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            writer.write("ID\tName\tDepartment\tCourse\tMark1\tMark2\tMark3\tTotal\n");

            for (Student s : students) {
                String deptName = departmentDAO.getDepartmentNameById(s.getDepartmentId());
                String courseName = courseDAO.getCourseNameById(s.getCourseId());

                String line = s.getId() + "\t" + s.getName() + "\t" + deptName + "\t" + courseName + "\t"
                        + s.getMark1() + "\t" + s.getMark2() + "\t" + s.getMark3() + "\t"
                        + s.getTotalMarks();
                writer.write(line + "\n");
            }

            writer.close();
            System.out.println("✅ Student data exported successfully to " + FILE_PATH);

        } catch (IOException e) {
            System.out.println("❌ Error writing to file:");
            e.printStackTrace();
        }
    }
}
