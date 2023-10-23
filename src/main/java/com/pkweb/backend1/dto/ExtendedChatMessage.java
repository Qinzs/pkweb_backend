package com.pkweb.backend1.dto;

import com.pkweb.backend1.Entity.ChatMessage;

public class ExtendedChatMessage {

    private ChatMessage chatMessage;
    private UserDTO fromUser; // 使用UserDTO代替User

    public ExtendedChatMessage(ChatMessage chatMessage, UserDTO fromUser) {
        this.chatMessage = chatMessage;
        this.fromUser = fromUser;
    }

    // getter and setters ...

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }

    public UserDTO getFromUser() { // 注意这里的返回类型已经修改为UserDTO
        return fromUser;
    }

    public void setFromUser(UserDTO fromUser) { // 注意这里的参数类型已经修改为UserDTO
        this.fromUser = fromUser;
    }
}
