package model;

public class Student extends Person {
    private int id;
    private int departmentId;
    private int courseId;
    private int mark1, mark2, mark3;

    // Constructor with ID (for existing students)
    public Student(int id, String name, int departmentId, int courseId, int mark1, int mark2, int mark3) {
        super(name); // Only pass name
        this.id = id;
        this.departmentId = departmentId;
        this.courseId = courseId;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    // Constructor without ID (for new students)
    public Student(String name, int departmentId, int courseId, int mark1, int mark2, int mark3) {
        super(name); // Only pass name
        this.departmentId = departmentId;
        this.courseId = courseId;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    public int getId() {
        return id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getMark1() {
        return mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public int getMark3() {
        return mark3;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }

    public int getTotalMarks() {
        return mark1 + mark2 + mark3;
    }

    public double getAverageMarks() {
        return (mark1 + mark2 + mark3) / 3.0;
    }
}
