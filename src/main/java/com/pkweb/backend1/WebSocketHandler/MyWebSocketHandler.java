package com.pkweb.backend1.WebSocketHandler;

import com.pkweb.backend1.Entity.ChatMessage;
import com.pkweb.backend1.Entity.User;
import com.pkweb.backend1.Services.ChatMessageService;
import com.pkweb.backend1.Services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.pkweb.backend1.dto.IncomingMessage;
import com.pkweb.backend1.dto.ExtendedChatMessage;
import com.pkweb.backend1.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> usersSessions = new ConcurrentHashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private UserServices userService;  // 注入UserService
    // ... 其他代码与之前相同

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            // 首先，将消息映射到IncomingMessage DTO
            IncomingMessage incomingMsg = objectMapper.readValue(message.getPayload(), IncomingMessage.class);

            // 然后，您可以将IncomingMessage转换为ChatMessage实体
            ChatMessage chatMessage = new ChatMessage();
            //根据socket送来的fromUser的id，从数据库中获取用户信息
            chatMessage.setFromUserId(String.valueOf(incomingMsg.getFromUser().getId()));
            chatMessage.setToUserId(String.valueOf(incomingMsg.getToContactId()));
            chatMessage.setContent(incomingMsg.getContent());
            chatMessage.setSendTime(new Date(incomingMsg.getSendTime()));
            chatMessage.setStatus(incomingMsg.getStatus());  // 这里您可能需要转换或设置状态

            // 业务逻辑处理
            WebSocketSession recipientSession = usersSessions.get(chatMessage.getToUserId());
            System.err.println("Sending message: " + chatMessage);

            if (recipientSession != null && recipientSession.isOpen()) {
                // Convert chatMessage to JSON string
                chatMessage.setStatus("read");
//                String chatMessageJson = objectMapper.writeValueAsString(chatMessage);
//                TextMessage textMessage = new TextMessage(chatMessageJson);
                User fromUserEntity = userService.getUserById(chatMessage.getFromUserId());  // 获取发送消息用户的详细信息
                UserDTO fromUserDTO = new UserDTO(fromUserEntity);  // 将User实体转换为UserDTO
                ExtendedChatMessage extendedChatMessage = new ExtendedChatMessage(chatMessage, fromUserDTO);  // 使用UserDTO创建ExtendedChatMessage

                String extendedChatMessageJson = objectMapper.writeValueAsString(extendedChatMessage);  // Convert ExtendedChatMessage to JSON string
                TextMessage textMessage = new TextMessage(extendedChatMessageJson);

                //WebSocketSession recipientSession = usersSessions.get(chatMessage.getToUserId());

                //打印出来
                System.out.println("textMessage: " + textMessage);
                recipientSession.sendMessage(textMessage);

            } else {
                chatMessage.setStatus("unread");
            }
            chatMessageService.sendMessage(chatMessage);


        } catch (Exception e) {
            // 处理任何异常
            e.printStackTrace();
        }
    }


    // ... 其他代码与之前相同


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 从WebSocketSession获取用户ID，并添加到usersSessions中
        String userId = (String) session.getAttributes().get("userId");
        System.err.println("Sending userId: " + userId);
        if (userId != null) {
            usersSessions.put(userId, session);
        }

        // 从数据库中获取与该用户相关的聊天记录
        List<ChatMessage> userChatMessages = chatMessageService.getMessagesForUser(userId);

        // 将每个消息转换为TextMessage并发送给前端

        for (ChatMessage chatMsg : userChatMessages) {
            User fromUserEntity = userService.getUserById(chatMsg.getFromUserId());  // 获取发送消息用户的详细信息
            UserDTO fromUserDTO = new UserDTO(fromUserEntity);  // 将User实体转换为UserDTO
            ExtendedChatMessage extendedChatMessage = new ExtendedChatMessage(chatMsg, fromUserDTO);  // 使用UserDTO创建ExtendedChatMessage
            TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(extendedChatMessage));
            session.sendMessage(textMessage);
        }

    }


    // 其他必要的重写方法和业务逻辑...
}
