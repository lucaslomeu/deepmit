package com.deepmit.command;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class History {
    private static final String FILE_PATH = "history.txt";
    private static final String JSON_FILE_PATH = "history.json";
    private static final String CSV_FILE_PATH = "history.csv";
    private static Map<String, String> historyMap = new LinkedHashMap<>();

    public static void addEntry(String input, String output) {
        historyMap.put(input, output);
    }

    public static void displayHistory() {
        if (historyMap.isEmpty()) {
            System.out.println("No history available.");
            return;
        }

        System.out.println("=================================");
        System.out.println("           HISTORY               ");
        System.out.println("=================================");

        historyMap.forEach((key, value) -> {
            System.out.println("Input: " + key);
            System.out.println("Output: " + value);
            System.out.println("=================================");
        });

        System.out.println("End of history.");
        System.out.println("Do you want to export the history? (y/n)");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            exportHistory();
        }
    }

    public static void exportHistory() {
        if (historyMap.isEmpty()) {
            System.out.println("No history available.");
            return;
        }

        System.out.println("Choose a format to export the history:");
        System.out.println("1. Text file");
        System.out.println("2. JSON file");
        System.out.println("3. CSV file");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        System.out.println("Exporting history...");

        switch (choice) {
            case "1":
                exportToTextFile();
                break;
            case "2":
                exportToJsonFile();
                break;
            case "3":
                exportToCSVFile();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void exportToTextFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            for (Map.Entry<String, String> entry : historyMap.entrySet()) {
                writer.write("Input: " + entry.getKey());
                writer.newLine();
                writer.write("Output: " + entry.getValue());
                writer.newLine();
                writer.write("=================================");
                writer.newLine();
            }

            System.out.println("History exported.");
            System.out.println("Open the file? (y/n)");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                Runtime.getRuntime().exec("notepad " + FILE_PATH);
                System.out.println("File opened.");
                System.out.println("How would you like to proceed? (/help to see options)");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportToJsonFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(Paths.get(JSON_FILE_PATH).toFile(), historyMap);
            System.out.println("History exported to JSON file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportToCSVFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH))) {
            writer.write("Input,Output");
            writer.newLine();

            for (Map.Entry<String, String> entry : historyMap.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }

            System.out.println("History exported to CSV file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteHistoryFile() {
        try {
            Files.deleteIfExists(Paths.get(FILE_PATH));
            Files.deleteIfExists(Path.of(JSON_FILE_PATH));
            Files.deleteIfExists(Path.of(CSV_FILE_PATH));
            System.out.println("History file deleted.");
        } catch (Exception e) {
            System.out.println("Failed to delete history file.");
        }
    }

    public static void clearHistory() {
        historyMap.clear();
        System.out.println("History cleared.");
    }
}
