package com.openclassrooms.yourcaryourway.service.implementation;

import com.openclassrooms.yourcaryourway.model.Message;
import com.openclassrooms.yourcaryourway.repository.MessageRepository;
import com.openclassrooms.yourcaryourway.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;
    @Override
    public List<Message> findAllByChatId(Integer chatId) {
        return messageRepository.findAllByChatId(chatId);
    }
}
