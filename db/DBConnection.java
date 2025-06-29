package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root"; 
    private static final String PASSWORD = "Gangadharan@kgcas"; 

    // ✅ This is the method that must be defined
    public static Connection getConnection() {
        try {
            // Load the MySQL JDBC Driver (optional in newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed:");
            e.printStackTrace();
            return null;
        }
    }
}
