package com.openclassrooms.yourcaryourway.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.yourcaryourway.dto.request.ChatMessage;
import com.openclassrooms.yourcaryourway.dto.request.MessageRequest;
import com.openclassrooms.yourcaryourway.dto.response.MessageResponse;
import com.openclassrooms.yourcaryourway.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
@Slf4j
public class ChatHandler implements WebSocketHandler {

    private final ChatService chatService;
    private final ObjectMapper objectMapper;
    private final Set<WebSocketSession> sessions = new HashSet<>();

    ChatHandler(ChatService chatService) {
        this.chatService = chatService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String chatId = (String) session.getAttributes().get("id");
        log.debug(chatId);
        String payload = (String) message.getPayload();
        MessageRequest messageRequest = objectMapper.readValue(payload, ChatMessage.class).getMessage();
        MessageResponse messageResponse = chatService.addMessage(Integer.valueOf(chatId), messageRequest.getUserId(), messageRequest.getContent());
        for (WebSocketSession s : sessions) {
            if (Objects.equals(s.getAttributes().get("id"), chatId)) {
                s.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageResponse)));
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.debug("transport error");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.debug("session closed");
        sessions.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
