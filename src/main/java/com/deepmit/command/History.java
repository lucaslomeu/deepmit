package com.deepmit.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class History {
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
       })

        System.out.println("End of history.");
        System.out.println("Do you want to export the history? (y/n)");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            exportHistory();
        }

    }

    public static void deleteHistoryFile() {
        try {
            Files.deleteIfExists(Paths.get(FILE_PATH));
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
