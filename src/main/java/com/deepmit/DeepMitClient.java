package com.deepmit;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

public class DeepMitClient {
    private static final String API_KEY = "API KEY";
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    private static final ExecutorService executor = Executors.newFixedThreadPool(4);
    private static final HttpClient client = HttpClient.newBuilder().executor(executor).build();

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

                return responseBody.getJSONArray("choices").getJSONObject(0).getJSONObject("message")
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
