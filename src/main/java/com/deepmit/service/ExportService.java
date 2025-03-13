package com.deepmit.service;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class ExportService {
    private static final String FILE_PATH = "history.txt";

    public static void exportHistory() {
        if (historyMap.isEmpty()) {
            System.out.println("No history available.");
            return;
        }

        System.out.println("Exporting history...");

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            for (Map.Entry<String, String> entry : historyMap.entrySet()) {
                bw.write("Input: " + entry.getKey());
                bw.newLine();
                bw.write("Output: " + entry.getValue());
                bw.newLine();
                bw.write("=================================");
                bw.newLine();
            }

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
}
