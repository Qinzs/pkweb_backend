package com.pkweb.backend1.Services;

import com.pkweb.backend1.dto.UserPointsDTO;
import com.pkweb.backend1.Entity.User;
import com.pkweb.backend1.Entity.UserPoints;
import com.pkweb.backend1.Repositories.UserMapper;
import com.pkweb.backend1.Repositories.UserPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPointsService {
    @Autowired
    private UserPointsRepository userPointsRepository;

    @Autowired
    UserMapper userMapper;

    public UserPoints getUserPointsByUser(User user) {
        return userPointsRepository.findByUser(user);
    }

    public double calculatePercentile(int userId) {
        // 查询数据库中所有玩家的积分，并按积分降序排列
        List<UserPoints> allUserPoints = userPointsRepository.findAllByOrderByPointsDesc();

        if (allUserPoints == null || allUserPoints.isEmpty()) {
            // 如果没有玩家积分数据，则返回0百分比
            return 0.0;
        }

        // 查找当前玩家的积分
        for (int i = 0; i < allUserPoints.size(); i++) {
            UserPoints userPoints = allUserPoints.get(i);
            // 获取当前用户的排名
            if (userPoints.getId().intValue() == userId){
                return   (double)(allUserPoints.size()-1-i)/(allUserPoints.size()-1) * 100;
            }
        }
        return 0.0;
    }

    public List<UserPointsDTO> getTop15Players() {
        // 查询数据库中前十五名玩家的积分数据，并按积分降序排列
        List<UserPoints> topPlayers = userPointsRepository.findTop15ByOrderByPointsDesc();


        // 转换为DTO对象
        List<UserPointsDTO> topPlayersDTO = topPlayers.stream()
                .map(userPoints -> new UserPointsDTO(userPoints.getUser().getUsername(), userPoints.getPoints(),userPoints.getUser().getProfile()))
                .collect(Collectors.toList());
        return topPlayersDTO;
    }

}

