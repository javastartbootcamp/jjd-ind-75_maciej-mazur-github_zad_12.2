import app.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Nested
    class MultipleEmployees {

        @BeforeEach
        public void init() throws IOException  {
            File file = new File("employees.csv");

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append("Jan;Kowalski;123456789;Management;6000\n");
            fileWriter.append("Aneta;Zawadzka;432156789;IT;5200\n");
            fileWriter.append("Karol;Kowalewski;5412328948;IT;5200\n");
            fileWriter.append("Jan;Zarzecki;678628373674;Support;4800\n");
            fileWriter.close();
        }

        @Test
        void noDuplicatedLines() throws IOException {
            // when
            Main.main(new String[]{});

            // then
            File statsFile = new File("stats.txt");
            assertThat(statsFile).exists();
            List<String> allLines = Files.readAllLines(statsFile.toPath());

            HashSet<String> allLinesWithoutDuplicates = new HashSet<>(allLines);

            assertThat(allLines.size()).as("Plik wynikowy zawiera duplikaty!").isEqualTo(allLinesWithoutDuplicates.size());
        }

        @Test
        void avgSalary() throws IOException {
            // when
            Main.main(new String[]{});

            // then
            File statsFile = new File("stats.txt");
            assertThat(statsFile).exists();
            String fileContent = Files.readString(statsFile.toPath());

            assertThat(fileContent).contains("Średnia wypłata: 5300");
        }

        @Test
        void minSalary() throws IOException {
            // when
            Main.main(new String[]{});

            // then
            File statsFile = new File("stats.txt");
            assertThat(statsFile).exists();
            String fileContent = Files.readString(statsFile.toPath());

            assertThat(fileContent).contains("Minimalna wypłata: 4800");
        }

        @Test
        void maxSalary() throws IOException {
            // when
            Main.main(new String[]{});

            // then
            File statsFile = new File("stats.txt");
            assertThat(statsFile).exists();
            String fileContent = Files.readString(statsFile.toPath());

            assertThat(fileContent).contains("Maksymalna wypłata: 6000");
        }

        @Test
        void iTDepartmentEmployeeCount() throws IOException {
            // when
            Main.main(new String[]{});

            // then
            File statsFile = new File("stats.txt");
            assertThat(statsFile).exists();
            String fileContent = Files.readString(statsFile.toPath());

            assertThat(fileContent).contains("Liczba pracowników IT: 2");
        }

        @Test
        void managementDepartmentEmployeeCount() throws IOException {
            // when
            Main.main(new String[]{});

            // then
            File statsFile = new File("stats.txt");
            assertThat(statsFile).exists();
            String fileContent = Files.readString(statsFile.toPath());

            assertThat(fileContent).contains("Liczba pracowników Management: 1");
        }

        @Test
        void supportDepartmentEmployeeCount() throws IOException {
            // when
            Main.main(new String[]{});

            // then
            File statsFile = new File("stats.txt");
            assertThat(statsFile).exists();
            String fileContent = Files.readString(statsFile.toPath());

            assertThat(fileContent).contains("Liczba pracowników Support: 1");
        }

    }

    @Nested
    class OnlyOneEmployeeInHRDepartment {

        @BeforeEach
        public void init() throws IOException  {
            File file = new File("employees.csv");

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append("Jan;Kowalski;123456789;IT;6000\n");
            fileWriter.close();
        }

        @Test
        void noDuplicatedLines() throws IOException {
            // when
            Main.main(new String[]{});

            // then
            File statsFile = new File("stats.txt");
            assertThat(statsFile).exists();
            List<String> allLines = Files.readAllLines(statsFile.toPath());

            HashSet<String> allLinesWithoutDuplicates = new HashSet<>(allLines);

            assertThat(allLines.size()).as("Plik wynikowy zawiera duplikaty!").isEqualTo(allLinesWithoutDuplicates.size());
        }

        @Test
        void avgSalary() throws IOException {
            // when
            Main.main(new String[]{});

            // then
            File statsFile = new File("stats.txt");
            assertThat(statsFile).exists();
            String fileContent = Files.readString(statsFile.toPath());

            assertThat(fileContent).contains("Średnia wypłata: 6000");
        }

        @Test
        void minSalary() throws IOException {
            // when
            Main.main(new String[]{});

            // then
            File statsFile = new File("stats.txt");
            assertThat(statsFile).exists();
            String fileContent = Files.readString(statsFile.toPath());

            assertThat(fileContent).contains("Minimalna wypłata: 6000");
        }

        @Test
        void maxSalary() throws IOException {
            // when
            Main.main(new String[]{});

            // then
            File statsFile = new File("stats.txt");
            assertThat(statsFile).exists();
            String fileContent = Files.readString(statsFile.toPath());

            assertThat(fileContent).contains("Maksymalna wypłata: 6000");
        }

    }

}
