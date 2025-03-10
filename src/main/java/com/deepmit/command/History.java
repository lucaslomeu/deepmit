package com.deepmit.command;

import java.util.LinkedHashMap;
import java.util.Map;

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
    }

    public static void clearHistory() {
        historyMap.clear();
        System.out.println("History cleared.");
    }
}
