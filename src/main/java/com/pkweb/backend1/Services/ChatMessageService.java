package com.pkweb.backend1.Services;

import com.pkweb.backend1.Entity.ChatMessage;
import com.pkweb.backend1.Repositories.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    // 获取指定用户的未读消息
    public List<ChatMessage> getUnreadMessages(String userId) {
        return chatMessageRepository.findByToUserIdAndStatus(userId, "unread");
    }

    // 获取两个用户之间的聊天记录
    public List<ChatMessage> getChatHistory(String fromUserId, String toUserId) {
        return chatMessageRepository.findByFromUserIdAndToUserIdOrToUserIdAndFromUserIdOrderBySendTimeAsc(fromUserId, toUserId, toUserId, fromUserId);
    }

    // 发送消息
    public void sendMessage(ChatMessage message) {
        // 日志记录message的值，确保它是你预期的
        System.err.println("Sending message: " + message);

        // 其他可能的检查...
        if(message.getFromUserId() == null || message.getToUserId() == null) {
            System.out.println("Error: User IDs are not set properly!");
            return;
        }
        chatMessageRepository.save(message);
    }

    // 标记消息为已读
    public void markAsRead(Long messageId) {
        ChatMessage message = chatMessageRepository.findById(messageId).orElse(null);
        if (message != null) {
            message.setStatus("read");
            chatMessageRepository.save(message);
        }
    }

    // ... 其他需要的方法

    // 获取与指定用户相关的所有聊天记录
    public List<ChatMessage> getMessagesForUser(String userId) {
        return chatMessageRepository.findByFromUserIdOrToUserIdOrderBySendTimeAsc(userId, userId);
    }
}
