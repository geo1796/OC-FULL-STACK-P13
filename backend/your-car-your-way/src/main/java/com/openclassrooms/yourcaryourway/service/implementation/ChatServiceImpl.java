package com.openclassrooms.yourcaryourway.service.implementation;

import com.openclassrooms.yourcaryourway.dto.request.NewChat;
import com.openclassrooms.yourcaryourway.dto.response.ChatResponse;
import com.openclassrooms.yourcaryourway.exception.NotFoundException;
import com.openclassrooms.yourcaryourway.mapper.ChatMapper;
import com.openclassrooms.yourcaryourway.model.Chat;
import com.openclassrooms.yourcaryourway.model.User;
import com.openclassrooms.yourcaryourway.repository.ChatRepository;
import com.openclassrooms.yourcaryourway.repository.MessageRepository;
import com.openclassrooms.yourcaryourway.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {
    private ChatRepository chatRepository;
    private MessageRepository messageRepository;
    private ChatMapper chatMapper;

    @Override
    public Chat createChat(NewChat newChat) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return chatRepository.save(Chat.builder()
                .author(user)
                .subject(newChat.getSubject())
                .date(new Date())
                .build());
    }

    @Override
    public ChatResponse getById(Integer id) {
        Optional<Chat> optionalChat = chatRepository.findById(id);
        if (optionalChat.isEmpty()) {
            throw new NotFoundException();
        }
        Chat chat = optionalChat.get();
        chat.setMessages(messageRepository.findAllByChatId(id));
        return chatMapper.toDto(chat);
    }
}
