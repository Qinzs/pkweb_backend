package com.pkweb.backend1.Services;

import com.pkweb.backend1.Repositories.UserPointsRepository;
import com.pkweb.backend1.controller.pk.User;
import com.pkweb.backend1.pojo.UserPoints;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPointsService {
    @Autowired
    private UserPointsRepository userPointsRepository;

    public UserPoints getUserPointsByUser(User user) {
        return userPointsRepository.findByUser(user);
    }

    public double calculatePercentile(Long userId) {
        // 查询数据库中所有玩家的积分，并按积分降序排列
        List<UserPoints> allUserPoints = userPointsRepository.findAllByOrderByPointsDesc();

        if (allUserPoints.isEmpty()) {
            // 如果没有玩家积分数据，则返回0百分比
            return 0.0;
        }

        // 查找当前玩家的积分
        UserPoints currentUserPoints = userPointsRepository.findByUserId(userId);

        if (currentUserPoints == null) {
            // 如果当前玩家的积分数据不存在，则返回0百分比
            return 0.0;
        }

        // 获取当前玩家的积分
        int userPoints = currentUserPoints.getPoints();

        // 计算当前玩家在排名中的位置
        int userRank = 1;
        for (UserPoints userPoint : allUserPoints) {
            if (userPoint.getPoints() > userPoints) {
                userRank++;
            } else {
                break;
            }
        }

        // 计算百分比位置
        double percentile = (double) userRank / allUserPoints.size() * 100.0;

        return percentile;
    }

    public List<UserPointsDTO> getTop15Players() {
        // 查询数据库中前十五名玩家的积分数据，并按积分降序排列
        List<UserPoints> topPlayers = userPointsRepository.findTop15ByOrderByPointsDesc();

        // 转换为DTO对象
        List<UserPointsDTO> topPlayersDTO = topPlayers.stream()
                .map(userPoints -> new UserPointsDTO(userPoints.getUser().getUsername(), userPoints.getPoints()))
                .collect(Collectors.toList());

        return topPlayersDTO;
    }

}

