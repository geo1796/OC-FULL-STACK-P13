package com.openclassrooms.yourcaryourway.websocket;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

import java.util.Objects;

public class UrlParamWsHandlerDecorator extends WebSocketHandlerDecorator {

    public UrlParamWsHandlerDecorator(WebSocketHandler delegate) {
        super(delegate);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String query = Objects.requireNonNull(session.getUri()).getQuery();
        if (!query.isEmpty()) {
            String[] queryParams = query.split("&");
            for (String param : queryParams) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    session.getAttributes().put(keyValue[0], keyValue[1]);
                }
            }
        }

        // Call the original afterConnectionEstablished method
        super.afterConnectionEstablished(session);
    }
}

