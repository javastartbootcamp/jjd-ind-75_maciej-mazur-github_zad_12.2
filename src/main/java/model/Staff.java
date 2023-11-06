package model;

import java.util.Arrays;

public class Staff {
    private static final int INITIAL_EMPLOYEES_SIZE = 10;

    private Employee[] employees = new Employee[INITIAL_EMPLOYEES_SIZE];
    private int currentNumberOfEmployees;
    private int numberOfItEmployees;
    private int numberOfManagementEmployees;
    private int numberOfSupportEmployees;
    private double maxSalary;
    private double minSalary;
    private double totalSalary;
    private boolean hasAtLeastOneSalaryAdded;

    public void addEmployee(Employee employee) {
        if (currentNumberOfEmployees >= employees.length) {
            extendEmployeesArray();
        }
        employees[currentNumberOfEmployees++] = employee;
        updateStaffStatistics(employee);
    }

    private void updateStaffStatistics(Employee employee) {
        if (!hasAtLeastOneSalaryAdded) {
            minSalary = employee.getSalary();
            hasAtLeastOneSalaryAdded = true;
        } else {
            minSalary = Math.min(employee.getSalary(), minSalary);
        }

        maxSalary = Math.max(employee.getSalary(), maxSalary);
        totalSalary += employee.getSalary();

        switch (employee.getDepartment()) {
            case IT -> numberOfItEmployees++;
            case MANAGEMENT -> numberOfManagementEmployees++;
            case SUPPORT -> numberOfSupportEmployees++;
            default -> { }
        }
    }

    private void extendEmployeesArray() {
        employees = Arrays.copyOf(employees, (employees.length + INITIAL_EMPLOYEES_SIZE));
        System.out.println("Powiększenie tablicy");
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public int getTotalNumberOfEmployees() {
        return currentNumberOfEmployees;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public int getNumberOfEmployeesByDepartment(Department department) {
        switch (department) {
            case IT -> {
                return numberOfItEmployees;
            }
            case MANAGEMENT -> {
                return numberOfManagementEmployees;
            }
            case SUPPORT -> {
                return numberOfSupportEmployees;
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
