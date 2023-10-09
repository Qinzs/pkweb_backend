package com.pkweb.backend1.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "[User]")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userId;

    @Column(name = "Username", nullable = false, length = 10)
    private String username;

    @Column(name = "Password", nullable = false, length = 13)
    private String password;

    @Column(name = "Email", length = 20)
    private String email;

    @Column(name = "RegistrationDate")
    private Date registrationDate;

    @Column(name = "LastLoginDate")
    private Date lastLoginDate;

    @Lob
    @Column(name = "Profile")
    private byte[] profile;

    // Getter and Setter methods

// Getter and Setter methods

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }
    @Transient
    private int answeredCount;

    @Transient
    private int publishedCount;

// getters and setters for the two fields
public int getAnsweredCount() {
    return answeredCount;
}

    public void setAnsweredCount(int answeredCount) {
        this.answeredCount = answeredCount;
    }



}
