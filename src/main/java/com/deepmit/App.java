package com.deepmit;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem vindo ao DeepMit. Vamos começar!");
        System.out.println("Primeiro eu gostaria de saber o seu nome:");

        String name = scanner.nextLine();

        System.out.println("Bem vindo, " + name + "!");

        while (true) {
            System.out.println("Qual dúvida você possui? (Digite '/exit' para sair)");

            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("/exit")) {
                break;
            }

            System.out.println("Pesquisando a resposta, por favor aguarde...");

            String response = DeepMitClient.getResponse(userInput);
            System.out.println(response);
        }

        System.out.println("Obrigado por usar o chat!");

        // Finalizar corretamente as threads
        DeepMitClient.shutdown();
        scanner.close();
    }
}
