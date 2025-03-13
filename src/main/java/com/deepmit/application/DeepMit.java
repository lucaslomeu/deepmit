package com.deepmit.application;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.deepmit.command.History;
import com.deepmit.service.HistoryService;

public class DeepMit {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HistoryService historyService = new HistoryService();

    public static void main(String[] args) {
        new DeepMit().run();
    }

    private void run() {
        boolean runing = true;

        while (runing) {
            displayMenu();
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    DeepMitClient.start();
                    break;
                case "2":
                    History.displayHistory();();
                    break;
                case "3":
                    History.exportHistory();
                    break;
                case "4":
                    runing = false;
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }

        }
    }

    private void displayMenu() {
        System.out.println("=================================");
        System.out.println("     WELCOME TO DEEPMIT          ");
        System.out.println("=================================");
        System.out.println("1. Start Chat");
        System.out.println("2. Show History");
        System.out.println("3. Export History");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }
}
