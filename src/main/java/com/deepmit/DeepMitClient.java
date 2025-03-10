package com.deepmit;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import com.deepmit.command.History;

public class DeepMitClient {
    private static final String API_KEY = System.getenv("API_KEY");
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    private static final ExecutorService executor = Executors.newFixedThreadPool(4);
    private static final HttpClient client = HttpClient.newBuilder().executor(executor).build();

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDeepMit started! Type your message or a command (/help to see options).");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.startsWith("/")) {
                // Algum esquema para pegar os comandos e executar eles
                switch (userInput) {
                    case "/help":
                        System.out.println("Available commands:");
                        System.out.println("/history - Display chat history");
                        System.out.println("/time - Display current time");
                        System.out.println("/exit - Exit the chat");
                        break;

                    case "/history":
                        History.displayHistory();
                        break;

                    case "/time":
                        System.out.println("Current time: " + java.time.LocalTime.now());
                        break;

                    case "/exit":
                        System.out.println("Exiting chat...");
                        return;
                    default:
                        System.out.println("Invalid command!");
                }
            } else {
                System.out.println("Searching for response, please wait...");
                String response = getResponse(userInput);
                System.out.println("Response: " + getResponse(userInput));
            }

        }
    }

    public static String getResponse(String userInput) {
        JSONObject jsonBody = new JSONObject();

        jsonBody.put("model", "deepseek/deepseek-r1:free");

        jsonBody.put("messages", new JSONArray()
                .put(new JSONObject().put("role", "system").put("content", "Hello! How can I help you?"))
                .put(new JSONObject().put("role", "user").put("content", userInput)));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject responseBody = new JSONObject(response.body());

                History.addEntry(userInput, responseBody.getJSONArray("choices").getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content"));

                return responseBody.getJSONArray("choices").getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
            } else {
                return "Sorry, I couldn't understand your message.";
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "Sorry, I couldn't understand your message.";
        }

    }

    public static void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
