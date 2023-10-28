package com.pkweb.backend1.WebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkweb.backend1.Entity.*;
import com.pkweb.backend1.Repositories.*;
import com.pkweb.backend1.Services.ProblemService;
import com.pkweb.backend1.controller.pk.CodeSubmit;
import org.apache.catalina.Store;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
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
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private UserPointsRepository userPointsRepository;
    @Autowired
    private UserRepository userRepository;
@Autowired
private ContactRepository contactRepository;
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
            // ... 其他代码 ...

// Store the match in the database
            Match match = new Match();
            match.setUser1Id(Long.parseLong((String) user1.getAttributes().get("userId")));
            match.setUser2Id(Long.parseLong((String) user2.getAttributes().get("userId")));
            match.setProblemId(problem.getId());
            match.setStartTime(LocalDateTime.now());

// Saving the match to get the autogenerated matchID
            Match savedMatch = matchRepository.save(match);

// Fetch the autogenerated matchID
            Long matchId = savedMatch.getMatchId();

// Construct a response that includes both the problem and the matchID
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("problem", problem);
            responseMap.put("matchId", matchId);

            String response = objectMapper.writeValueAsString(responseMap);
            //首先判断这两个
            // 创建从 userId1 到 userId2 的联系关系
            Contact contact1 = new Contact();
            contact1.setUserId((int) Long.parseLong((String) user1.getAttributes().get("userId")));
            contact1.setContactId((int) Long.parseLong((String) user2.getAttributes().get("userId")));


            // 创建从 userId2 到 userId1 的联系关系
            Contact contact2 = new Contact();
            contact2.setUserId((int) Long.parseLong((String) user2.getAttributes().get("userId")));
            contact2.setContactId((int) Long.parseLong((String) user1.getAttributes().get("userId")));
            try {
                contactRepository.saveAndFlush(contact1);
                contactRepository.saveAndFlush(contact2);
            } catch (DataIntegrityViolationException e) {
                // 捕获违反主键约束的异常
                System.out.println("好友对已存在");
            }

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
        int matchId = jsonObject.getInt("matchId");
        int userId = jsonObject.getInt("userId");
        int problemId = jsonObject.getInt("problemId");
        System.out.println("Extracted problemId: " + problemId);

        String sourceCode = jsonObject.getString("problemSolutions");
        String inputFormat = jsonObject.getString("inputFormat");

        String outputFormat = jsonObject.getString("outputFormat");

        // Call the submitCode method from CodeSubmit to check the solution
        String result = codeSubmit.submitCode(sourceCode, inputFormat, outputFormat);
        // 解析JSON字符串
        JSONObject jsonObject1 = new JSONObject(result);
        String time = jsonObject1.getString("time");
        int memory = jsonObject1.getInt("memory");
        String statusDescription = jsonObject1.getJSONObject("status").getString("description");
        Submission submission = new Submission();

        submission.setUserId((long) userId); // Convert int to Long
        submission.setProblemId((long) problemId); // Convert int to Long
        submission.setSubmittedCode(sourceCode);
        submission.setSubmittedTime(LocalDateTime.now()); // Current time
        submission.setResult(statusDescription);
        submission.setExecutionTime(Double.parseDouble(time)); // Assuming time is a string with potential decimals
        submission.setMemoryUse(memory);

// Save the submission to the database
        submissionRepository.save(submission);
        JSONObject responseObj = new JSONObject();

// Add the already known values
        responseObj.put("result", statusDescription);
        responseObj.put("runTime", time);
        responseObj.put("memoryUse", String.valueOf(memory)); // Convert int to String

// Determine the "ifwin" status
        String ifwin = "waiting"; // default
        Match match = matchRepository.findById((long) matchId).orElse(null);
        if (match != null) {
            if (match.getWinnerId() != null) {
                ifwin = "lose"; // If there's already a winner, the user loses
            } else if (statusDescription.equals("Accepted")) {
                ifwin = "win"; // If the solution is accepted and no winner yet, the user wins
            }
        }
        responseObj.put("ifwin", ifwin);

        //然后进行比较，如果statusDescription为Accepted，则检查该matchID对应的match的WinnerId是否为空，如果为空，则将该用户的ID设置为WinnerId，EndTime设置为当前时间，否则不做任何操作。
        if (statusDescription.equals("Accepted")) {
            Match match1 = matchRepository.findById((long) matchId).get();
            if (match1.getWinnerId() == null) {
                match1.setWinnerId((long) userId);
                match1.setEndTime(LocalDateTime.now());
                matchRepository.save(match1);
                // 更新用户积分
                UserPoints userPoints = userPointsRepository.findByUser_UserId((long) userId);

                if (userPoints != null) {
                    int currentPoints = userPoints.getPoints();
                    userPoints.setPoints(currentPoints + 5); // Add 5 points for winning
                    userPointsRepository.save(userPoints);
                }
            }

        }

        // Send the result back to the user
        session.sendMessage(new TextMessage(responseObj.toString()));


        System.out.println("Result sent successfully.");
    }


}

