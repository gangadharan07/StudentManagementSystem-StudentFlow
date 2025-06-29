package dao;

import db.DBConnection;
import model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // Get course ID by its name; returns -1 if not found
    public int getCourseIdByName(String name) {
        String query = "SELECT id FROM courses WHERE name = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, name);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Get full Course (including fee) by ID; returns null if not found
    public Course getCourseById(int id) {
        String query = "SELECT * FROM courses WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("fee"),
                        rs.getInt("department_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Get course name by its ID; returns null if not found
    public String getCourseNameById(int id) {
        String query = "SELECT name FROM courses WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // List all courses
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String query = "SELECT * FROM courses";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Course(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("fee"),
                    rs.getInt("department_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
