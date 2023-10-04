package com.openclassrooms.yourcaryourway.service;

import com.openclassrooms.yourcaryourway.dto.request.NewChat;
import com.openclassrooms.yourcaryourway.dto.response.ChatResponse;
import com.openclassrooms.yourcaryourway.dto.response.MessageResponse;
import com.openclassrooms.yourcaryourway.model.Chat;


public interface ChatService {
    Chat createChat(NewChat newChat);
    ChatResponse getById(Integer id);

    MessageResponse addMessage(Integer chatId, Integer userId, String content);
}
