package picnicanalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PicnicAnalyzer {
    private static final Logger LOGGER = Logger.getLogger(PicnicAnalyzer.class.getName());
    private final File inputFile;

    public PicnicAnalyzer(String fileName) {
        this.inputFile = new File(fileName);
        if (!inputFile.exists()) {
            try {
                if (inputFile.createNewFile()) {
                    System.out.println("Файл " + fileName + " создан.");
                } else {
                    LOGGER.log(Level.SEVERE, "Не удалось создать файл " + fileName);
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Ошибка при создании файла", e);
            }
        }
    }

    public int countWords() {
        int wordCount = 0;
        try {
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNext()) {
                scanner.next();
                wordCount++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Файл не найден", e);
        }
        return wordCount;
    }

    public String findLongestWord() {
        String longestWord = "";
        try {
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Файл не найден", e);
        }
        return longestWord;
    }

    public Map<String, Integer> countWordFrequency() {
        Map<String, Integer> frequencyMap = new HashMap<>();
        try {
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNext()) {
                String word = scanner.next();
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Файл не найден", e);
        }
        return frequencyMap;
    }

    public static void main(String[] args) {
        PicnicAnalyzer analyzer = new PicnicAnalyzer("input.txt");

        // Task 1: Count words
        int wordCount = analyzer.countWords();
        System.out.println("Общее количество слов: " + wordCount);

        // Task 2: Find longest word
        String longestWord = analyzer.findLongestWord();
        System.out.println("Самое длинное слово: " + longestWord);

        // Task 3: Count word frequency
        Map<String, Integer> wordFrequency = analyzer.countWordFrequency();
        System.out.println("Частота слов:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
