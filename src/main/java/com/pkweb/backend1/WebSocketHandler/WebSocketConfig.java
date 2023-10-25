package com.pkweb.backend1.WebSocketHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myChatHandler(), "/ws/chat")
                .addInterceptors(new UserIdHandshakeInterceptor())
                .setAllowedOrigins("*");

        // 注册第二个WebSocket处理器
        registry.addHandler(myNotificationHandler(), "/ws/match")
                .addInterceptors(new UserIdHandshakeInterceptor())
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler myChatHandler() {
        return new MyWebSocketHandler();
    }

    // 第二个WebSocket处理器的@Bean方法
    @Bean
    public WebSocketHandler myNotificationHandler() {
        return new MatchingWebSocketHandler();
    }
}
