package com.pkweb.backend1.controller.pk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import java.util.Base64;


    @RestController
    @RequestMapping("/pk")
    public class CodeSubmit {

        @Value("63a227cf39mshecd0ef4d413fbe3p11bf08jsnc3e3b7aa79d4")  // Assuming you have the API key in a properties or yml file
        private String rapidApiKey;

        public String submitCode(String source, String input, String expected) {
            JSONObject requestBody = new JSONObject();
            requestBody.put("language_id", 92); // Assuming Python; adjust if different languages are used
            requestBody.put("source_code", Base64.getEncoder().encodeToString(source.getBytes()));
            requestBody.put("stdin", Base64.getEncoder().encodeToString(input.getBytes()));  // Encoding the input using Base64

            requestBody.put("expected_output", Base64.getEncoder().encodeToString(expected.getBytes()));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=true&fields=*"))
                    .header("content-type", "application/json")
                    .header("X-RapidAPI-Key", rapidApiKey)
                    .header("X-RapidAPI-Host", "judge0-ce.p.rapidapi.com")
                    .method("POST", HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();
            try {
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                JSONObject jsonObject = new JSONObject(response.body());
                String token = jsonObject.getString("token");

                // You may need a better way to ensure the code has been judged
                Thread.sleep(5000);  // Still 5 seconds; consider adjusting or using a loop to check periodically

                return getSubmitCodeResult(token);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

    private String getSubmitCodeResult(String token) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge0-ce.p.rapidapi.com/submissions/" + token + "?base64_encoded=true&fields=*"))
                .header("X-RapidAPI-Key", "63a227cf39mshecd0ef4d413fbe3p11bf08jsnc3e3b7aa79d4")
                .header("X-RapidAPI-Host", "judge0-ce.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}

