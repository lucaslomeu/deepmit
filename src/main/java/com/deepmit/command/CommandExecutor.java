package com.deepmit.command;

import java.util.Scanner;

import com.deepmit.service.HistoryService;

public class CommandExecutor {
    private final HistoryService historyService = new HistoryService();

    public void execute(String command, Scanner scanner) {
        switch (command) {
            case "/help":
                System.out.println("Available commands:");
                System.out.println("/history - Display chat history");
                System.out.println("/export - Export chat history");
                System.out.println("/delete - Delete chat history");
                System.out.println("/clear - Clear chat history");
                System.out.println("/time - Display current time");
                System.out.println("/exit - Exit the chat");
                break;

            case "/history":
                historyService.displayHistory();
                break;

            case "/export":
                historyService.exportHistory();
                break;

            case "/delete":
                historyService.deleteHistory();
                break;

            case "/clear":
                History.clearHistory();
                break;

            case "/time":
                java.time.LocalDateTime now = java.time.LocalDateTime.now();
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                        .ofPattern("HH:mm:ss, EEEE, MMMM dd");
                System.out.println("Current time: " + now.format(formatter));
                break;

            case "/exit":
                System.out.println("Exiting chat...");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid command!");
        }
    }
}
