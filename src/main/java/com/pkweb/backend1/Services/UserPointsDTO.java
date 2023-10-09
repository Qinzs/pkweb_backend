package com.pkweb.backend1.Services;

public class UserPointsDTO {
    private String username;
    private int points;

    public UserPointsDTO(String username, int points) {
    }

    // Getter方法
    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    // Setter方法
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

