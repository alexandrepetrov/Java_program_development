import java.io.*;
import java.util.*;

// Класс для обработки данных
class DataProcessor {
    // Сортировка данных (по возрастанию)
    public List<String> sortData(List<String> data) {
        Collections.sort(data);
        return data;
    }

    // Фильтрация данных (удаление пустых строк)
    public List<String> filterData(List<String> data) {
        data.removeIf(String::isEmpty);
        return data;
    }

    // Анализ данных (подсчет количества строк)
    public int analyzeData(List<String> data) {
        return data.size();
    }
}

// Класс для работы с файлами
class FileHandler {
    // Чтение данных из файла
    public List<String> readFile(String fileName) throws IOException {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        }
        return data;
    }

    // Запись данных в файл
    public void writeFile(String fileName, List<String> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}

// Основной класс
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler();
        DataProcessor dataProcessor = new DataProcessor();

        try {
            // Чтение данных из файла
            System.out.print("Введите имя файла для чтения: ");
            String inputFileName = scanner.nextLine();
            List<String> data = fileHandler.readFile(inputFileName);

            // Обработка данных
            data = dataProcessor.filterData(data);
            data = dataProcessor.sortData(data);
            int lineCount = dataProcessor.analyzeData(data);

            // Вывод результатов
            System.out.println("Обработанные данные:");
            for (String line : data) {
                System.out.println(line);
            }
            System.out.println("Количество строк: " + lineCount);

            // Сохранение данных в файл
            System.out.print("Введите имя файла для сохранения: ");
            String outputFileName = scanner.nextLine();
            fileHandler.writeFile(outputFileName, data);
            System.out.println("Данные сохранены в файл: " + outputFileName);

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}