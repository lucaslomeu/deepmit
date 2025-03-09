package com.deepmit;

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
            System.out.println("     BEM-VINDO AO DEEPMIT        ");
            System.out.println("=================================");
            System.out.println("1. Iniciar Chat");
            System.out.println("2. Ver Histórico");
            System.out.println("3. Exportar Histórico");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    DeepMitClient.start();
                    break;
                case "2":
                    System.out.println("Histórico");
                    break;
                case "3":
                    System.out.println("Exportar Histórico");
                    break;
                case "4":
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }

        }
    }
}
