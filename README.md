# 🎓 Student Management System

A Java-based console application to manage student academic data using a MySQL backend. The system supports student CRUD operations, role-based login, department and course normalization, report generation, and file export functionality.

---

## 📂 Folder Structure

src/
├── auth/ # Login & authentication logic
├── dao/ # Data Access Objects for DB interaction
├── db/ # Database connection class
├── file/ # File export logic
├── model/ # Java Bean classes for entities
├── service/ # Business logic (StudentService, etc.)
├── utils/ # Utility classes (validation, etc.)
└── Main.java # Entry point of the application

---

## 🧱 Modules Description

- **Login Module**: Supports admin/user role-based login with authentication.
- **Student Module**: Add, update, delete, search, and view student data.
- **Department & Course Modules**: Normalized department and course tables with relational integrity.
- **Reporting Module**: 
  - Total number of students
  - Average marks per department
  - Top 3 performing students
- **File Export Module**: Exports student data to a local `.txt` file.

---

## 🧩 Technologies Used

- **Java (Core + JDBC)**
- **MySQL Database**
- **JDBC Driver**
- **OOP (Inheritance, Encapsulation)**
- **Console I/O**
- **SQL Joins & Queries**

---

## 🧮 Database Schema

### 🔸 `departments` Table

| Field | Type | Constraints |
|-------|------|-------------|
| id    | INT  | PRIMARY KEY, AUTO_INCREMENT |
| name  | VARCHAR(100) | NOT NULL |

### 🔸 `courses` Table

| Field         | Type        | Constraints |
|---------------|-------------|-------------|
| id            | INT         | PRIMARY KEY, AUTO_INCREMENT |
| name          | VARCHAR(100)| NOT NULL |
| fee           | DECIMAL(10,2)| NOT NULL |
| department_id | INT         | FOREIGN KEY → departments(id) |

### 🔸 `students` Table

| Field         | Type         | Constraints |
|---------------|--------------|-------------|
| id            | INT          | PRIMARY KEY, AUTO_INCREMENT |
| name          | VARCHAR(100) | NOT NULL |
| department_id | INT          | FOREIGN KEY → departments(id) |
| course_id     | INT          | FOREIGN KEY → courses(id) |
| mark1         | INT          | CHECK (0–100) |
| mark2         | INT          | CHECK (0–100) |
| mark3         | INT          | CHECK (0–100) |

### 🔸 `users` Table

| Field     | Type         | Constraints |
|-----------|--------------|-------------|
| id        | INT          | PRIMARY KEY, AUTO_INCREMENT |
| username  | VARCHAR(100) | UNIQUE, NOT NULL |
| password  | VARCHAR(100) | NOT NULL |
| role      | VARCHAR(20)  | e.g., 'admin', 'user' |

---

## 📈 SQL Concepts Demonstrated

- `WHERE`, `LIKE`, `ORDER BY`, `GROUP BY`, `JOIN`, `LIMIT`
- Aggregates: `COUNT()`, `AVG()`
- Pattern matching using `%...%`
- Prepared statements for security

---

## 💡 Java Concepts Demonstrated

- **OOP Principles**: Inheritance (`Student` → `Person`)
- **Collections**: `ArrayList`, `List` interface
- **Exception Handling**: `try-catch-finally`
- **File I/O**: `BufferedWriter`, `FileWriter`
- **Database Connectivity**: `Connection`, `PreparedStatement`, `ResultSet`

---

## 📤 File Export Feature

Exports all student records to a text file.
