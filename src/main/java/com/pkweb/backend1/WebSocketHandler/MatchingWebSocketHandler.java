package com.pkweb.backend1.WebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkweb.backend1.Entity.Problem;
import com.pkweb.backend1.Services.ProblemService;
import com.pkweb.backend1.controller.pk.CodeSubmit;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MatchingWebSocketHandler extends TextWebSocketHandler {
    private Queue<WebSocketSession> waitingSessions = new LinkedList<>();

    @Autowired
    private ProblemService problemService;  // Service to fetch random problems from the database
    @Autowired
    private CodeSubmit codeSubmit;  // <-- Inject the service

    private ObjectMapper objectMapper = new ObjectMapper();  // Jackson's JSON processor

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        synchronized (waitingSessions) {
            waitingSessions.add(session);
            tryPairUsers();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        synchronized (waitingSessions) {
            waitingSessions.remove(session);
        }
    }

    private void tryPairUsers() throws Exception {
        if (waitingSessions.size() >= 2) {
            WebSocketSession user1 = waitingSessions.poll();
            WebSocketSession user2 = waitingSessions.poll();

            // Fetch a random problem, possibly based on difficulty or other criteria
            Problem problem = problemService.getRandomProblem();
            if (problem == null) {
                // Handle the case where no problem is available, maybe by notifying the users
                return;
            }

            // Convert the problem to JSON using Jackson
            String response = objectMapper.writeValueAsString(problem);

            user1.sendMessage(new TextMessage(response));
            user2.sendMessage(new TextMessage(response));
        }
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Handling incoming WebSocket message.");

        // Parse the incoming message
        String payload = message.getPayload();
        System.out.println("Received payload: " + payload);

        JSONObject jsonObject = new JSONObject(payload);

        int problemId = jsonObject.getInt("problemId");
        System.out.println("Extracted problemId: " + problemId);

        String sourceCode = jsonObject.getString("problemSolutions");
        System.out.println("Extracted sourceCode: " + sourceCode.substring(0, Math.min(50, sourceCode.length())) + "...");  // Printing only first 50 chars for brevity

        String inputFormat = jsonObject.getString("inputFormat");
        System.out.println("Extracted inputFormat: " + inputFormat);

        String outputFormat = jsonObject.getString("outputFormat");
        System.out.println("Extracted outputFormat: " + outputFormat);

        // Call the submitCode method from CodeSubmit to check the solution
        System.out.println("Submitting the code for checking.");
        String result = codeSubmit.submitCode(sourceCode, inputFormat, outputFormat);
        System.out.println("Received result: " + result);

        // Send the result back to the user
        System.out.println("Sending the result back to the client.");
        session.sendMessage(new TextMessage(result));
        System.out.println("Result sent successfully.");
    }

}

