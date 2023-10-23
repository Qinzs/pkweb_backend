package com.pkweb.backend1.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "chatlist")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "from_user_id", nullable = false)
    private String fromUserId;

    @Column(name = "to_user_id", nullable = false)
    private String toUserId;

    @Column(nullable = false)
    private String content;

    @Column(name = "send_time")
    private Date sendTime;

    @Column(nullable = false)
    private String status;  // 可以考虑使用枚举 (enum) 来表示消息状态

    // 默认构造函数
    public ChatMessage() {
    }

    // 带参数构造函数
    public ChatMessage(String fromUserId, String toUserId, String content, Date sendTime, String status) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.content = content;
        this.sendTime = sendTime;
        this.status = status;
    }

    // getter, setter ...
    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public Date getSendTime() {
        return sendTime;
    }
//给statue写一个getter
    public String getStatus() {
        return status;
    }

    public void setSendTime(Date date) {
        this.sendTime = date;
    }
    @Override
    public String toString() {
        return "ChatMessage{" +
                "fromUserId='" + fromUserId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                ", status='" + status + '\'' +
                '}';
    }

}
