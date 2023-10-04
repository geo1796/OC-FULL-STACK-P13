package com.openclassrooms.yourcaryourway.service;

import com.openclassrooms.yourcaryourway.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAllByChatId(Integer chatId);
}
