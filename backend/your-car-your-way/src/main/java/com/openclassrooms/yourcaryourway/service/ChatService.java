package com.openclassrooms.yourcaryourway.service;

import com.openclassrooms.yourcaryourway.dto.request.NewChat;
import com.openclassrooms.yourcaryourway.dto.response.ChatResponse;
import com.openclassrooms.yourcaryourway.model.Chat;

import java.util.List;

public interface ChatService {
    Chat createChat(NewChat newChat);
    ChatResponse getById(Integer id);
}
