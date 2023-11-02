package app;

import exception.NoSuchDepartmentException;
import io.ConsolePrinter;
import io.file.FileManager;
import model.Staff;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

public class StatisticsController {
    private Staff staff;
    private final FileManager fileManager = new FileManager();
    private final ConsolePrinter printer = new ConsolePrinter();

    public void run() {
        try {
            staff = fileManager.importData();
            printer.printLine("Udało się zaimportować dane pracowników z pliku.");
            String statistics = generateStatistics();
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

    private String generateStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Średnia wypłata: " + formatDouble(staff.getTotalSalary() / staff.getTotalNumberOfEmployees()) + "\n");
        sb.append("Minimalna wypłata: " + formatDouble(staff.getMinSalary()) + "\n");
        sb.append("Maksymalna wypłata: " + formatDouble(staff.getMaxSalary()) + "\n");
        sb.append("Liczba pracowników IT: " + formatDouble(staff.getNumberOfItEmployees()) + "\n");
        sb.append("Liczba pracowników Support: " + formatDouble(staff.getNumberOfSupportEmployees()) + "\n");
        sb.append("Liczba pracowników Management: " + formatDouble(staff.getNumberOfManagementEmployees()) + "\n");

        return sb.toString();
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
