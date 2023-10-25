package com.pkweb.backend1.WebSocketHandler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationWebSocketHandler extends TextWebSocketHandler {

    // 使用ConcurrentHashMap存储用户的WebSocketSession
    private Map<String, WebSocketSession> usersSessions = new ConcurrentHashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 这里可以处理从客户端发来的消息，例如客户端请求未读通知数量
        String userId = (String) session.getAttributes().get("userId");
        // 假设发送给客户端的消息是一个字符串，表示未读通知的数量
        TextMessage reply = new TextMessage("You have 5 unread notifications");
        session.sendMessage(reply);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 用户连接后，将用户的session保存到map中
        String userId = (String) session.getAttributes().get("userId");
        if (userId != null) {
            usersSessions.put(userId, session);
        }

        // 可以在这里发送一个欢迎消息或初始通知
        session.sendMessage(new TextMessage("Welcome to the notification service!"));
    }

    // 其他必要的重写方法和业务逻辑...
}
