package model;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final String pesel;
    private final Department department;
    private final double salary;

    public Employee(String firstName, String lastName, String pesel, Department department, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.department = department;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }
}
