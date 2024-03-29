package com.targetindia.pack1;

public class Staff extends Person {
    private String school;
    private double salary;

    public Staff(String name, String address, String school, double salary) {
        super(name, address);
        this.school = school;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Staff [name=" + name + ", address=" + address + ", schoolName=" + school + ", salary=" + salary + "]";
    }

    public String getSchool() {
        return school;
    }

    public void setSchoolName(String school) {
        this.school = school;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

