package com.openclassrooms.yourcaryourway.websocket;

import com.openclassrooms.yourcaryourway.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    @Autowired
    ChatService chatService;

    @Bean
    public WebSocketHandler wsHandler() {
        WebSocketHandler chatHandler = new ChatHandler(chatService);
        return new UrlParamWsHandlerDecorator(chatHandler);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler(), "/chat").setAllowedOrigins(allowedOrigins); // Définir le point d'entrée WebSocket
    }
}
