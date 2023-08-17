package com.pkweb.backend1.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import java.util.Base64;

@RequestMapping("/pk")
@RestController
public class CodeSubmit {
    private String sourceCode;
    private String stdin;
    private String expectedOutput;

    public void initializeValues() {
        sourceCode = Base64.getEncoder().encodeToString("print(\"1\")".getBytes());
        stdin = "";
        expectedOutput = Base64.getEncoder().encodeToString("1\n".getBytes());
    }

    @RequestMapping("/coderesult")
    public String submitCode() {
        initializeValues();
        JSONObject requestBody = new JSONObject();
        requestBody.put("language_id", 92);
        requestBody.put("source_code", sourceCode);
        requestBody.put("stdin", stdin);
        requestBody.put("expected_output", expectedOutput);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=true&fields=*"))
                .header("content-type", "application/json")
                .header("Content-Type", "application/json")
                .header("X-RapidAPI-Key", "63a227cf39mshecd0ef4d413fbe3p11bf08jsnc3e3b7aa79d4")
                .header("X-RapidAPI-Host", "judge0-ce.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            String token = jsonObject.getString("token");
            Thread.sleep(5000); // Wait for 10 seconds
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

