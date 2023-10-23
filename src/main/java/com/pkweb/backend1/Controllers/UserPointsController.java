package com.pkweb.backend1.Controllers;

import com.pkweb.backend1.Entity.User;
import com.pkweb.backend1.dto.UserPointsDTO;
import com.pkweb.backend1.Entity.UserPoints;
import com.pkweb.backend1.Services.UserPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/points")
public class UserPointsController {
    @Autowired
    private UserPointsService userPointsService;

    @GetMapping("/user/{userId}")
    public int getUserPoints(@PathVariable Long userId) {
        // 通过用户ID查询用户的积分
        User user = new User();
        user.setUserId(Math.toIntExact(userId)); // 设置用户ID
        UserPoints userPoints = userPointsService.getUserPointsByUser(user);

        if (userPoints != null) {
            return userPoints.getPoints();
        } else {
            // 处理积分不存在的情况，可以返回默认值或其他适当的响应
            return 0;
        }
    }

    @GetMapping("/percentile/{userId}")
    public double getPlayerPercentile(@PathVariable Long userId) {
        double percentile = userPointsService.calculatePercentile(userId);
        return percentile;
    }

    @GetMapping("/leaderboard/top15")
    public List<UserPointsDTO> getTop15Players() {
        List<UserPointsDTO> topPlayers = userPointsService.getTop15Players();
        return topPlayers;
    }
}
