package model;

public class Staff {
    private final Employee[] employees;
    private int numberOfEmployees;

    public Staff(int employeeNumber) {
        employees = new Employee[employeeNumber];
    }

    public void addEmployee(Employee employee) {
        employees[numberOfEmployees++] = employee;
    }

    public double getTotalSalary() {
        double totalSalary = 0;

        for (Employee employee : employees) {
            totalSalary += employee.getSalary();
        }

        return totalSalary;
    }

    public int getTotalNumberOfEmployees() {
        return numberOfEmployees;
    }

    public double getMinSalary() {
        double minSalary = Double.MAX_VALUE;

        for (Employee employee : employees) {
            if (employee.getSalary() < minSalary) {
                minSalary = employee.getSalary();
            }
        }

        return minSalary;
    }

    public double getMaxSalary() {
        double maxSalary = Double.MIN_VALUE;

        for (Employee employee : employees) {
            if (employee.getSalary() > maxSalary) {
                maxSalary = employee.getSalary();
            }
        }

        return maxSalary;
    }

    public int getNumberOfItEmployees() {
        int number = 0;

        for (Employee employee : employees) {
            if (employee.getDepartment() == Department.IT) {
                number++;
            }
        }

        return number;
    }

    public int getNumberOfManagementEmployees() {
        int number = 0;

        for (Employee employee : employees) {
            if (employee.getDepartment() == Department.MANAGEMENT) {
                number++;
            }
        }

        return number;
    }

    public int getNumberOfSupportEmployees() {
        int number = 0;

        for (Employee employee : employees) {
            if (employee.getDepartment() == Department.SUPPORT) {
                number++;
            }
        }

        return number;
    }
}
