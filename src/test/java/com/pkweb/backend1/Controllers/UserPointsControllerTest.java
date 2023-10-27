package com.pkweb.backend1.Controllers;

import com.pkweb.backend1.Entity.UserPoints;
import com.pkweb.backend1.dto.UserPointsDTO;
import com.pkweb.backend1.Services.UserPointsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserPointsControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserPointsControllerTest.class);

    @InjectMocks
    private UserPointsController userPointsController;

    @Mock
    private UserPointsService userPointsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserPoints() {
        // 模拟用户ID
        int userId = 1;

        // 模拟UserPoints对象
        UserPoints userPoints = new UserPoints();
        userPoints.setPoints(100);

        // 模拟userPointsService的getUserPointsByUser方法返回模拟UserPoints对象
        when(userPointsService.getUserPointsByUser(Mockito.any())).thenReturn(userPoints);

        // 调用getUserPoints方法并断言返回的积分
        int points = userPointsController.getUserPoints(userId);
        assertEquals(100, points);

        // 输出日志信息
        logger.info("UserPointsControllerTest - testGetUserPoints: User " + userId + " has " + points + " points.");
    }

    @Test
    public void testGetPlayerPercentile() {
        // 模拟用户ID
        int userId = 1;

        // 模拟百分位
        double percentile = 75.0;

        // 模拟userPointsService的calculatePercentile方法返回模拟百分位
        when(userPointsService.calculatePercentile(userId)).thenReturn(percentile);

        // 调用getPlayerPercentile方法并断言返回的百分位
        double result = userPointsController.getPlayerPercentile(userId);
        assertEquals(percentile, result);

        // 输出日志信息
        logger.info("UserPointsControllerTest - testGetPlayerPercentile: User " + userId + " has a percentile of " + percentile);
    }

    @Test
    public void testGetTop15Players() {
        // 创建模拟UserPointsDTO对象列表
        List<UserPointsDTO> userPointsDTOList = new ArrayList<>();
        // 添加模拟数据到列表中

        // 模拟userPointsService的getTop15Players方法返回模拟UserPointsDTO列表
        when(userPointsService.getTop15Players()).thenReturn(userPointsDTOList);

        // 调用getTop15Players方法并断言返回的UserPointsDTO列表
        List<UserPointsDTO> result = userPointsController.getTop15Players();
        assertEquals(userPointsDTOList, result);

        // 输出日志信息
        logger.info("UserPointsControllerTest - testGetTop15Players: Top 15 players list retrieved.");
    }
}
