package com.pkweb.backend1.Controllers;

import com.pkweb.backend1.Entity.Match;
import com.pkweb.backend1.Entity.Submission;
import com.pkweb.backend1.Entity.UserPoints;
import com.pkweb.backend1.Repositories.MatchRepository;
import com.pkweb.backend1.Repositories.SubmissionRepository;
import com.pkweb.backend1.Repositories.UserPointsRepository;
import com.pkweb.backend1.controller.pk.CodeSubmit;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import java.time.LocalDateTime;

@RestController
public class CodeSubmissionController {

    @Autowired
    private CodeSubmit codeSubmit;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserPointsRepository userPointsRepository;

    @PostMapping("/submitCode")
    public ResponseEntity<String> submitCode(@RequestBody String payload) throws Exception {

        // Parse the incoming payload
        System.out.println("Handling incoming POST request.");

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

        // ... [same logic from handleTextMessage] ...

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


        System.out.println("Result sent successfully.");
        // Send the result back as HTTP response
        return ResponseEntity.ok(responseObj.toString());
    }
}
