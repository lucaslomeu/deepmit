package com.deepmit;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class DeepMit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=================================");
            System.out.println("     WELCOME TO DEEPMIT          ");
            System.out.println("=================================");
            System.out.println("1. Start Chat");
            System.out.println("2. Show History");
            System.out.println("3. Export Histórico");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    DeepMitClient.start();
                    break;
                case "2":
                    System.out.println("History");
                    break;
                case "3":
                    System.out.println("Export History");
                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }

        }
    }
}
