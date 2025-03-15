package com.deepmit.application;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.deepmit.service.HistoryService;

public class DeepMit {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HistoryService historyService = new HistoryService();
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final long INACTIVITY_LIMIT = 5;

    public static void main(String[] args) {
        new DeepMit().run();
    }

    private void run() {
        boolean runing = true;

        scheduler.schedule(this::handleInactivity, INACTIVITY_LIMIT, TimeUnit.MINUTES);

        while (runing) {
            displayMenu();
            String option = scanner.nextLine().trim();

            resetInactivityTimer();

            switch (option) {
                case "1":
                    DeepMitClient.start();
                    break;
                case "2":
                    historyService.displayHistory();
                    break;
                case "3":
                    historyService.exportHistory();
                    break;
                case "4":
                    historyService.deleteHistory();
                    break;
                case "5":
                    historyService.clearHistory();
                    break;
                case "6":
                    runing = false;
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }

        }
    }

    private void handleInactivity() {
        System.out.println("No activity detected for 5 minutes. Exiting...");

        System.exit(0);
    }

    private void resetInactivityTimer() {
        scheduler.shutdownNow();
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(this::handleInactivity, INACTIVITY_LIMIT, TimeUnit.MINUTES);
    }

    private void displayMenu() {
        System.out.println("=================================");
        System.out.println("        WELCOME TO DEEPMIT       ");
        System.out.println("=================================");
        System.out.println("1. Start Chat");
        System.out.println("2. Show History");
        System.out.println("3. Export History");
        System.out.println("4. Delete History");
        System.out.println("5. Clear History");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }
}
