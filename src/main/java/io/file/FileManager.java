package io.file;

import exception.NoSuchDepartmentException;
import model.*;

import java.io.*;
import java.util.Scanner;

public class FileManager {
    private static final String INPUT_FILE_NAME = "employees.csv";
    private static final String OUTPUT_FILE_NAME = "stats.txt";

    public Staff importData() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(INPUT_FILE_NAME))) {
            int lineNumbers = countCsvLines();
            Staff staff = new Staff(lineNumbers);

            while (scanner.hasNextLine()) {
                Employee employee = createEmployeeFromString(scanner.nextLine());
                staff.addEmployee(employee);
            }

            return staff;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Brak pliku wejściowego " + INPUT_FILE_NAME);
        }
    }

    public void exportData(String statistics) throws IOException {
        try (
                var fileWriter = new FileWriter(OUTPUT_FILE_NAME);
                var writer = new BufferedWriter(fileWriter)
                ) {
            writer.write(statistics);
        }
    }

    private Employee createEmployeeFromString(String line) throws NoSuchDepartmentException, NumberFormatException {
        String[] split = line.split(";");
        String firsName = split[0];
        String lastName = split[1];
        String pesel = split[2];
        Department department = createDepartmentFromString(split[3].toUpperCase());
        double salary = Double.parseDouble(split[4]);

        return new Employee(firsName, lastName, pesel, department, salary);
    }

    private Department createDepartmentFromString(String data) {
        switch (data) {
            case "MANAGEMENT" -> {
                return Department.MANAGEMENT;
            }
            case "IT" -> {
                return Department.IT;
            } case "SUPPORT" -> {
                return Department.SUPPORT;
            }
            default -> throw new NoSuchDepartmentException("W pliku " + INPUT_FILE_NAME +
                    " znajduje się nieprawidłowa nazwa działu " + data);
        }
    }

    private int countCsvLines() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(INPUT_FILE_NAME))) {
            int linesNumber = 0;

            while (scanner.hasNextLine()) {
                scanner.nextLine();
                linesNumber++;
            }

            return linesNumber;
        }
    }
}
