//package com.pkweb.backend1.controller.pk;
//
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//import java.io.IOException;
//
//public class MyWebSocketHandler extends TextWebSocketHandler {
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
//        System.out.println("Received message: " + message.getPayload());
//        session.sendMessage(new TextMessage("Hello, Client!"));
//    }
//
//
//}
