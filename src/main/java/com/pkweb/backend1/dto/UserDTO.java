package com.pkweb.backend1.dto;
import com.pkweb.backend1.Entity.User;
import java.util.Base64;

public class UserDTO {

    private int userId;
    private String username;
    private String profile; // 这里profile已经是Base64编码的字符串

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        // 假设User中的getProfile()方法返回byte[]类型的数据
    }

    // getter and setters ...

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getProfile() {
        return profile;
    }

    // ... 其他方法
}
