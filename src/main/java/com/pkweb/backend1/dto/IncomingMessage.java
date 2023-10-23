package com.pkweb.backend1.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class IncomingMessage {

    private String id;
    private long sendTime;
    private String type;
    private String status;
    private int toContactId;
    private User fromUser;
    private String content;

    // Getter and Setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter for sendTime
    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    // ... [Provide similar getters and setters for other fields as well]

    // Getter and Setter for fromUser
    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public int getToContactId() {
        return toContactId;
    }

    public String getContent() {
        return content;
    }

    public void setToContactId(int toContactId) {
        this.toContactId = toContactId;
    }

    public String getStatus() {
        return status;
    }


    public static class User {
        private String nickname;
        private String avatar;
        private int id;

        // Getter and Setter for nickname
        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        // Getter and Setter for avatar
        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        // Getter and Setter for id
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        // ... [You can also add constructor, and other necessary methods here]
    }

    // ... [You can add constructor, and other necessary methods for IncomingMessage class here]
}
