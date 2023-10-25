package com.pkweb.backend1.Controllers;

import com.pkweb.backend1.Controllers.ForgetPassword;
import com.pkweb.backend1.Repositories.UserMapper;
import com.pkweb.backend1.pojo.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForgetPasswordTest {

    @InjectMocks
    private ForgetPassword forgetPassword;

    @Mock
    private UserMapper userMapper;

    @Mock
    private JavaMailSender mailSender;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Set private fields if needed using ReflectionTestUtils
        ReflectionTestUtils.setField(forgetPassword, "UserEmail", "z1605235289@gmail.com");
        ReflectionTestUtils.setField(forgetPassword, "newPassword", "1789");
        ReflectionTestUtils.setField(forgetPassword, "code", "000000");
    }

    @Test
    public void testCheckUserByEmail() {
        // Create a test user
        User testUser = new User(1, "TestUser", "TestPassword", "z1605235289@gmail.com",
                Date.valueOf("2023-10-25"), Date.valueOf("2023-10-25"), "TestProfile");

        // Mock the behavior of the UserMapper
        Mockito.when(userMapper.findByEmail("z1605235289@gmail.com")).thenReturn(testUser);

        // Call the method under test
        String result = forgetPassword.checkUserByEmail("{\"Email\": \"z1605235289@gmail.com\"}");

        // Add your assertions here to check the result
        assertEquals("OK", result);
    }

    @Test
    public void testInputCode() {
        // Set the code to a known value
        ReflectionTestUtils.setField(forgetPassword, "code", "123456");

        // Call the method under test with the correct code
        String result1 = forgetPassword.inputCode("{\"Code\": \"123456\"}");

        // Call the method under test with an incorrect code
        String result2 = forgetPassword.inputCode("{\"Code\": \"654321\"}");

        // Add your assertions here to check the results
        assertEquals("Right code", result1);
        assertEquals("Wrong code", result2);
    }

    @Test
    public void testSetPasswordByID() {
        // Create a test user
        User testUser = new User(1, "TestUser", "TestPassword", "z1605235289@gmail.com",
                Date.valueOf("2023-10-25"), Date.valueOf("2023-10-25"), "TestProfile");

        // Mock the behavior of the UserMapper
        Mockito.when(userMapper.findByEmail("z1605235289@gmail.com")).thenReturn(testUser);

        // Call the method under test to set a new password
        String result = forgetPassword.setPasswordByID("{\"Password\": \"NewPassword\"}");

        // Add your assertions here to check the result
        assertEquals("OK", result);
    }

    // Add more test methods for other controller methods
}
