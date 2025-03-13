package com.deepmit.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class History {

    private static final Map<String, String> historyMap = new LinkedHashMap<>();

    public static void start() {
        displayHistory();
    }

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

        for (Map.Entry<String, String> input : historyMap.entrySet()) {
            System.out.println("Input: " + input.getKey());
            System.out.println("Output: " + input.getValue());
            System.out.println("=================================");
        }

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

        System.out.println("Exporting history...");

        String filePath = "history.txt";

        try {
            File file = new File(filePath);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (Map.Entry<String, String> entry : historyMap.entrySet()) {
                bw.write("Input: " + entry.getKey());
                bw.newLine();
                bw.write("Output: " + entry.getValue());
                bw.newLine();
                bw.write("=================================");
                bw.newLine();
            }

            bw.close();

            System.out.println("History exported.");
            System.out.println("Open the file? (y/n)");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                Runtime.getRuntime().exec("notepad " + filePath);
                System.out.println("File opened.");
                System.out.println("How would you like to proceed? (/help to see options)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteHistoryFile() {
        File file = new File("history.txt");

        if (file.delete()) {
            System.out.println("History file deleted.");
        } else {
            System.out.println("Failed to delete history file.");
        }
    }

    public static void clearHistory() {
        historyMap.clear();
        System.out.println("History cleared.");
    }
}
