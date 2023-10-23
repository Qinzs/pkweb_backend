package com.pkweb.backend1.Repositories;

import com.pkweb.backend1.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // 查询给定用户的未读消息
    List<ChatMessage> findByToUserIdAndStatus(String toUserId, String status);

    // 查询两个用户之间的聊天记录
    List<ChatMessage> findByFromUserIdAndToUserIdOrToUserIdAndFromUserIdOrderBySendTimeAsc(String fromUserId, String toUserId, String userId2, String userId1);
    // 查询与特定用户相关的所有聊天记录
    List<ChatMessage> findByFromUserIdOrToUserIdOrderBySendTimeAsc(String userId, String userId2);

}
