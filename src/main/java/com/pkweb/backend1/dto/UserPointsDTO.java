package com.pkweb.backend1.dto;

public class UserPointsDTO {
    private String username;
    private int points;
    // 头像
    private byte[] profile;


    public UserPointsDTO(String username, int points) {
    }

    public UserPointsDTO(String username, int points, byte[] profile) {
        this.username = username;
        this.points = points;
        this.profile = profile;
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

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }
}