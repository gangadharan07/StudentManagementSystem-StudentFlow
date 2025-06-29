package model;

public class Course {
    private int id;
    private String name;
    private double fee;
    private int departmentId;

    public Course(int id, String name, double fee, int departmentId) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.departmentId = departmentId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getFee() { return fee; }
    public int getDepartmentId() { return departmentId; }

    public void setName(String name) { this.name = name; }
    public void setFee(double fee) { this.fee = fee; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
}
