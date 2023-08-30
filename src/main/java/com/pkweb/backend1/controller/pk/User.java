package com.pkweb.backend1.controller.pk;

import java.sql.Blob;
import java.sql.Date;

public class User {
    private Integer UserID;
    private String Username;
    private String Password;
    private String Email;
    private Date RegistrationDate;
    private Date LastLoginDate;
    private String Profile;

    public User() {
    }

    public User(Integer userID, String username, String password, String email, Date registrationDate, Date lastLoginDate, String profile) {
        UserID = userID;
        Username = username;
        Password = password;
        Email = email;
        RegistrationDate = registrationDate;
        LastLoginDate = lastLoginDate;
        Profile = profile;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        RegistrationDate = registrationDate;
    }

    public Date getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }

    public String getProfile() {
        return Profile;
    }

    public void setProfile(String profile) {
        Profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", RegistrationDate=" + RegistrationDate +
                ", LastLoginDate=" + LastLoginDate +
                ", Profile=" + Profile +
                '}';
    }
}
