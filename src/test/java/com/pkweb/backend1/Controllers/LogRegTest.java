package com.pkweb.backend1.Controllers;

import com.pkweb.backend1.controller.pk.LogReg;
import com.pkweb.backend1.Entity.User;
import com.pkweb.backend1.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogRegTest {

    @InjectMocks
    private LogReg logReg;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegister() {
        // 创建模拟请求数据
        Map<String, String> request = new HashMap<>();
        request.put("username", "testuser");
        request.put("password", "testpassword");
        request.put("email", "test@example.com");

        // 创建模拟User对象
        User user = new User();
        user.setUserId(1);
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        // 模拟userService的registerUser方法返回模拟User对象
        Mockito.when(userService.registerUser("testuser", "testpassword", "test@example.com")).thenReturn(user);

        // 调用register方法并断言返回的ResponseEntity
        ResponseEntity<?> response = logReg.register(request);
        assertEquals("user", response.getBody()); // 这里根据你的业务逻辑来断言

        // 验证userService的registerUser方法是否被调用
        Mockito.verify(userService).registerUser("testuser", "testpassword", "test@example.com");
    }

    @Test
    public void testLogin() {
        // 创建模拟请求数据
        Map<String, String> request = new HashMap<>();
        request.put("username", "testuser");
        request.put("password", "testpassword");

        // 创建模拟User对象
        User user = new User();
        user.setUserId(1);
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        // 模拟userService的loginUser方法返回模拟User对象
        Mockito.when(userService.loginUser("testuser", "testpassword")).thenReturn(user);

        // 调用login方法并断言返回的ResponseEntity
        ResponseEntity<?> response = logReg.login(request);
        Mockito.verify(userService).loginUser("testuser", "testpassword");
    }
}

