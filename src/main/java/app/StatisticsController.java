package app;

import exception.NoSuchDepartmentException;
import io.ConsolePrinter;
import io.file.FileManager;
import model.Department;
import model.Staff;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

public class StatisticsController {
    public void run() {
        FileManager fileManager = new FileManager();
        ConsolePrinter printer = new ConsolePrinter();

        try {
            Staff staff = fileManager.importData();
            printer.printLine("Udało się zaimportować dane pracowników z pliku.");
            String statistics = generateStatistics(staff);
            fileManager.exportData(statistics);
            printer.printLine("Prawidłowo wyeksportowano statystyki firmy do pliku.");
        } catch (FileNotFoundException | NoSuchDepartmentException e) {
            printer.printLine(e.getMessage());
        } catch (NumberFormatException e) {
            printer.printLine("Napotkano błędny format ceny w pliku wejściowym.");
        } catch (IOException e) {
            printer.printLine("Błąd zapisu do pliku wyjściowego.");
        }
    }

    private String generateStatistics(Staff staff) {
        if (staff.getTotalNumberOfEmployees() == 0) {
            return "Wczytany plik wejściowy nie zawierał żadnych rekordów z danymi pracowników.";
        }

        return String.format("Średnia wypłata: %s%n" +
                "Minimalna wypłata: %s%n" +
                "Maksymalna wypłata: %s%n" +
                "Liczba pracowników IT: %d%n" +
                "Liczba pracowników Support: %d%n" +
                "Liczba pracowników Management: %d%n",
                formatDouble(staff.getTotalSalary() / staff.getTotalNumberOfEmployees()),
                formatDouble(staff.getMinSalary()),
                formatDouble(staff.getMaxSalary()),
                staff.getNumberOfEmployeesByDepartment(Department.IT),
                staff.getNumberOfEmployeesByDepartment(Department.SUPPORT),
                staff.getNumberOfEmployeesByDepartment(Department.MANAGEMENT));
    }

    private String formatDouble(double number) {
        int decimalPart = (int) number;

        if (((double) decimalPart) == number) {  //  czyli jeśli number jest liczbą całkowitą
            return String.valueOf(decimalPart);
        } else {
            return String.format(Locale.getDefault(), "%.2f", number);
        }
    }
}
